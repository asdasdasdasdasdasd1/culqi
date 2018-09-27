package com.sissylla01;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pe.culqi.CulqiApplication;
import com.pe.culqi.bean.BaseResponseBean;
import com.pe.culqi.controller.LoginController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CulqiApplication.class)
public class LoginControllerTest {
	
	@Autowired
	LoginController loginController;
	BaseResponseBean brb;
	
	@Test
	public final void testInicio() {
		String index = "redirect:/index";
		assertTrue(index.equals(loginController.inicio()));		
	}
	@Test
	public final void testIndex() {
		String index = "index";
		assertTrue(index.equals(loginController.index()));		
	}	
//	@Test
//	public final void testTokensFinal() throws Exception {
//		//
//	}
//	@Test
//	public final void testTokens() {
//		//
//	}
}
