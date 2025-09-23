package geteway.service;

import geteway.entity.Accident;
import geteway.repository.AccidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentServiceImpl {

	private final AccidentRepository accidentRepository;
	private Integer count = 0;
	private AtomicInteger counts = new AtomicInteger(0);

	public Page<Accident>searchByCity(int page,int size , String city){

		Pageable pageable = PageRequest.of(page,size , Sort.by("id").descending());

		Page<Accident> data = accidentRepository.findByCityContainingIgnoreCase(city,pageable);

		List<Accident> resp = new ArrayList<>();
		for (Accident a : data){
			log.info("cek {} " ,a);
			resp.add(a);
		}

		return new PageImpl<>(resp,pageable,data.getTotalElements());

	}

	public Accident accident(String airportCode){
		log.info("***Acc Service****");
		Accident acc = accidentRepository.findByAirportCode(airportCode).orElseThrow(()->new RuntimeException("Not Found"));

		return acc;
	}

	public Accident findCountry(String country ){

		return accidentRepository.findFirstByCountry(country);
	}

	public List<Accident>findTwoCountry(String name){


		counts.incrementAndGet();
		log.info("Count: {} " , count);

		return accidentRepository.findCountry(name);
	}

	public Accident findCountryAcc(String name){


			return accidentRepository.findByCountry(name).orElse(null);

	}


}
