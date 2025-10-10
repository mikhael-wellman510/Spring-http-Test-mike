package geteway.Asyncronus.Async;

import geteway.Asyncronus.Dto.ResponsesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncController {

	private final AsyncServiceImpl asyncService;

	@GetMapping("/api/restricted/asyncEx")
	public ResponseEntity<?>callAsync() throws InterruptedException {

		asyncService.asyncFunc();

		return ResponseEntity.ok("Succes Call Api");
	}

	@GetMapping("/api/restricted/asyncFunc")
	public ResponseEntity<?>callCompletableAsync(){
		log.info("Start ----");
		long start = System.currentTimeMillis();
		ResponsesDto res = asyncService.testAsync().join();
		long end = System.currentTimeMillis();
		log.info("End ---");
		log.info("Time : {} ms " , (end-start));
		return ResponseEntity.ok(res);
	}

	@GetMapping("/api/restricted/callEsb")
	public ResponseEntity<?>callDevx() throws InterruptedException {

		asyncService.asyncInternalCallEsb().join();

		return ResponseEntity.ok("Success!");
	}


}
