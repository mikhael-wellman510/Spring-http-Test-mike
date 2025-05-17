package geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {


	// terhubung dengan Unit Testing Controller
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
