package geteway.controller;

import geteway.service.AccidentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class AccidentController {

	private final AccidentServiceImpl accidentService;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
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
