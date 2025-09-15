package geteway.controller;

import geteway.service.CsvImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restricted")
@RequiredArgsConstructor
public class CsvImportController {

	private final CsvImportService csvImportService;

	@GetMapping("/generatedCsv")
	public void csvImport(){
		csvImportService.importCsv();
	}
}
