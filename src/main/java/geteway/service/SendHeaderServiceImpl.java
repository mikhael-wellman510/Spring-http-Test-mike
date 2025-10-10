package geteway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.TestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendHeaderServiceImpl {
	private final AsyncHttpClient client;
	private final ObjectMapper mapper;

	public CompletableFuture<TestDto>send2() throws JsonProcessingException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE,"application/json");
		String url = "http://localhost:8000/sendHead";
//
//		Map<String , Object> reqBody = new HashMap<>();
//
//		reqBody.put("nama" , "mikhael");
//		reqBody.put("umur" ,26);

//		String body = mapper.writeValueAsString(reqBody);

		return client.preparePost(url)
				.setHeaders(httpHeaders)
//				.setBody(body)
				.execute(new AsyncCompletionHandler<TestDto>() {

					@Override
					public @Nullable TestDto onCompleted(@Nullable Response response) throws Exception {
						final String res = response.getResponseBody();

						return null;
					}

					@Override
					public void onThrowable(Throwable t) {
						// 🛑 Akan dipanggil kalau ada timeout / koneksi error
						log.error("Request failed: {}", t.getMessage());
						super.onThrowable(t);
					}
				}).toCompletableFuture();

	}

	public CompletableFuture<?> send() throws JsonProcessingException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE , "application/json");
//		httpHeaders.set("kampus", "polimedia");
//		httpHeaders.set("tokenss" , "h2Nok45aa");

		String url = "http://localhost:8000/headers";


		Map<String , Object> reqBody = new HashMap<>();

		reqBody.put("nama" , "mikhael");
		reqBody.put("umur" ,26);

		String body = mapper.writeValueAsString(reqBody);

		return client.preparePost(url)
				.setBody(body)
				.setHeaders(httpHeaders)

				.execute(new AsyncCompletionHandler<String>() {
					@Override
					public @Nullable String onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						int status = response.getStatusCode();

						log.info("Status {} " , status);
						final String body = response.getResponseBody();
						log.info("Body : {} " , body);
						return body;
					}
				}).toCompletableFuture();
	}
}
