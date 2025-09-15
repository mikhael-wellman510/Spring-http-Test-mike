package geteway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/paralel")
public class ParalelController {

	private final AsyncHttpClient client;

	@GetMapping("")
	public ResponseEntity<?>paralelControlelr() throws ExecutionException, InterruptedException {

		String url1 = "http://localhost:8000/test1";
		String url2 = "http://localhost:8000/test2";
		String url3 = "http://localhost:8000/test3";
		CompletableFuture<?> data1 = client.prepareGet(url1)
				.execute(new AsyncCompletionHandler<Object>() {

					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						return response.getResponseBody();
					}
				}).toCompletableFuture();

		CompletableFuture<?> data2 = client.prepareGet(url2)
				.execute(new AsyncCompletionHandler<Object>() {

					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						return response.getResponseBody();
					}
				}).toCompletableFuture();

		CompletableFuture<?> data3 = client.prepareGet(url3)
				.execute(new AsyncCompletionHandler<Object>() {

					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						return response.getResponseBody();
					}
				}).toCompletableFuture();


		CompletableFuture.allOf(data1,data2,data3).join();

		log.info("Data 1 : {}  , data 2 : {} , data 3 {} " , data1.get() , data2.get() ,data3.get());
		return ResponseEntity.ok("s");


	}


}
