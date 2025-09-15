package geteway.DynamicCron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

	@Scheduled(cron = "${scheduler.job}")
	public void testings(){
		log.info("Schedule jalan !!!");
	}

}
