package geteway.controller;

import geteway.dto.TransactionDto;
import geteway.entity.Authentication.UserPrincipal;
import geteway.service.TransactionLogImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restricted")
public class TransactionLogController {

	private final TransactionLogImpl transactionLog;

	@PostMapping("/saveLog")
	public ResponseEntity<?>tx(@RequestBody TransactionDto transactionDto , Authentication authentication){
		UserPrincipal up = (UserPrincipal)authentication.getPrincipal();
		var data = transactionLog.saveTx(up,transactionDto);

		return ResponseEntity.ok(data);
	}
}
