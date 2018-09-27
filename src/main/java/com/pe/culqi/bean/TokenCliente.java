package com.pe.culqi.bean;

import feign.Headers;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface TokenCliente {
	@RequestLine("GET")
	@Headers("Content-Type: application/json")
	BaseResponseBean validarToken(TokenInput2 tokenInput2);
	@RequestLine("POST /tokensFinal")
	@Headers("Content-Type: application/json")
	TokenOutput obtenerToken(TokenInput3 tokenInput3);	
}
