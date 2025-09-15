package geteway.service;

import geteway.entity.BankDataSet;
import geteway.repository.BankDataSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankDataSetService {

	private final BankDataSetRepository bankDataSetRepository;

	public List<BankDataSet>findByDate(LocalDate date){

		return bankDataSetRepository.findByCreatedAt(date);

	}
}
