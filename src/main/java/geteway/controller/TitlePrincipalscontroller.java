package geteway.controller;

import geteway.service.TitlePrincipalsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class TitlePrincipalscontroller {

	private final TitlePrincipalsServiceImpl titlePrincipalsService;

	@GetMapping("/character/{name}")
	public ResponseEntity<?>findByCharacter(@PathVariable String name){

		return ResponseEntity.ok(titlePrincipalsService.findByCharacters(name));
	}

	@GetMapping("/character1/{name}")
	public ResponseEntity<?>findByCharacter2(@PathVariable String name){

		return ResponseEntity.ok(titlePrincipalsService.findFirstByCharacter(name));
	}

	@GetMapping("/principals/{name}")
	public ResponseEntity<?>findById(@PathVariable String name){

		return ResponseEntity.ok(titlePrincipalsService.findById(name));
	}

	@PutMapping("/principal")
	public ResponseEntity<?>Updated(@RequestParam(name = "name" , defaultValue = "")String name){

		return ResponseEntity.ok(titlePrincipalsService.updatedByCharacter(name));
	}

	@GetMapping("/testAja")
	public String testing(){
		return "Yuhu";
	}

	@GetMapping("/findCok")
	public ResponseEntity<?>findByIdAndCharacters(@RequestParam(name = "id") Long id , @RequestParam(name = "characters")String characters , @RequestParam(name = "type")Integer type){

		var res = titlePrincipalsService.findByIdAndCharacters(id , characters , type);

		return ResponseEntity.ok(res);
	}
}
