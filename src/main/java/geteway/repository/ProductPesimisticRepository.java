package geteway.repository;

import geteway.entity.ProductPesimistic;
import jakarta.persistence.LockModeType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPesimisticRepository extends JpaRepository<ProductPesimistic , Long> {


    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @NotNull
    Optional<ProductPesimistic> findById(@NotNull Long id);
}
