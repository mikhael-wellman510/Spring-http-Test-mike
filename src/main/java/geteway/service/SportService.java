package geteway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.SportResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportService {

    private final AsyncHttpClient clien;
    private final ObjectMapper objectMapper;

    @CircuitBreaker(name = "externalApiCb" , fallbackMethod = "fallbackResponse")
    public CompletableFuture<?>callExternalApi(){
        String url = "http://localhost:8000/getAsync1";

        return clien.prepareGet(url)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                        assert response != null;
                        String body = response.getResponseBody();

                        log.info("Cek {} " , body);
                        return objectMapper.readValue(body,SportResponse.class);
                    }
                }).toCompletableFuture();
    }
    public CompletableFuture<?> fallbackResponse(Throwable throwable) {
        log.warn("Fallback method called due to: {}", throwable.toString());
        return CompletableFuture.completedFuture("Fallback: External service is currently unavailable.");
    }
    // Todo -> Asyncronous Process
    public void combineFuture(){
        String url = "http://localhost:8000/getAsync1";
        String url2 = "http://localhost:8000/getAsync2";
        CompletableFuture<SportResponse> service1 = clien.prepareGet(url)
                .execute(new AsyncCompletionHandler<SportResponse>() {
                    @Override
                    public @Nullable SportResponse onCompleted(@Nullable Response response) throws Exception {
                        assert response != null;
                        String body = response.getResponseBody();
                        SportResponse res = objectMapper.readValue(body, SportResponse.class);
                        log.info("Hasil Res : {} " , res);

                        return res;
                    }
                })
                .toCompletableFuture();


        CompletableFuture<SportResponse> service2 = clien.prepareGet(url2)
                .execute(new AsyncCompletionHandler<SportResponse>() {
                    @Override
                    public @Nullable SportResponse onCompleted(@Nullable Response response) throws Exception {
                        assert response != null;
                        String body = response.getResponseBody();
                        SportResponse res = objectMapper.readValue(body, SportResponse.class);
                        log.info("Hasil Res : {} " , res);

                        return res;
                    }
                })
                .toCompletableFuture();

        CompletableFuture<?>combine = CompletableFuture.allOf(service1, service2);

        combine.thenRun(()->{

            try {
                SportResponse s1 = service1.get();
                SportResponse s2 = service2.get();

                log.info("Hasil 1 : {} " , s1);
                log.info("Hasil 2 : {} " , s2);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Finis !");
    }

    public void service(){
        String url = "http://localhost:8000/getAsync1";
        String url2 = "http://localhost:8000/getAsync2";
        CompletableFuture<?> service1 = clien.prepareGet(url)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                        assert response != null;
                        String body = response.getResponseBody();
                        SportResponse res = objectMapper.readValue(body, SportResponse.class);
                        log.info("Hasil Res : {} " , res);

                        return null;
                    }
                })
                .toCompletableFuture();


        CompletableFuture<?> service2 = clien.prepareGet(url2)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                        assert response != null;
                        String body = response.getResponseBody();
                        SportResponse res = objectMapper.readValue(body, SportResponse.class);
                        log.info("Hasil Res : {} " , res);

                        return null;
                    }
                })
                .toCompletableFuture();

        log.info("Finis !");


    }
}
