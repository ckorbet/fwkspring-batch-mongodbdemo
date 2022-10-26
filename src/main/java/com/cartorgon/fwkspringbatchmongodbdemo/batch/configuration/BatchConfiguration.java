package com.cartorgon.fwkspringbatchmongodbdemo.batch.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.util.json.ParameterBindingDocumentCodec;
import org.springframework.data.mongodb.util.json.ParameterBindingJsonReader;

import com.cartorgon.fwkspringbatchmongodbdemo.batch.listener.JobCompletionNotificationListener;
import com.cartorgon.fwkspringbatchmongodbdemo.batch.processor.TiendaProcessor;
import com.cartorgon.fwkspringbatchmongodbdemo.model.Tienda;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  
  private static BeanWrapperFieldSetMapper<Tienda> mapper = new BeanWrapperFieldSetMapper<>();  

  @Bean
  public FlatFileItemReader<Tienda> readerFromCsv() {
    mapper.setTargetType(Tienda.class);
    return new FlatFileItemReaderBuilder<Tienda>()
        .name("tiendaItemReaderFromCsv")
        .resource(new ClassPathResource("sourceData.csv"))
        .delimited()
        .names("codTienda", "poblacion", "direccion" )
        .fieldSetMapper(mapper)
        .build();
  }
  
  @Bean
  public TiendaProcessor processor() {
      return new TiendaProcessor();
  }
  
  @Bean
  public MongoItemWriter<Tienda> writer(final MongoTemplate mongoTemplate) {
      return new MongoItemWriterBuilder<Tienda>()
          .template(mongoTemplate)
          .collection("tiendas")
          .build();
  }
  
  private String replacePlaceholders(String input, List<Object> values) {
    ParameterBindingJsonReader reader = new ParameterBindingJsonReader(input, values.toArray());
    DecoderContext decoderContext = DecoderContext.builder().build();
    Document document = new ParameterBindingDocumentCodec().decode(reader, decoderContext);
    return document.toJson();
  }
  
  @Bean
  public MongoItemReader<Tienda> readerFromMongoDb(final MongoTemplate mongoTemplate) {
    final Map<String, Sort.Direction> sortCriteria = Map.of("_id", Direction.DESC);

    return new MongoItemReaderBuilder<Tienda>()
        .name("tiendaItemReaderFromMongoDb")
        .template(mongoTemplate)
        .collection("tiendas")
        
        .jsonQuery("{ poblacion: ?0 ,  direccion: ?1 }") // OK
//        .jsonQuery("{ poblacion: ?poblacion ,  direccion: ?direccion }") // OK
//        .jsonQuery("{ $and: [ { poblacion: ?0 }, { direccion: ?1 } ] }") // OK
        
        .parameterValues(Arrays.asList("Pasadena", "My direccion de pasadena"))
//        .parameterValues(Collections.EMPTY_LIST)
        .sorts(sortCriteria)
        .targetType((Class<? extends Tienda>) Tienda.class)
        .build();
  } 
  
  @Bean
  public Step step1(final StepBuilderFactory stepBuilderFactory,
                    final FlatFileItemReader<Tienda> readerFromCsv, 
                    final MongoItemWriter<Tienda> itemWriter) {
      return stepBuilderFactory
          .get("step1")
          .<Tienda, Tienda>chunk(5)
          .reader(readerFromCsv)
          .processor(processor())
          .writer(itemWriter)
          .build();
  }
  
  @Bean
  public Step step2(final StepBuilderFactory stepBuilderFactory,
                    final MongoItemReader<Tienda> readerFromMongoDb) {
    return stepBuilderFactory
        .get("step2")
        .<Tienda, Tienda>chunk(5)
        .reader(readerFromMongoDb)
        .writer(new ConsoleItemWriter())
        .build();
  }
  
  @Bean
  public Job updateUserJob(final JobBuilderFactory jobBuilderFactory,
                           final JobCompletionNotificationListener listener, 
                           final Step step1,
                           final Step step2) {
      return jobBuilderFactory
          .get("writeTiendasToMongoDBJob")
          .incrementer(new RunIdIncrementer())
          .listener(listener)
          .start(step1)
          .next(step2)
          .build();
  }

}
