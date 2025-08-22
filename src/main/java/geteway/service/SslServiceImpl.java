package geteway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.SportDtos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class SslServiceImpl {
	private final AsyncHttpClient client;

	private final ObjectMapper objectMapper;

	public CompletableFuture<?>testSsl(){

		String url = "https://localhost:8000/testSsl";


		return client.prepareGet(url)
				.execute(new AsyncCompletionHandler<Object>() {
					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						return objectMapper.readValue(response.getResponseBody(), SportDtos.class) ;
					}

					@Override
					public void onThrowable(Throwable t) {
						log.info("Error karena : {} " , t.getMessage());
						super.onThrowable(t);
					}
				}).toCompletableFuture();

	}
}
