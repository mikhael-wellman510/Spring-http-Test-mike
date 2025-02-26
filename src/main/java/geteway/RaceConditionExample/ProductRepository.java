package geteway.RaceConditionExample;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(String id);

}
