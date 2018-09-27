package com.pe.culqi.bean;

import java.io.Serializable;
import java.text.DateFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenOutput implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("token") //tkm_live_4444333322221111-2020-10
	private String token;
	@JsonProperty("brand") //visa
	private String brand;
	@JsonProperty("creation_dt") //2018-06-20 18:00:00
	private String creation_dt;
	
}
