package com.cartorgon.fwkspringbatchmongodbdemo.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cartorgon.fwkspringbatchmongodbdemo.model.Tienda;

@Repository
public interface TiendaRepository extends MongoRepository<Tienda, String> {

}
