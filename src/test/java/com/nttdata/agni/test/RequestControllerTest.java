package com.nttdata.agni.test;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class RequestControllerTest {

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
        String transformRequest =  getInputMessage();     

        MvcResult response = mvc.perform(post(RESOURCE_LOCATION_BASEURL)
                    .content(transformRequest)
                    .contentType(MediaType.APPLICATION_XML)
                    .accept(MediaType.ALL))
                  .andExpect(status().is2xxSuccessful())   .andDo(print())      
                    .andReturn();
        saveOutputMessage(response.getResponse().getContentAsString());
                  
    }
       

	private String getInputMessage() throws IOException {
		
		String text = new String(Files.readAllBytes(Paths.get("src/test/resources/ccd.xml")));
		return text;
	}
	
	private void saveOutputMessage(String text) throws IOException {

		Files.write(Paths.get("src/test/resources/output.txt"), text.getBytes());
	}
	
 

}
