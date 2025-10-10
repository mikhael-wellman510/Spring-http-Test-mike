package geteway.Asyncronus.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.Asyncronus.Dto.BiodataDto;
import geteway.Asyncronus.Dto.FamilyDto;
import geteway.Asyncronus.Dto.HobbyDto;
import geteway.Asyncronus.Dto.ResponsesDto;
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
public class ApiServiceImpl {

	private final AsyncHttpClient client;
	private final ObjectMapper mapper;
	final String url = "http://localhost:8000/getData{num}";

	public CompletableFuture<ResponsesDto>callApiExternalAscyn() throws JsonProcessingException {
		String api1 = url.replace("{num}","1");
		String api2 = url.replace("{num}","2");
		String api3 = url.replace("{num}","3");
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

		CompletableFuture<BiodataDto> future1 =new CompletableFuture<>();
		CompletableFuture<FamilyDto> future2 = new CompletableFuture<>();
		CompletableFuture<HobbyDto> future3 = new CompletableFuture<>();
		client.prepareGet(api1)
				.setHeaders(headers)
				.execute(new AsyncCompletionHandler<Void>() {
					@Override
					public @Nullable Void onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						BiodataDto res = mapper.readValue(response.getResponseBody(), BiodataDto.class);
						future1.complete(res);
						return null;
					}
				}).toCompletableFuture();

		Map<String,Object> data = new HashMap<>();
		data.put("momName" , "jenty");
		data.put("dadName", "sitorus");
		String json = mapper.writeValueAsString(data);
		client.preparePost(api2)
				.setHeaders(headers)
				.setBody(json)
				.execute(new AsyncCompletionHandler<Void>() {
					@Override
					public @Nullable Void onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						FamilyDto res = mapper.readValue(response.getResponseBody() , FamilyDto.class);
						future2.complete(res);
						return null;
					}
				}).toCompletableFuture();

		client.prepareGet(api3)
				.setHeaders(headers)
				.execute(new AsyncCompletionHandler<Void>() {
					@Override
					public @Nullable Void onCompleted(@Nullable Response response) throws Exception {
						assert response != null;
						HobbyDto res =mapper.readValue(response.getResponseBody(),HobbyDto.class);
						future3.complete(res);
						return null;
					}
				}).toCompletableFuture();


		CompletableFuture<ResponsesDto> allData = CompletableFuture.allOf(future1,future2,future3)
				.thenApply(res -> {
					BiodataDto bio = future1.join();
					FamilyDto fam = future2.join();
					HobbyDto hobby = future3.join();

					return ResponsesDto.builder()
							.name(bio.getName())
							.age(bio.getAge())
							.address(bio.getAddress())
							.dadName(fam.getDadName())
							.momName(fam.getMomName())
							.hobby(hobby.getHobby())
							.createdAt(fam.getCreatedAt())
							.build();
				});


		return allData;

	}

	public CompletableFuture<BiodataDto>callApiExternal(){
		String api = url.replace("{num}","1");
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

		CompletableFuture<BiodataDto> future = new CompletableFuture<>();

		client.prepareGet(api)
				.setHeaders(headers)
				.execute(new AsyncCompletionHandler<Void>() {
					@Override
					public @Nullable Void onCompleted(@Nullable Response response) throws Exception {
						BiodataDto res = mapper.readValue(response.getResponseBody(), BiodataDto.class);
						future.complete(res);
						return null;
					}

					@Override
					public void onThrowable(Throwable t) {
						log.error("🚨 Error calling API {}: {}",t.getMessage());

						future.completeExceptionally(t);
					}
				}).toCompletableFuture();

		return future;
	}
}
