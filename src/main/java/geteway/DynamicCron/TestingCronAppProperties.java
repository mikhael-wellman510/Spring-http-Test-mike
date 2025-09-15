package geteway.DynamicCron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class TestingCronAppProperties {

	@Scheduled()
	public void jalan(){
		log.info("Jalam woid");
	}
}
