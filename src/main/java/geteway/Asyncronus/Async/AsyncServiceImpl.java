package geteway.Asyncronus.Async;

import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.Asyncronus.Dto.BiodataDto;
import geteway.Asyncronus.Dto.HobbyDto;
import geteway.Asyncronus.Dto.ResponsesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl {

	private final AsyncHttpClient client;
	private final ObjectMapper mapper;

	@Async
	public void asyncFunc() throws InterruptedException {

		Thread.sleep(3000);

		log.info("Finish");
	}


	@Async
	public CompletableFuture<ResponsesDto>testAsync(){
		String url = "http://localhost:8000/getData1";
		String url2 = "http://localhost:8000/getData3";
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

		CompletableFuture<BiodataDto> futureBio = new CompletableFuture<>();
		CompletableFuture<HobbyDto> hobbyBio = new CompletableFuture<>();

		client.prepareGet(url)
				.setHeaders(httpHeaders)
				.execute(new AsyncCompletionHandler<BiodataDto>() {
					@Override
					public @Nullable BiodataDto onCompleted(@Nullable Response response) throws Exception {

						assert response != null;
						BiodataDto bio = mapper.readValue(response.getResponseBody(), BiodataDto.class);
						futureBio.complete(bio);
						return null;
					}
				}).toCompletableFuture();

		client.prepareGet(url2)
				.setHeaders(httpHeaders)
				.execute(new AsyncCompletionHandler<HobbyDto>() {
					@Override
					public @Nullable HobbyDto onCompleted(@Nullable Response response) throws Exception {

						assert response != null;
						HobbyDto hobby = mapper.readValue(response.getResponseBody() ,HobbyDto.class);
						hobbyBio.complete(hobby);
						return null;
					}
				}).toCompletableFuture();

		CompletableFuture<ResponsesDto>allData =CompletableFuture.allOf(futureBio,hobbyBio)
				.thenApply(res -> {

					BiodataDto biodataDto = futureBio.join();
					HobbyDto hobby = hobbyBio.join();

					return ResponsesDto.builder()
							.name(biodataDto.getName())
							.age(biodataDto.getAge())
							.address(biodataDto.getAddress())
							.hobby(hobby.getHobby())
							.build();
				});

		return allData;

	}

	@Async
	public CompletableFuture<Void> asyncInternalCallEsb() throws InterruptedException {

		log.info("Start");
		CompletableFuture<?> tes = CompletableFuture.runAsync(()->{
			try {
				sendEmail();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		CompletableFuture<?>tes2 = CompletableFuture.runAsync(()->{
			try {
				sendEmail2();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		CompletableFuture.allOf(tes,tes2).join();
		log.info("Finish");
		return CompletableFuture.completedFuture(null);
	}

	public void sendEmail() throws InterruptedException {
		Thread.sleep(4000);
		log.info("Message send Success");
	}

	public void sendEmail2() throws InterruptedException {
		Thread.sleep(5000);
		log.info("Success 2");
	}
}
