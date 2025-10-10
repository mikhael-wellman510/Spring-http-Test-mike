package geteway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class TestDto {

	private String nama;
	private Integer umur;
	private String status;
}
