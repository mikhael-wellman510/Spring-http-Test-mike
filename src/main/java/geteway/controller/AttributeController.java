package geteway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class AttributeController {

	private final ObjectMapper mapper;

	@PostMapping("/setAttribute")
	public void setAttribute(HttpServletRequest request){
		log.info("Controller attribute");
		request.setAttribute("consumer" ,  "TestConsumer");


		String attribute = (String)request.getAttribute("consumer");

		log.info("att : {} " , attribute);

	}

	@GetMapping("/getAttribute")
	public void getAttribute(HttpServletRequest request){

		String attribute = (String)request.getAttribute("consumer");

		log.info("attribute : {} " , attribute);
	}

}
