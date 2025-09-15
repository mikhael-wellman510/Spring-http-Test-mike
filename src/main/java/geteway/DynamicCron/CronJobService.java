package geteway.DynamicCron;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.ZoneId;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CronJobService implements SchedulingConfigurer {
	private final  TestService testService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		String time = "0 38 11-15 * * *";

		taskRegistrar.addTriggerTask(
				()->testService.testings(),
				triggerContext -> {
					CronTrigger trigger = new CronTrigger(time, ZoneId.of("Asia/Jakarta"));
					return trigger.nextExecutionTime(triggerContext).toInstant();
				}
		);
	}
}
