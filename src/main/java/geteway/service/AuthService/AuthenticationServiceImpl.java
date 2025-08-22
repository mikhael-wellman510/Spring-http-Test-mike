package geteway.service.AuthService;

import geteway.dto.AuthenticationDto.RegisterRequest;
import geteway.entity.Authentication.Role;
import geteway.entity.Authentication.Users;
import geteway.repository.AuthenticationRepository.RoleRepository;
import geteway.repository.AuthenticationRepository.UserRepository;
import geteway.util.AuthUtils.DecryptUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;


	public String registerAccount(RegisterRequest registerRequest){

		try {
			Optional<Users> user = userRepository.findByUsername(registerRequest.getUsername());

			if(user.isPresent()){
				throw new RuntimeException("User Sudah digunakan , Silahkan cari nama lain");
			}

			// Todo -> cek apakah sudah terdaftar user dan role
			Set<Role> roles = registerRequest.getRoles().stream().map(id -> {

				return roleRepository.findById(id).orElseThrow(()->new RuntimeException("Roles Not found"));
			}).collect(Collectors.toSet());

			String passDecrypt = DecryptUtils.decrypt(registerRequest.getPassword());
			log.info("decrypt : {} " , passDecrypt);
			Users saveUser = Users.builder()
					.username(registerRequest.getUsername())
					.password(passwordEncoder.encode(passDecrypt))
					.name(registerRequest.getName())
					.address(registerRequest.getAddress())
					.age(registerRequest.getAge())
					.roles(roles)
					.build();

			userRepository.save(saveUser);


			return "Succes Create user";
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

	}
}
