package geteway.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.*;

import geteway.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final AsyncHttpClient asyncHttpClient;
    private final ObjectMapper objectMapper;

    @Override
    public String scope(ScopeDto scopeDto) {
        return scopeDto.getScope();
    }

    @Override
    public CompletableFuture<?> addProfile(ProfileRequest profileRequest) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/addProfile";
        String json = objectMapper.writeValueAsString(profileRequest);

        log.info("json : {} " ,json);

        return asyncHttpClient.preparePost(url).setHeaders(httpHeaders)
                .setBody(json)
                .execute(new AsyncCompletionHandler<ProfileResponse>() {
            @Override
            public @Nullable ProfileResponse onCompleted(@Nullable Response response) throws Exception {
                String body =response.getResponseBody();
                JsonNode jsonNode = objectMapper.readTree(body);
                JsonNode data = jsonNode.path("data");
                return objectMapper.treeToValue(data, ProfileResponse.class);
            }
        }).toCompletableFuture();
    }

    @Override
    public CompletableFuture<?> findById(String id) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/findById/" + id;
        log.info("hasil url : {} " ,url);
        return asyncHttpClient.prepareGet(url).setHeaders(httpHeaders).execute(new AsyncCompletionHandler<Object>() {
            @Override
            public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                String body = response.getResponseBody();
                JsonNode jsonNode = objectMapper.readTree(body);
                JsonNode data = jsonNode.path("data");
                return objectMapper.treeToValue(data,ProfileResponse.class);
            }
        }).toCompletableFuture();
    }

    @Override
    public CompletableFuture<?> updated(ProfileRequest profileRequest) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/updated";
        String json = objectMapper.writeValueAsString(profileRequest);
        return asyncHttpClient.preparePut(url)
                .setHeaders(httpHeaders)
                .setBody(json)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                            String body = response.getResponseBody();
                            int status = response.getStatusCode();
                            if (status == 200){

                                return objectMapper.readValue(body, ProfileResponse.class);
                            }else {
                                throw new RuntimeException("NotFound");
                            }


                    }
                }).toCompletableFuture();

    }

    @Override
    public CompletableFuture<?> findAll() throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/findAll";

        return asyncHttpClient.prepareGet(url)
                .setHeaders(httpHeaders)
                .execute(new AsyncCompletionHandler<List<ProfileResponse>>() {
                    @Override
                    public @Nullable List<ProfileResponse> onCompleted(@Nullable Response response) throws Exception {

                        String body = response.getResponseBody();
                        List<ProfileResponse> data = objectMapper.readValue(body, new TypeReference<List<ProfileResponse>>() {
                        });
                        return data.stream().map(res -> ProfileResponse.builder()
                                .id(res.getId())
                                .name(res.getName())
                                .address(res.getAddress())
                                .age(res.getAge())
                                .weight(res.getWeight())
                                .hobby(res.getHobby())
                                .build()).toList();
                    }
                }).toCompletableFuture();
    }

    @Override
    public CompletableFuture<?> deleted(String id) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/deleted/" + id;


        return asyncHttpClient.prepareDelete(url)
                .setHeaders(httpHeaders)
                .execute(new AsyncCompletionHandler<String>() {
                    @Override
                    public @Nullable String onCompleted(@Nullable Response response) throws Exception {
                        // Todo -> hasil nya sudah string
                        String body = response.getResponseBody();

                        return body;
                    }
                }).toCompletableFuture()
                ;
    }

    @Override
    public CompletableFuture<?> paggingFindByName(String name, Integer weight, Integer page, Integer size) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        String url = "http://localhost:8000/findNameAndWeight?name=" + name + "&weight=" + weight + "&page=" +page + "&size=" + size;

        return asyncHttpClient.prepareGet(url).setHeaders(httpHeaders)
                .execute(new AsyncCompletionHandler<PaggingResponse>() {

                    @Override
                    public @Nullable PaggingResponse onCompleted(@Nullable Response response) throws Exception {

                        String body = response.getResponseBody();
                        return objectMapper.readValue(body, PaggingResponse.class);
                    }
                }).toCompletableFuture();
    }

    @Override
    public CompletableFuture<?> findAllBigQuery() throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE,"application/json" );

        String url = "http://localhost:8000/findAllBigQuery";


        return asyncHttpClient.prepareGet(url)
                .setHeaders(httpHeaders)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                        System.out.println(response.getResponseBody());
                        String body = response.getResponseBody();
                        log.info("body : {} " , body);
                        return objectMapper.readValue(body, new TypeReference<List<ReactionRecord>>() {});
                    }
                })
                .toCompletableFuture()
                ;
    }
}
