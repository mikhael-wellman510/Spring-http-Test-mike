package geteway.controller;

import geteway.Redis.Dto.ProfileDto;
import geteway.service.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisController {

	private final RedisServiceImpl redisService;

	@PostMapping("/redisSave")
	public ResponseEntity<?>saveRedis(@RequestBody ProfileDto profileDto){

		var data = redisService.saveData(profileDto);

		return ResponseEntity.ok(data);
	}

	@GetMapping("/findRedis/{id}")
	public ResponseEntity<?>findRedis(@PathVariable Long id) throws IOException {
		var data = redisService.findById(id);

		return ResponseEntity.ok(data);
	}

	@PutMapping("/updatedRedis")
	public ResponseEntity<?>updatedd(@RequestBody ProfileDto profileDto){

		var data = redisService.updatedProfile(profileDto);

		return ResponseEntity.ok(data);
	}

	@GetMapping("/pokemon/{id}")
	public CompletableFuture<?>pokemon(@PathVariable Long id){

		return redisService.pokemon2(id)
				.thenApply(res -> {
					return ResponseEntity.ok(res);
				}).exceptionally(ex -> {
					log.error("err : {} " , ex.getMessage());
					return ResponseEntity.internalServerError().build();
				});
	}
}
