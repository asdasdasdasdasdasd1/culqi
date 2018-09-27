package com.pe.culqi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pe.culqi.bean.BaseResponseBean;
import com.pe.culqi.bean.TokenCliente;
import com.pe.culqi.bean.TokenInput;
import com.pe.culqi.bean.TokenInput2;
import com.pe.culqi.bean.TokenInput3;
import com.pe.culqi.bean.TokenOutput;
import com.pe.culqi.service.FeignService;
import com.pe.culqi.util.Constante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("feignService")
	FeignService feignService;
	
	@GetMapping("/")
	public String inicio() {
		return "redirect:/index";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@PostMapping(value="tokens")
	public @ResponseBody TokenOutput tokens(HttpSession session, @RequestBody TokenInput tokenInput) {
		log.info("SERVICIO /tokens");
		log.info("Pan: "+tokenInput.getPan());
		log.info("Exp_year: "+tokenInput.getExp_year());
		log.info("Exp_month: "+tokenInput.getExp_month());
		//Consumir servicio externo... https://lookup.binlist.net/45717360
		TokenInput2 tokenInput2 = new TokenInput2();
		try {
			tokenInput2.setBin(tokenInput.getPan().substring(0,6));
			log.info("BIN: "+tokenInput2.getBin());
		}catch(IndexOutOfBoundsException indexOutOfBoundsException){
			log.error("FORMATO DEL PAN: " + indexOutOfBoundsException.getMessage());
		}catch(Exception e) {
			log.error("EXCEPCIÓN DESCONOCIDA: " + e.getMessage());
		}
		final String uri1 = Constante.URI1+tokenInput2.getBin();		
		TokenCliente obj1=null;
		BaseResponseBean brb=null;
		try {
			log.info("CONSULTA A binlist: "+uri1);
			obj1 = feignService.buildClient(TokenCliente.class, uri1);
			brb = obj1.validarToken(tokenInput2);
			log.info("RESULTADO DE binlist: "+brb);
		}catch(Exception e) {
			log.error("ERROR EN EL SERVICIO binlist: " + e.getMessage());
		}
		//Crear endpoint... http://localhost:8090/tokensFinal POST -> {p.e. "scheme":"visa"}
		TokenInput3 tokenInput3 = new TokenInput3();
		tokenInput3.setPan(tokenInput.getPan());
		tokenInput3.setExp_year(tokenInput.getExp_year());
		tokenInput3.setExp_month(tokenInput.getExp_month());
		//se guarda scheme en brand
		tokenInput3.setBrand(brb.getScheme());
		log.info("SERVICIO /tokensFinal");
		TokenCliente obj2 = feignService.buildClient(TokenCliente.class,Constante.URI2);
		TokenOutput tokenOutput=null;
		try {
			log.info("CREA SERVICIO FINAL: "+ Constante.URI2 + "/tokensFinal");
			tokenOutput = obj2.obtenerToken(tokenInput3);			
			log.info("RESULTADO FINAL: "+tokenOutput);
		}catch(Exception e) {
			log.error("ERROR EN EL SERVICIO binlist: " + e.getMessage());
		}		
		return tokenOutput;
	}
	@PostMapping(value="tokensFinal")
	public @ResponseBody TokenOutput tokensFinal(@RequestBody TokenInput3 tokenInput3) {
		TokenOutput tokenOutput = new TokenOutput();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		tokenOutput.setToken("tkn_live_" + tokenInput3.getPan() + "-" + tokenInput3.getExp_year() + "-" + tokenInput3.getExp_month());
		tokenOutput.setBrand(tokenInput3.getBrand());
		tokenOutput.setCreation_dt(dateFormat.format(date));
		log.info("CONSTRUYENDO EL NUEVO SERVICIO");
		return tokenOutput;
	}
}

//String uri = "https://lookup.binlist.net/45717360";
//RestTemplate resultTemplate = new RestTemplate(); 
//String result = resultTemplate.getForObject(uri, String.class);