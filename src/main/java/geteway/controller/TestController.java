package geteway.controller;

import geteway.entity.Authentication.UserPrincipal;
import geteway.entity.Authentication.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

	@GetMapping("/testAuth")
	public void testAuth(Authentication authentication){
		UserPrincipal up = (UserPrincipal)authentication.getPrincipal();
		log.info("Up : {} " , up);
		log.info("Succes !");
	}

	@GetMapping("/superAdmin")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public void testAdmin(){
		log.info("Welcome super admin");
	}

	@GetMapping("/direktur")
	@PreAuthorize("hasRole('DIREKTUR')")
	public void testAdmin2(){
		log.info("Welcome super admin");
	}
}
