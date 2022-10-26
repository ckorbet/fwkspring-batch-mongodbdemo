package com.cartorgon.fwkspringbatchmongodbdemo.batch.configuration;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class SelectFromMongoDbTasklet implements Tasklet {
  
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public final RepeatStatus execute(final StepContribution contribution, 
                                    final ChunkContext chunkContext) throws Exception {
    
    
    // TODO Auto-generated method stub
    return null;
  }

}
