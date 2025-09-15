package geteway.controller;

import geteway.service.BankDataSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class BankDataSetController {

	private final BankDataSetService bankDataSetService;

	@GetMapping("/date")
	public ResponseEntity<?>findByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date){

		return ResponseEntity.ok(bankDataSetService.findByDate(date));
	}
}
