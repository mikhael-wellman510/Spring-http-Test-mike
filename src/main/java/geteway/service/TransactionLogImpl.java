package geteway.service;

import geteway.dto.TransactionDto;
import geteway.entity.Authentication.UserPrincipal;
import geteway.entity.Authentication.Users;
import geteway.entity.TransactionLog;
import geteway.repository.AuthenticationRepository.UserRepository;
import geteway.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionLogImpl {

	private final TransactionLogRepository transactionLogRepository;
	private final UserRepository userRepository;
	private final LogServiceImpl logService;
	@Transactional
	public TransactionLog saveTx(UserPrincipal up, TransactionDto transactionDto){
		String status = null;
		Users users = null;

		try {
			// Todo -> cari nama user
			Users user = userRepository.findById(transactionDto.getTo()).orElseThrow(()-> new RuntimeException("User tidak ditemukan"));
			Users me = userRepository.findById(up.getId()).orElseThrow(()->new RuntimeException("not"));

			me.setBalance(me.getBalance() - transactionDto.getAmount());

			user.setBalance((user.getBalance() == null ? 0 : user.getBalance()) - transactionDto.getAmount());
			saveTransaction(user);
			status = "Success";
			users = user;
			return logService.saveLog(up , users , transactionDto , status );

		} catch (Exception e) {
			status = "Failed";
			logService.saveLog(up , users , transactionDto , status );
			throw e;
		}


	}


	public void saveTransaction(Users users){

		userRepository.save(users);
	}


}
