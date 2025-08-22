package geteway.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostConstructServiceImpl {

	@PostConstruct
	public void running(){
		log.info("Running");
	}

}
