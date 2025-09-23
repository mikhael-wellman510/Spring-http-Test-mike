package geteway.controller;

import geteway.service.TransactionalTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionalTestController {

	private final TransactionalTestService transactionalTestService;

	@GetMapping("/test111")
	public void testController(){
		transactionalTestService.testTransactional();
	}

}
