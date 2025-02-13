package geteway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import geteway.dto.ProfileRequest;
import geteway.dto.ProfileResponse;

import java.util.concurrent.CompletableFuture;

public interface ProfileService {

    CompletableFuture<?> addProfile(ProfileRequest profileRequest) throws JsonProcessingException;
    CompletableFuture<?> findById(String id) throws JsonProcessingException;
    CompletableFuture<?> updated(ProfileRequest profileRequest) throws JsonProcessingException;
    CompletableFuture<?> findAll() throws JsonProcessingException;
    CompletableFuture<?> deleted(String id) throws JsonProcessingException;
    CompletableFuture<?> paggingFindByName(String name ,Integer weight, Integer page , Integer size);
}
