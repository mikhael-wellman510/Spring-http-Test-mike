package geteway.Asyncronus.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import geteway.Asyncronus.Dto.BiodataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

	private final ApiServiceImpl apiService;

	@GetMapping("/api/restricted/data1")
	public ResponseEntity<?>getData() throws ExecutionException, InterruptedException {

		BiodataDto res = apiService.callApiExternal().get();

		return ResponseEntity.ok(res);
	}

	@GetMapping("/api/restricted/data2")
	public ResponseEntity<?>getData2() throws JsonProcessingException {
		long start = System.currentTimeMillis();
		var data = apiService.callApiExternalAscyn().join();
		long end = System.currentTimeMillis();

		log.info("time : {} ms" , end-start);
		return ResponseEntity.ok(data);
	}
}
