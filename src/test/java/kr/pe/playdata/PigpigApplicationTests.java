package kr.pe.playdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.pe.playdata.controller.Controller;

@SpringBootTest
class PigpigApplicationTests {

	@Autowired
	private Controller controller;
	private MockMvc mock;
	
	@BeforeEach
	public void init() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	void contextLoads() {
		System.out.println(11111);
		
	}

}
