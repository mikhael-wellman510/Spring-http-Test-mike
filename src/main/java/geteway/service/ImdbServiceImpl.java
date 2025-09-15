package geteway.service;

import geteway.entity.TitlePrincipals;
import geteway.repository.TitlePrincipalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImdbServiceImpl {

		private final TitlePrincipalsRepository titlePrincipalsRepository;


		@Async("taskExecutor")
		public CompletableFuture<List<TitlePrincipals>> fetchRange(long startId, long endId) {
			log.info("Fetching range: {} - {}", startId, endId);

			List<TitlePrincipals> data = titlePrincipalsRepository.findByIdBetween(startId, endId);

			return CompletableFuture.completedFuture(data);
		}

		public List<TitlePrincipals>hasilTes(long startId, long endId){
			return titlePrincipalsRepository.findByIdBetween(startId, endId);
		}

		public List<TitlePrincipals>data(){
			Runtime runtime = Runtime.getRuntime(); // paksa garbage collector dulu biar lebih "bersih"
			runtime.gc();

			long usedMemoryBefore = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
			long startTime = System.currentTimeMillis();



			long endTime = System.currentTimeMillis();
			long usedMemoryAfter = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
			List<TitlePrincipals>datas = titlePrincipalsRepository.findFirst10Million();
			// log hasil
			log.info("Used memory before query: {} MB", usedMemoryBefore);
			log.info("Used memory after query : {} MB", usedMemoryAfter);
			log.info("Memory increase        : {} MB", (usedMemoryAfter - usedMemoryBefore));
			log.info("Execution time         : {} ms", (endTime - startTime));
			return datas;
		}

}
