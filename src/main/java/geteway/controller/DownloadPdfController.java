package geteway.controller;

import geteway.service.DownloadPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class DownloadPdf {

	private final DownloadPdfService downloadPdf;

	@GetMapping("/getPdf")
	public CompletableFuture<?> getPdf(){

		return downloadPdf.getPdf()
				.thenApply(res-> {

					return ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_PDF)
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"contoh.pdf\"")
							.body(res);
				})
				.exceptionally(ex -> {
					return ResponseEntity.internalServerError().build();
				});
	}
}
