package geteway.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.Redis.Dto.ProfileDto;
import geteway.Redis.Profile;
import geteway.Redis.Repository.ProfileRepository;
import geteway.dto.Pokemon;
import geteway.dto.ProfileRequest;
import geteway.dto.ProfileResponse;
import geteway.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl {

	private final ProfileRepository profileRepository;
	private final ObjectMapper objectMapper;
	private static final String USER_KEY_PREFIX = "profile";
	private final RedisUtils redisUtils;



	public ProfileDto saveData(ProfileDto profileDto){

		Profile save = profileRepository.save(Profile.builder()
						.name(profileDto.getName())
						.nip(profileDto.getNip())
						.createdAt(LocalDateTime.now())
				.build());

		return ProfileDto.builder()
				.id(save.getId())
				.name(save.getName())
				.nip(save.getNip())
				.createdAt(save.getCreatedAt())
				.build();
	}

	public ProfileDto findById(Long id) throws IOException {

		if(redisUtils.hashKey(parseKey(id))){
			log.info("---- Data dari Cache Redis ---");
			Object data = redisUtils.get(parseKey(id));
			ProfileDto res = objectMapper.convertValue(data, ProfileDto.class);

			log.info("Hasil data : {} " , data);
			log.info("Hasil res : {} " , res);

			return res;
		}
		log.info("---- Query Ke DB ----- ");
		Profile find = profileRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
		redisUtils.set(parseKey(find.getId()) , find , 30);

		return ProfileDto.builder()
				.id(find.getId())
				.name(find.getName())
				.nip(find.getNip())
				.createdAt(find.getCreatedAt())
				.build();

	}


	public ProfileDto updatedProfile(ProfileDto profileDto){

		Profile find = profileRepository.findById(profileDto.getId()).orElseThrow(()->new RuntimeException("Not Found"));

		find.setNip(profileDto.getNip());
		find.setName(profileDto.getName());
		find.setCreatedAt(LocalDateTime.now());

		Profile updated = profileRepository.save(find);

		redisUtils.deleted(parseKey(updated.getId()));
		redisUtils.set(parseKey(updated.getId()), updated , 50);

		return ProfileDto.builder()
				.id(updated.getId())
				.name(updated.getName())
				.nip(updated.getNip())
				.createdAt(updated.getCreatedAt())
				.build();
	}

	public String parseKey(Long id){

		return USER_KEY_PREFIX + id;
	}


	public CompletableFuture<?>pokemon2(Long limit){
		AsyncHttpClient client = Dsl.asyncHttpClient();

		String url = "https://pokeapi.co/api/v2/pokemon?limit={limit}&offset=0";
		String res = url.replace("{limit}" , String.valueOf(limit));
		if(redisUtils.hashKey(parseKey(limit))){
			log.info("Data from redis");
			Object data = redisUtils.get(parseKey(limit));

			Pokemon pk = objectMapper.convertValue(data, Pokemon.class);

			return CompletableFuture.completedFuture(pk);
		}
		return client.prepareGet(res)
				.execute(new AsyncCompletionHandler<Object>() {

					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						final String body = response.getResponseBody();
						Pokemon res = objectMapper.readValue(body,Pokemon.class);
						redisUtils.set(parseKey(limit) ,res,50);

						return res;
					}
				}).toCompletableFuture();


	}


	public CompletableFuture<?>pokemon(Long limit){
		AsyncHttpClient client = Dsl.asyncHttpClient();

		String url = "https://pokeapi.co/api/v2/pokemon?limit={limit}&offset=0";
		String res = url.replace("{limit}" , String.valueOf(limit));

		String url2 = "http://localhost:8000/api/v1/find/2";
		log.info("url : {} " , res);

		CompletableFuture<?> data = client.prepareGet(res)
				.execute(new AsyncCompletionHandler<Object>() {

					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						final String body = response.getResponseBody();
						log.info("Pokemon finish ---");
						return objectMapper.readValue(body,Pokemon.class);
					}
				}).toCompletableFuture();

		CompletableFuture<?>color = client.prepareGet(url2)
				.execute(new AsyncCompletionHandler<Object>() {
					@Override
					public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
						String res = response.getResponseBody();
						log.info("Color finish ---");

						return "Color";
					}
				}).toCompletableFuture();

		data.thenAccept(datas -> {
			log.info("Pokemon finish duluan {} " , datas);
		});

		color.thenAccept(results -> {
			log.info("Color finish duluan : {} " , color);
		});


		return color;
	}

}
