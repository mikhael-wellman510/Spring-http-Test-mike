package geteway.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.TestDto;
import geteway.service.SendHeaderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class SendHeaderController {

	private final AsyncHttpClient client;
	private final ObjectMapper mapper;
	private final SendHeaderServiceImpl sendHeaderService;

	@PostMapping("/sport")
	public CompletableFuture<ResponseEntity<TestDto>>sendSport() throws JsonProcessingException {

		return sendHeaderService.send2()
				.thenApply(res -> {
					log.info("Cekk !!!");
					return ResponseEntity.ok(res);
				}).exceptionally(ex -> {
					return ResponseEntity.internalServerError().build();
				});
	}

	@PostMapping("/sends")
	public void testt(){
		log.info("Cek!!!1");
		String url = "http://localhost:8000/cek1";
		client.preparePost(url)
				.execute(new AsyncCompletionHandler<Object>() {
					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						log.info("succes");
						return null;
					}
				}).toCompletableFuture();
	}

	@PostMapping("/sendHeaders")
	public CompletableFuture<?> sendHeader() throws JsonProcessingException {
		log.info("Cek ");
		return sendHeaderService.send()
				.thenApply(res -> {
					log.info("Con : {} " , res);
					return ResponseEntity.ok(res.toString());
				})
				.exceptionally(ex -> {
					log.info("Ex : {} " , ex.getMessage());
					return ResponseEntity.internalServerError().build();
				});

	}





}
