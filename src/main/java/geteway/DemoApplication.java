package geteway;

import com.example.MikhaelLib.utils.HelloUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
//@EnableElasticsearchRepositories(basePackages = "geteway.repository")
//@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class DemoApplication {


	// terhubung dengan Unit Testing Controller
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		log.info("Cek : {} " , HelloUtils.hello("Mikhael"));
	}

}
