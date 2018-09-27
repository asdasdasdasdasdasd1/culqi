package com.pe.culqi.service;

public interface FeignService {

	public <T> T buildClient(Class<T> valueType, String uri);
}
