package geteway.RaceConditionExample.Optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JacketOptimisticController {

	private final JacketOptimisticServiceImpl jacketOptimisticService;

	@PostMapping("/api/restricted/test/{id}")
	public ResponseEntity<?>testOptimistic(@PathVariable Long id) throws InterruptedException {
		jacketOptimisticService.addStock(id);

		return ResponseEntity.ok("Success");
	}
}
