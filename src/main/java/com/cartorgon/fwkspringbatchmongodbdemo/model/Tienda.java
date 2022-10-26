package com.cartorgon.fwkspringbatchmongodbdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "tienda")
public class Tienda {  
  @Id
  private Long codTienda;
  private String poblacion;
  private String direccion;
}
