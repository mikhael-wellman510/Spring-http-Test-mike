package geteway.controller.AsyncController;

import geteway.service.AsyncService.TestThreadImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestThreadController {

	private final TestThreadImpl testThread;

	@GetMapping("/test")
	public void testingController() throws InterruptedException {
		for (int i = 0 ; i < 10 ; i++){
			testThread.test();
		}

	}
}
