package geteway.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class RegisterRequest {

	private String username;
	private String password;
	private String name;
	private String address;
	private Integer age;
	private List<Long>roles;
}
