package geteway.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.DrugLabelResponse;
import geteway.dto.ProfileResponse;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DrugLebelServiceImpl implements DrugLabelService {

    private final AsyncHttpClient asyncHttpClient;
    private final ObjectMapper objectMapper;

    public DrugLebelServiceImpl(AsyncHttpClient asyncHttpClient , ObjectMapper objectMapper){
            this.asyncHttpClient = asyncHttpClient;
            this.objectMapper =objectMapper;
    }

    @Override
    public CompletableFuture<?> findAllDrugLabel() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        String url = "http://103.87.66.93:8080/findDrug";
        return asyncHttpClient.prepareGet(url)
                .setHeaders(httpHeaders)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
                        String bodyResponse = response.getResponseBody();

                        List<DrugLabelResponse> data = objectMapper.readValue(bodyResponse ,new TypeReference<List<DrugLabelResponse>>(){});

                        return data.stream().map((val)-> DrugLabelResponse.builder()
                                .city(val.getCity())
                                .country(val.getCountry())
                                .reasonForRecall(val.getReasonForRecall())
                                .productDescription(val.getProductDescription())
                                .productQuantity(val.getProductQuantity()).build()).toList();
                    }
                }).toCompletableFuture();
    }
}
