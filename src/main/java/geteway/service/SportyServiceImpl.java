package geteway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportyServiceImpl {

	private final AsyncHttpClient client;
	private final ObjectMapper mapper;
	private final ExecutorService securityExecutorService;

	public CompletableFuture<?>getSporty(){
		HttpHeaders httpHeaders =new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE ,"application/json");

		String url = "http://localhost:8000/api/v1/sporty";
		

		return client.preparePost(url)
				.setHeaders(httpHeaders)
				.execute(new AsyncCompletionHandler<Object>() {
					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						log.info(String.valueOf(response.getStatusCode()));

						final String res = response.getResponseBody();
						log.info("hasil : {} " , res);
						return res;
					}
				}).toCompletableFuture();
	}

	public CompletableFuture<?>getSporty2(){
		HttpHeaders httpHeaders =new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE ,"application/json");

		String url = "http://localhost:8000/api/v1/sporty";

		return client.preparePost(url)
				.setHeaders(httpHeaders)
				.execute()
				.toCompletableFuture();

	}
}
