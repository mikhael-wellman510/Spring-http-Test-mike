package geteway.service.AuthService;

import geteway.entity.Authentication.UserPrincipal;
import geteway.entity.Authentication.Users;
import geteway.repository.AuthenticationRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users users = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Username Not found"));

		UserPrincipal up = new UserPrincipal();
		up.setId(users.getId());
		up.setUsername(users.getUsername());
		up.setPassword(users.getPassword());
		up.setName(users.getName());
		up.setAddress(users.getAddress());
		up.setAge(users.getAge());
		up.setRoles(users.getRoles());
		log.info("Simpan data ke users principals !");
		return up;
	}
}
