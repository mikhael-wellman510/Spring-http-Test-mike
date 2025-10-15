package geteway.RaceConditionExample.Optimistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JacketOptimisticRespository extends JpaRepository<Jacket,Long> {
}
