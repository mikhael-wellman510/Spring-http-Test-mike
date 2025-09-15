package geteway.controller;

import geteway.entity.TitlePrincipals;
import geteway.service.ImdbServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ImdbController {

	private final ImdbServiceImpl imdbService;
	@GetMapping("/test12")
	public ResponseEntity<?> test22() {

		var data = imdbService.hasilTes(0,10000000);

		return ResponseEntity.ok(data.size());
	}
	@GetMapping("/test11")
	public ResponseEntity<?> test() {
		int threads = 10;          // jumlah thread
		long chunkSize = 1000000;  // masing-masing ambil 500 ribu

		List<CompletableFuture<List<TitlePrincipals>>> futures = new ArrayList<>();

		for (int i = 0; i < threads; i++) {
			long start = i * chunkSize + 1;
			long end = (i + 1) * chunkSize;
			futures.add(imdbService.fetchRange(start, end));
		}

		// Tunggu semua thread selesai
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

		// Gabungkan hasil
		List<TitlePrincipals> result = futures.stream()
				.map(CompletableFuture::join) // ambil hasil dari tiap future
				.flatMap(List::stream)       // gabungkan semua list
				.toList();


		return ResponseEntity.ok(result.size());
	}

//	@GetMapping("/test")
//	public ResponseEntity<?>testControler() throws InterruptedException {
//
//		List<CompletableFuture<List<TitlePrincipals>>> futures = new ArrayList<>();
//
//		for (int i = 0 ; i <  100 ; i++) {
//
//		   futures.add(imdbService.testThread(i));
//		}
//
//
//		return ResponseEntity.ok("ok");
//
//	}
//
//	@GetMapping("/test2")
//	public ResponseEntity<?>testControler2() throws InterruptedException {
//
//		var datas = imdbService.data();
//
//		return ResponseEntity.ok(datas);
//
//	}
}
