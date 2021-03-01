package com.dc.hb.facade.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HbControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
       // User user =new User("root","root");
        //session.setAttribute("user",user); 
    }
	
	
	@Test
	public void sendHbTest() {
		String json="{\r\n"
				+ "  \"userId\":\"1\",\r\n"
				+ "  \"num\":\"10\",\r\n"
				+ "  \"type\":\"Normal\",\r\n"
				+ "  \"money\":200,\r\n"
				+ "  \"description\":\"恭喜发财\",\r\n"
				+ "  \"groupId\":1001\r\n"
				+ "}";
        try {
			mvc.perform(MockMvcRequestBuilders.post("/hb/sendHbByGroup")
			            .accept(MediaType.APPLICATION_JSON_UTF8)
			            .content(json.getBytes()) //传json参数
			            .session(session).contentType(MediaType.APPLICATION_JSON_UTF8)
			    )
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void grabHbTest() {
		String json="{\r\n"
				+ "    \"hbId\":\"111\",\r\n"
				+ "    \"userId\":\"1000\"\r\n"
				+ "} ";
        try {
			mvc.perform(MockMvcRequestBuilders.post("/hb/grabHb")
			            .accept(MediaType.APPLICATION_JSON_UTF8)
			            .content(json.getBytes()) //传json参数
			            .session(session).contentType(MediaType.APPLICATION_JSON_UTF8)
			    )
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
