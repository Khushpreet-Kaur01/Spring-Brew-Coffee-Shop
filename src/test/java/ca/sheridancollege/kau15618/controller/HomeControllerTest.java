package ca.sheridancollege.kau15618.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.kau15618.beans.CoffeeProduct;



@SpringBootTest
@AutoConfigureMockMvc

public class HomeControllerTest {
	 @Autowired
	    private MockMvc mockMvc;
//	 
//	 @Test
//		public void testGetProduct() throws Exception {
//			mockMvc.perform(get("/getProductbyId/1")).andExpect(status().isOk());
//		}

		@Test
		public void testIndex() throws Exception {
			mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
		}
}

//	    @Test
//	    public void testIndexPage() throws Exception {
//	        mockMvc.perform(get("/"))
//	            .andExpect(status().isOk())
//	            .andExpect(view().name("index"));
//	    }
//	   
//
//}
