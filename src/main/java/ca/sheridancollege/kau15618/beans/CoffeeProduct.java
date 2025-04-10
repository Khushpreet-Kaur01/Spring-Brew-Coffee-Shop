package ca.sheridancollege.kau15618.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CoffeeProduct {
	private Long id;
	private String name;
	private String description;
	private Double price;

}
