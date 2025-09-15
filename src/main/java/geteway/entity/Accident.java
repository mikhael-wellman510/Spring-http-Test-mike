package geteway.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "accident" , indexes = {
		@Index(name = "idx_accident_airport_code", columnList = "airport_code")
})
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Accident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(columnDefinition = "TEXT")
	private String description;

	private String street;

	private String city;
	private String county;
	private String state;
	private String country;

	@Column(name = "airport_code")
	private String airportCode;

	@Column(name = "wind_direction")
	private String windDirection;

	@Column(name = "weather_condition")
	private String weatherCondition;
}
