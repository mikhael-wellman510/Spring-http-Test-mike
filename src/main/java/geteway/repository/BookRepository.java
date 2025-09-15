package geteway.repository;

import geteway.RaceConditionExample.Product;
import geteway.entity.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Book> findById(Long id);
}
