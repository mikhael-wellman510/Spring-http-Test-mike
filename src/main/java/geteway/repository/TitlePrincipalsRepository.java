package geteway.repository;

import geteway.entity.TitlePrincipals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitlePrincipalsRepository  extends JpaRepository<TitlePrincipals, Long> {

	Optional<TitlePrincipals>findByNconst(String name);
	Optional<TitlePrincipals>findByCharacters(String name);
	@Query(value = "SELECT * FROM title_principals LIMIT 10000000", nativeQuery = true)
	List<TitlePrincipals> findFirst10Million();

	Optional<TitlePrincipals> findByIdAndCharacters(Long id, String characters);
	Optional<TitlePrincipals> findFirstByCharacters(String name);
	List<TitlePrincipals> findByIdBetween(Long start, Long end);
}
