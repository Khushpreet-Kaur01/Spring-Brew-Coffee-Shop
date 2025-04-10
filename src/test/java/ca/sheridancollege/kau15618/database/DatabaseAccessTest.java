package ca.sheridancollege.kau15618.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.kau15618.beans.CoffeeProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseAccessTest {
	@Autowired
	private DatabaseAccess da;

	@Test
	public void InsertionOfProduct() {
		int Size = da.getAllProducts().size();

		CoffeeProduct product = new CoffeeProduct();
		product.setName("Latte");
		product.setDescription("Coffee");
		product.setPrice(4.99);
		da.addProduct(product);
//		assertNotNull(id);

		assertEquals(++Size , da.getAllProducts().size());

	}


}
