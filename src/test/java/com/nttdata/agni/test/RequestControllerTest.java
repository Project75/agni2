package com.nttdata.agni.test;


/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.agni.Application;
import com.nttdata.agni.api.rest.RequestController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class RequestControllerTest {

   // private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/fhirtranslator/v1/profile/[0-9]+";
   private static final String RESOURCE_LOCATION_BASEURL = "/fhirtranslator/v1/cda2fhir/";

    @InjectMocks
    RequestController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTranslateHL7toFHIR() throws Exception {
        //User r1 = mockUser("shouldCreateRetrieveDelete");
        String transformRequest =  getInputMessage();
        //byte[] r1Json = toJson(transformRequest);
        MvcResult result=null;String resultString =null;
        
        	//CREATE2
        result = mvc.perform(post("/fhirtranslator/v1/cda2fhir/")
                    .content(transformRequest)
                    .contentType(MediaType.APPLICATION_XML)
                    .accept(MediaType.ALL))
                  .andExpect(status().is2xxSuccessful())   .andDo(print())      
                    .andReturn();
         resultString = result.getResponse().getContentAsString();
            System.out.println("************Final Output");
            	System.out.println(resultString);
      
    }
    
    

	private String getInputMessage() throws IOException {
		
		String text = new String(Files.readAllBytes(Paths.get("src/test/resources/ccd.xml")));
	    /* 
		FileInputStream fis = new FileInputStream("src/test/resources/170.315_b1_toc_inp_ccd_r21_sample1_v5.xml");
		    //InputStream inputStream = new ByteArrayInputStream(input.getBytes());
			 
			    String text = null;
			    try (Scanner scanner = new Scanner(fis, StandardCharsets.UTF_8.name())) {
			        text = scanner.useDelimiter("\\A").next();
			    }
			    */
		//System.out.println("ZZ-Input File:"+text);
			return text;
	}
 

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

}
