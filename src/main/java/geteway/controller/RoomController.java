package geteway.controller;

import geteway.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class RoomController {

	private final RoomServiceImpl roomService;

	@GetMapping("/book/{id}")
	public ResponseEntity<?>bookRoom(@PathVariable Long id){

		roomService.bookingRoom(id);

		return ResponseEntity.ok("success");
	}
}
