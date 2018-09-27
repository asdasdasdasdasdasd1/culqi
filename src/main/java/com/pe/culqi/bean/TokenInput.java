package com.pe.culqi.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenInput implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("pan")
	private String pan;
	@JsonProperty("exp_year")
	private int exp_year;
	@JsonProperty("exp_month")
	private int exp_month;
	
}