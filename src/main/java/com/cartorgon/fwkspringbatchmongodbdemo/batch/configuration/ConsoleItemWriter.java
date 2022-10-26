package com.cartorgon.fwkspringbatchmongodbdemo.batch.configuration;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleItemWriter implements ItemWriter<Object> {

  @Override
  public final void write(final List<? extends Object> items) throws Exception {
        items.forEach(item -> log.info(item.toString()));
  }
}
