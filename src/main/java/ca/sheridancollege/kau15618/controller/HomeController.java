package ca.sheridancollege.kau15618.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.kau15618.beans.CoffeeProduct;

@Controller
public class HomeController {
	

		final String REST_URL = "http://localhost:8080/products";
		@GetMapping("/")
		public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<CoffeeProduct[]> responseEntity = restTemplate.getForEntity(REST_URL, CoffeeProduct[].class);
		model.addAttribute("coffeeList", responseEntity.getBody());
		return "index";
		}
		
		@GetMapping(value="/products/{id}", produces="application/json")
		@ResponseBody
		public CoffeeProduct getProduct(@PathVariable Long id, RestTemplate restTemplate) {
		ResponseEntity<CoffeeProduct> responseEntity = restTemplate.getForEntity(REST_URL + "/" + id, CoffeeProduct.class);
		return responseEntity.getBody();
		}

	}



