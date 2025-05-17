package geteway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FetchCsvDownload {

    private final RestTemplate restTemplate;

    @GetMapping("/fetchCsv")
    public ResponseEntity<?>fetchCsvFromCronExample(){

        String genAiUrl = "http://localhost:8080/csv";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.parseMediaType("text/csv")));

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<byte[]>response = restTemplate.exchange(
                genAiUrl,
                HttpMethod.GET,
                requestEntity,
                byte[].class
        );

        ByteArrayResource resource = new ByteArrayResource(response.getBody());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.parseMediaType("text/csv"));
        responseHeaders.setContentDisposition(ContentDisposition.attachment().filename("downloaded.csv").build());

        return new ResponseEntity<>(resource,responseHeaders,HttpStatus.OK);
    }
}
