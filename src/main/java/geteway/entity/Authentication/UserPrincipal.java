package geteway.entity.Authentication;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserPrincipal implements UserDetails {

	private Long id;
	private String username;
	private String password;
	private String name;
	private String address;
	private Integer age;
	private Set<Role> roles;
	private String jwtToken;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
				.collect(Collectors.toSet());

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
}
