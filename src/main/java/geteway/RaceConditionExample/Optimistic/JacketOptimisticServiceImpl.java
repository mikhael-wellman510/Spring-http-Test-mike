package geteway.RaceConditionExample.Optimistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JacketOptimisticServiceImpl {

		private final JacketOptimisticRespository jacketOptimisticRespository;

		@Transactional
		public void addStock(Long id) throws InterruptedException {

			Jacket jacket = jacketOptimisticRespository.findById(id).orElse(null);

			if(jacket == null){
				throw new RuntimeException("Not Found");
			}
			log.info("jacket version : {} " , jacket.getVersion());
			Thread.sleep(4000);

			jacket.setStock(jacket.getStock() + 1);
			Jacket save = jacketOptimisticRespository.save(jacket);
			log.info("Success save data with version : {} " , save.getVersion());
		}
}
