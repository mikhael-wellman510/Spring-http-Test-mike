package geteway.service;

import geteway.entity.Accident;
import geteway.repository.AccidentRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvImportService {

	private final AccidentRepository accidentRepository;
	private final EntityManager entityManager;
	private static final long RESUME_FROM_ROW = 5500000L;

	@Transactional
	public void importCsv(){

		String filePath = "C:\\Users\\Mikhael Wellman\\Documents\\MICROSERVICE\\gateway\\src\\main\\resources\\US_Accidents_March23.csv";
		int batchSize = 100000;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			List<Accident> batch = new ArrayList<>();
			long totalCount = 0;
			long currentRow = 0;
			br.readLine();

			while ((line = br.readLine()) != null) {
				currentRow++;

				if (currentRow <= RESUME_FROM_ROW) {
					continue;
				}

				// Split berdasarkan koma (,)
				String[] values = line.split(",");

				Accident accident = Accident.builder().build();

				accident.setDescription(values[10]);
				accident.setStreet(values[11]);
				accident.setCity(values[12]);
				accident.setCounty(values[13]);
				accident.setState(values[14]);
				accident.setCountry(values[16]);
				accident.setAirportCode(values[18]);
				accident.setWindDirection(values[25]);
				accident.setWeatherCondition(values[28]);

				batch.add(accident);

				if (batch.size() == batchSize) {
					accidentRepository.saveAll(batch);
					entityManager.flush();   // kirim ke DB
					entityManager.clear();
					batch.clear();
					totalCount += batchSize;
					log.info("Inserted {} rows so far...", totalCount);
				}
			}

			if (!batch.isEmpty()) {
				accidentRepository.saveAll(batch);
				entityManager.flush();
				entityManager.clear();
				totalCount += batch.size();
			}

			log.info("CSV import selesai! Total {} rows.", totalCount);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
