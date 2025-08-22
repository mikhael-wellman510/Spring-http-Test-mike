package geteway.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Pokemon {

	private Long count;
	private String next;
	private String previous;
	List<PokemonResult> results;
}
