package geteway.RaceConditionExample.Optimistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jacket")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Jacket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameJacket;
	private Integer stock;

	@Version
	private Long version;
}
