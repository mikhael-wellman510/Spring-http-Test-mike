package geteway.repository;

import geteway.entity.ProductOptimistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRaceRepository extends JpaRepository<ProductOptimistic, Long> {
}
