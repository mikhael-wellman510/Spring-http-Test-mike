package geteway.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CekDeviceController {


	@GetMapping("/api/restricted/cekDevice")
	public ResponseEntity<?>cekDevice(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		log.info("User agent : {} " , userAgent);
		if(userAgent == null){
			log.info("unknown device");
		}

		return ResponseEntity.ok("Succes");
	}
}
