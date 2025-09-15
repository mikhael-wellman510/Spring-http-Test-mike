package geteway.service;

import geteway.entity.Book;
import geteway.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl {

	private final BookRepository bookRepository;

	// Todo -> Pesimistic
	// Dia akan lock satu persatu , tidak bisa masuk ke db secara bersamaan
	@Transactional
	public void borrowBook(Long id) throws InterruptedException {
		Book book = bookRepository.findById(id).orElse(null);

		assert book != null;
		book.setQty(book.getQty() - 1);

		bookRepository.save(book);
		Thread.sleep(3000);

		log.info("Success save ---");
	}
}
