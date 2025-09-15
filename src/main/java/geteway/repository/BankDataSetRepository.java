package geteway.repository;

import geteway.entity.BankDataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BankDataSetRepository extends JpaRepository<BankDataSet,Long> {
	List<BankDataSet>findByCreatedAt(LocalDate date);
}
