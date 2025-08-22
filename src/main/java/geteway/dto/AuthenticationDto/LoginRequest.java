package geteway.dto.AuthenticationDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {

	private String username;
	private String password;
}
