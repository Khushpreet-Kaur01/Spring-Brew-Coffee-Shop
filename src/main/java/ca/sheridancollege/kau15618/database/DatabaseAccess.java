package ca.sheridancollege.kau15618.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.kau15618.beans.CoffeeProduct;



@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public List<CoffeeProduct> getAllProducts() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM coffee";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<CoffeeProduct>(CoffeeProduct.class));
	}

	public CoffeeProduct getProductById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM coffee WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.queryForObject(query, namedParameters,new BeanPropertyRowMapper<CoffeeProduct>(CoffeeProduct.class));
	}

	public Long addProduct(CoffeeProduct product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO coffee(name,description,price) VALUES(:name,:description,:price)";
		namedParameters.addValue("name",( product).getName() );
		namedParameters.addValue("description",product.getDescription());
		namedParameters.addValue("price", product.getPrice());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey().longValue();
	}

	public void deleteProduct(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    String query = "DELETE FROM coffee WHERE id = :id";
	    namedParameters.addValue("id", id);
	    jdbc.update(query, namedParameters);
		
	}
	public int updateProduct(CoffeeProduct product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="Update coffee set name=:name, description=:description, price=:price where id=:id";
		namedParameters.addValue("name",product.getName());
	    namedParameters.addValue("description", product.getDescription());
	    namedParameters.addValue("price", product.getPrice());
	    namedParameters.addValue("id", product.getId());
	    return jdbc.update(query, namedParameters);
	}

}
