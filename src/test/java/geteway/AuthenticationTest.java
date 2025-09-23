package geteway;

import geteway.dto.AuthenticationDto.RegisterRequest;
import geteway.entity.Authentication.Role;
import geteway.entity.Authentication.Users;
import geteway.repository.AuthenticationRepository.RoleRepository;
import geteway.repository.AuthenticationRepository.UserRepository;
import geteway.service.AuthService.AuthenticationServiceImpl;
import geteway.util.AuthUtils.DecryptUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

@SpringBootTest
public class AuthenticationTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private AuthenticationServiceImpl authenticationService;

	private RegisterRequest registerRequest;

	private Users users;


	@Test
	public void testRegisterAccount(){

		Role role = Role.builder()
				.id(1L)
				.name("ADMIN")
				.build();
		Role role2 = Role.builder()
				.id(2L)
				.name("SUPER_ADMIN")
				.build();

		RegisterRequest reg = new RegisterRequest();
		reg.setUsername("mikhael@gmail.com");
		reg.setRoles(List.of(1L,2L));
		reg.setPassword("+g7mrw1CBA1BeKK1Crz5SYtfmbEz6KEOdA++ngqF65Zl8wJWcVpp");

		Mockito.when(userRepository.findByUsername(reg.getUsername())).thenReturn(Optional.empty());
		Mockito.when(roleRepository.findById(reg.getRoles().get(0))).thenReturn(Optional.of(role));
		Mockito.when(roleRepository.findById(reg.getRoles().get(1))).thenReturn(Optional.of(role2));
		Mockito.when(passwordEncoder.encode("plain")).thenReturn("hashedPass");
		// todo -> jika tidak menampung hasil save nya
		// FIX → save return entity yang dikirim
		Mockito.when(userRepository.save(any(Users.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		String register = authenticationService.registerAccount(reg);

		Assertions.assertNotNull(register);
		Assertions.assertEquals("Succes Create user" , register);
		Mockito.verify(roleRepository, times(1)).findById(1L);
		Mockito.verify(roleRepository,times(1)).findById(2L);
		Mockito.verify(roleRepository,times(2)).findById(anyLong());


	}
}
