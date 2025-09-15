package geteway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamsController {

	@GetMapping("/pr")
	public ResponseEntity<?>testings(@RequestParam String name){


		return ResponseEntity.ok(name);
	}
}
