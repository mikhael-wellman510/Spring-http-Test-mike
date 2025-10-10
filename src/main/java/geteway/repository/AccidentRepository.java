package geteway.repository;

import geteway.entity.Accident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {
	Optional<Accident>findByAirportCode(String airportCode);
	Accident findFirstByCountry(String country);
	Optional<Accident>findByCountry(String country);

	@Query(value = "select * from accident where country = :name limit 2", nativeQuery = true)
	List<Accident>findCountry(String name);

	Page<Accident> findByCityContainingIgnoreCase(String city , Pageable pageable);

	@Query(value = "select count(*) from accident" , nativeQuery = true)
	Long countAllAccident();
}
