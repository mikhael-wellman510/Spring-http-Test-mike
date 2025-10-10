package geteway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class SportyCekGateway {


	@GetMapping("/ceks")
	public void panggil(){

		log.info("kepanggil!!!");
	}

}
