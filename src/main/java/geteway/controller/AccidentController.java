package geteway.controller;

import geteway.service.AccidentServiceImpl;
import geteway.service.email.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class AccidentController {

	private final AccidentServiceImpl accidentService;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private final SendEmailService sendEmailService;

	@PostMapping("/sendEmail")
	public ResponseEntity<?>send(){

		var send = sendEmailService.sendEmail();

		return ResponseEntity.ok("ok");
	}

	@GetMapping("/loading")
	public void testLoading(){

		accidentService.loadingSimulation();
	}

	@GetMapping("/city")
	public ResponseEntity<?>searchByCity(@RequestParam(name = "city",defaultValue = "")String city , @RequestParam(name = "page" ,defaultValue = "0")int page , @RequestParam(name = "size" , defaultValue = "5")int size){
		log.info("Run : {} " , city);
		var res = accidentService.searchByCity(page, size, city);

		return ResponseEntity.ok(res);

	}



	@GetMapping("/acc")
	public ResponseEntity<?>findByAirport(@RequestParam(name = "airport_code")String airportCode){
		log.info("****Acc controller***");
		var data = accidentService.accident(airportCode);

		return ResponseEntity.ok(data);
	}



	@GetMapping("/country")
	public ResponseEntity<?>findByCountry(@RequestParam(name = "name")String name){

		var data = accidentService.findTwoCountry(name);

		return ResponseEntity.ok(data);
	}

	@GetMapping("/country2")
	public ResponseEntity<?>findByCountrys(@RequestParam(name = "name")String name){

		var data =accidentService.findCountryAcc(name);
		int count = atomicInteger.incrementAndGet();
		log.info("Count : {} " , count);

		return ResponseEntity.ok(data);
	}

}
