package com.pe.culqi.service.impl;

import org.springframework.stereotype.Component;

import com.pe.culqi.service.FeignService;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Component("feignService")
public class FeignServiceImpl implements FeignService{

	public <T> T buildClient(Class<T> valueType, String uri){
		return Feign.builder().logger(new Logger.JavaLogger()).logLevel(Logger.Level.FULL).encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target( valueType, uri);
	}
	
}
