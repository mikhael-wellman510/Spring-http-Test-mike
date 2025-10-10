package geteway.Asyncronus.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@ToString
public class FamilyDto {

	private String momName;
	private String dadName;
	private LocalDateTime createdAt;
}
