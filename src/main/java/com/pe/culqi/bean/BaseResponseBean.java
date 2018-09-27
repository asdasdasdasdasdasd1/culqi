package com.pe.culqi.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseResponseBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("number")  // length luhn
	private transient Object number;
	@JsonProperty("scheme")
	private String scheme;
	@JsonProperty("type")
	private String type;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("prepaid")
	private String prepaid;	
	@JsonProperty("country")  //numeric alpha2 name emoji currency latitude longuitude
	private transient Object country;
	@JsonProperty("bank")
	private transient Object bank; //name url phone city	
}

//{
//"number": {
//  "length": 16,
//  "luhn": true
//},
//"scheme": "visa",
//"type": "debit",
//"brand": "Visa/Dankort",
//"prepaid": false,
//"country": {
//  "numeric": "208",
//  "alpha2": "DK",
//  "name": "Denmark",
//  "emoji": "🇩🇰",
//  "currency": "DKK",
//  "latitude": 56,
//  "longitude": 10
//},
//"bank": {
//  "name": "Jyske Bank",
//  "url": "www.jyskebank.dk",
//  "phone": "+4589893300",
//  "city": "Hjørring"
//}
//}