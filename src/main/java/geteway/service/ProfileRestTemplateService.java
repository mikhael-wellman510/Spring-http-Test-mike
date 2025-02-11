package geteway.service;

import geteway.dto.ProfileRequest;
import geteway.dto.ProfileResponse;

import java.util.List;

public interface ProfileRestTemplateService {
    ProfileResponse addProfile(ProfileRequest profileRequest);
    ProfileResponse addProfileExchange(ProfileRequest profileRequest);
    ProfileResponse findById(String id);
    ProfileResponse findByIdExchange(String id);
    ProfileResponse updatedExchange(ProfileRequest profileRequest);
    List<ProfileResponse> findAllExecute();
    ProfileResponse updatedExecute(ProfileRequest profileRequest);
    List<ProfileResponse> findAllExchange();
    String deletedExchange(String id);
    ProfileResponse addProfileExecute(ProfileRequest profileRequest);
    ProfileResponse findByIdExecut(String id);
    String deletedExecute(String id);
}
