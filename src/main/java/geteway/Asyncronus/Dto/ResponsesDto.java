package geteway.Asyncronus.Dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ResponsesDto {

	private String name;
	private Integer age;
	private String address;
	private String dadName;
	private String momName;
	private List<String>hobby;
	private LocalDateTime createdAt;
}
