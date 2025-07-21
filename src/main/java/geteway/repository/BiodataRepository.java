package geteway.repository;

import geteway.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Long> , JpaSpecificationExecutor<Biodata> {

    void deleteByBatchTimeBefore(LocalDateTime cutoff);
}
