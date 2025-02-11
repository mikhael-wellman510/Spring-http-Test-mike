package geteway.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.ProfileRequest;
import geteway.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileRestTemplateServiceImpl implements ProfileRestTemplateService {

    private final RestTemplate restTemplate;
    private final static String URL = "http://localhost:8000";
    private final ObjectMapper objectMapper;
    @Override
    public ProfileResponse addProfile(ProfileRequest profileRequest) {
        try {
            ProfileResponse postProfile = restTemplate.postForObject(URL+"/addProfileRest", profileRequest, ProfileResponse.class);
            return postProfile;
        }catch (Exception ex){
            throw new RuntimeException("Err : " , ex);
        }
    }

    @Override
    public ProfileResponse addProfileExchange(ProfileRequest profileRequest) {

        try{

            ProfileResponse pr = restTemplate.exchange(URL + "/addProfileRest" ,
                    HttpMethod.POST,
                    new HttpEntity<>(profileRequest),
                    ProfileResponse.class
                    ).getBody();

            log.info("Hasil pr {} " , pr);
            return pr;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public ProfileResponse findByIdExchange(String id) {

        try {

            ResponseEntity<ProfileResponse> res = restTemplate.exchange(URL + "/" + id ,
                        HttpMethod.GET,
                        null, // todo -> ini req body nya
                        ProfileResponse.class
                    );
            log.info("Res : {} " , res);
            return res.getBody();
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public ProfileResponse findById(String id) {
        try {
            ProfileResponse findById = restTemplate.getForObject(URL+"/findByIdRest/" + id , ProfileResponse.class);
            log.info("Hasil Id : {} " , findById);
            return findById;
        }catch (Exception ex){
            throw new RuntimeException("error : " + ex.getMessage());
        }

    }

    @Override
    public ProfileResponse updatedExchange(ProfileRequest profileRequest) {

        try {
            ResponseEntity<ProfileResponse> updated = restTemplate.exchange(
                    URL + "/updatedRest",
                    HttpMethod.PUT,
                    new HttpEntity<>(profileRequest),
                    ProfileResponse.class
            );

            ProfileResponse pr = updated.getBody();
            log.info("Hasil updatet {} " , updated.getBody());
            return pr;

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }



    @Override
    public List<ProfileResponse> findAllExchange() {
        try {
            ResponseEntity<List<ProfileResponse>> res = restTemplate.exchange(URL+"/findAllRest",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProfileResponse>>() {}
                    );
            log.info("Res : {} " , res);

            return res.getBody();
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }



    @Override
    public String deletedExchange(String id) {

        try {

           return restTemplate.exchange(URL+"/deleted/"+ id,
                    HttpMethod.DELETE,
                    null,
                    String.class
                    ).getBody();

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public List<ProfileResponse> findAllExecute() {
        try {
            return restTemplate.execute(
                    URL+ "/findAllRest",
                    HttpMethod.GET,
                    null,
                    res -> {

                        String body =  new String(res.getBody().readAllBytes());
                        log.info("hasil body : {} ", body);
                        return objectMapper.readValue(body, new TypeReference<>() {
                        });

                    }
            );
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public ProfileResponse updatedExecute(ProfileRequest profileRequest) {

        return restTemplate.execute(URL +"/updatedRest", HttpMethod.PUT, requestCallback -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            requestCallback.getHeaders().addAll(headers);

            // Menambahkan request body
            requestCallback.getBody().write(new ObjectMapper().writeValueAsBytes(profileRequest));
        }, responseCallback -> {
            ProfileResponse res = objectMapper.readValue(responseCallback.getBody() , ProfileResponse.class);
            log.info("Result res : {} " , res);
            return res;
        });
    }

    @Override
    public ProfileResponse addProfileExecute(ProfileRequest profileRequest) {

        return restTemplate.execute(URL + "/addProfileRest", HttpMethod.POST,
                request -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    request.getHeaders().addAll(headers);
                    request.getBody().write(new ObjectMapper().writeValueAsBytes(profileRequest));
                },response -> {
                    ProfileResponse pr = objectMapper.readValue(response.getBody(),ProfileResponse.class);
                    log.info("profile add :  {} " , pr);
                    return pr;
                }
                );
    }

    @Override
    public ProfileResponse findByIdExecut(String id) {

        return restTemplate.execute(URL+ "/findByIdRest/" + id ,
                HttpMethod.GET,
                null,
                response -> {
                    ProfileResponse pr = objectMapper.readValue(response.getBody() , ProfileResponse.class);
                    return pr;
                }
                );
    }

    @Override
    public String deletedExecute(String id) {


        return restTemplate.execute(URL + "/deleted/" + id ,
                HttpMethod.DELETE,
                null,
                response -> {
                    String responseBody = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                            .lines().collect(Collectors.joining("\n"));

                    log.info("Response body: {}", responseBody);
                    return responseBody;
                }
                );
    }
}
