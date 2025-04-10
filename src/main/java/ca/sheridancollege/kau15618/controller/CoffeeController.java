package ca.sheridancollege.kau15618.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ca.sheridancollege.kau15618.beans.CoffeeProduct;
import ca.sheridancollege.kau15618.database.DatabaseAccess;

@RestController
@RequestMapping("/products")
public class CoffeeController {
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping
	public List<CoffeeProduct> getAllProducts(){
		return da.getAllProducts();
	}
	
	@GetMapping(value="/{id}")
	public  CoffeeProduct getProductbyId(@PathVariable Long id)
	{
		return da.getProductById(id);
	}
	
	@PostMapping(consumes="application/json")
	public String postCoffee(@RequestBody CoffeeProduct product) {
		return "http://localhost:8080/products/"+ da.addProduct(product);
	}
	@PutMapping(value="/{id}", consumes="application/json")
	public String updateCoffee(@PathVariable Long id,@RequestBody CoffeeProduct product) {
		
		product.setId(id);
		da.updateProduct(product);
		return "Updated product with ID: "+id;
	}
	@DeleteMapping(value="/{id}")
	public String deleteCoffee(@PathVariable Long id) {
		da.deleteProduct(id);
		return "Deleted product by ID: "+id;
	}
	
	
	


}
