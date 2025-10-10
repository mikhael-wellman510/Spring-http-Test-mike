package geteway.Asyncronus.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder(toBuilder = true)
@ToString
public class HobbyDto {

	private List<String> hobby;
}
