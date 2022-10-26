package com.cartorgon.fwkspringbatchmongodbdemo.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.cartorgon.fwkspringbatchmongodbdemo.model.Tienda;

public class TiendaProcessor implements ItemProcessor<Tienda, Tienda> {
  private static final Logger log = LoggerFactory.getLogger(TiendaProcessor.class);

  @Override
  public Tienda process(final Tienda item) throws Exception {
      log.info("Processing tienda data: {}", item);
      return item;
  }
}
