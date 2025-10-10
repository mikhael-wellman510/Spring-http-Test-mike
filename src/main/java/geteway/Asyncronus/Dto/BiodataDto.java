package geteway.Asyncronus.Dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class BiodataDto {

	private String name;
	private Integer age;
	private String address;
}
