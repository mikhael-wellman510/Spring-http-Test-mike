package geteway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authTest")
public class AuthTestController {

	@GetMapping("/cek")
	public ResponseEntity<?>testingAuthConst(){

		return ResponseEntity.ok("Mantap");
	}
}
