package com.cartorgon.fwkspringbatchmongodbdemo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MongoDbJsonQuery implements Serializable {
  
  private static final long serialVersionUID = 1038103780137610437L;
  
  private String id;
  private Object query;

}
