package geteway.service;

import geteway.dto.TransactionDto;
import geteway.entity.Authentication.UserPrincipal;
import geteway.entity.Authentication.Users;
import geteway.entity.TransactionLog;
import geteway.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogServiceImpl {

	private final TransactionLogRepository transactionLogRepository;


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TransactionLog saveLog(UserPrincipal up , Users user , TransactionDto transactionDto , String status){
		log.info("Status : {} " , status);

		TransactionLog tl = TransactionLog.builder()
				.fromAccount(up.getUsername())
				.toAccount(user == null ? "Usernot found" : user.getUsername())
				.amount(transactionDto.getAmount())
				.status(status)
				.build();
		return transactionLogRepository.save(tl);
	}
}
