package geteway.controller;

import geteway.service.DownloadPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class DownloadPdfController {
	private final SSLContext sslContext;
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

	@GetMapping("/getSample")
	public ResponseEntity<?>getPdf2() throws IOException {
		URL pdfUrl =new URL("https://localhost:8000/getPdf");

		HttpsURLConnection connection = (HttpsURLConnection)pdfUrl.openConnection();
		connection.setSSLSocketFactory(sslContext.getSocketFactory());
		InputStream inputStream = connection.getInputStream();

		String contentType = URLConnection.guessContentTypeFromStream(inputStream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);

		headers.setContentDisposition(ContentDisposition.inline().filename("preview.pdf").build());

		return ResponseEntity.ok()
		.headers(headers)
		.body(new InputStreamResource(inputStream));


	}
}
