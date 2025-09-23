package geteway.service;

import geteway.RaceConditionExample.Product;
import geteway.RaceConditionExample.ProductRepository;
import geteway.entity.Book;
import geteway.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalTestService {

	private final BookRepository bookRepository;
	private final ProductRepository productRepository;

	@Transactional
	public Map<String ,Object>testTransactional(){

		Book save = bookRepository.save(Book.builder()
						.bookName("Malaka222")
						.qty(100)
				.build());

		log.info("Tersimpan");

		String id = "dwdw";
		Product prod = productRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));

		return null;
	}

}
