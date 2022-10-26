package com.cartorgon.fwkspringbatchmongodbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableMongoAuditing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FwkspringBatchMongodbdemoApplication /*implements CommandLineRunner*/ {

//  @Override
//  public final void run(final String... args) throws Exception {
//    try(InputStream inputStream = new DefaultResourceLoader().getResource(DefaultResourceLoader.CLASSPATH_URL_PREFIX + "mongoDbQuery.json").getInputStream();) {
//      
//      log.info(new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8));
//      
//      final String staticDataString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//      log.info(staticDataString);
//      final List<Object> resList = new JSONArray(staticDataString).toList();       
//      log.info(resList.toString());
//        
//      final ObjectMapper objectMapper = new ObjectMapper();
//      final List<MongoDbJsonQuery> resList2 = Arrays.asList(objectMapper.readValue(inputStream, MongoDbJsonQuery[].class));
//      final List<Object> resList2 = Arrays.asList(objectMapper.readValue(inputStream, Object[].class));
//      final List<Map<String, Object>> resList2 = Arrays.asList(objectMapper.readValue(inputStream, Map[].class));
//      final Map<String, String> resMap = new HashMap<>(resList2.size());
//      
//      resList2
//      .forEach(query -> {
//        try {
//          log.info(query.getQuery().toString());
//          resMap.put(query.getId(), objectMapper.writeValueAsString(query.getQuery()));
//          query.setQuery(objectMapper.writeValueAsString(query.getQuery()));          
//        } catch (JsonProcessingException jpExcp) {
//          log.error("", jpExcp);
//        }
//      })
//      ;
//      
//      
//      objectMapper.writeValueAsString(resList2.get(0).getQuery());
//      log.info(resList2.toString());
//      final Dictionary<String, String> myMap = new Hashtable<>(2);
//      myMap.put("primero", "Este es mi primer elemento");
//      myMap.put("primero", "Este es mi segundo elemento");
//      
//      log.info(String.format("This is my 'primero' element: %s", myMap.get("primero")));
//      
//      log.info(String.format("writeValueAsString de string vacio: %s", objectMapper.writeValueAsString("")));
//      log.info(String.format("writeValueAsString de null: %s", objectMapper.writeValueAsString(null)));
//        
//    } catch (final IOException ioExcp) {
//      log.error("IOException", ioExcp);
//    }    
//  }

	public static final void main(final String[] args) {	  
	  new SpringApplication(FwkspringBatchMongodbdemoApplication.class).run(args);    
	}

}
