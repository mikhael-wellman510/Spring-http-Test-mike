package geteway.service.AsyncService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestThreadImpl {

		@Async
		public void test() throws InterruptedException {
			log.info("---- run ----");
			Thread.sleep(3000);
		}
}
