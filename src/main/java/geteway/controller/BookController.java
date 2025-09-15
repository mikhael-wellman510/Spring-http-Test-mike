package geteway.controller;

import geteway.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class BookController {

	private final BookServiceImpl bookService;

	@GetMapping("/borrow/{id}")
	public ResponseEntity<?>borrowBook(@PathVariable Long id) throws InterruptedException {
		bookService.borrowBook(id);

		return ResponseEntity.ok("Success!!");
	}
}
