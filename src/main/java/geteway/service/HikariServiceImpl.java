package geteway.service;

import geteway.entity.Biodata;
import geteway.repository.BiodataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HikariServiceImpl {

	private final BiodataRepository biodataRepository;


	public void testHikari(){
		Biodata bio = Biodata.builder()
				.name("Mike")
				.address("Bogor")
				.build();
		biodataRepository.delayQuery(5);

	}
}
