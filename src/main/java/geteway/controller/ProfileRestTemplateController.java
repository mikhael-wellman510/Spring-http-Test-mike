package geteway.controller;

import geteway.dto.ProfileRequest;
import geteway.dto.ProfileResponse;
import geteway.service.ProfileRestTemplateService;
import geteway.util.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/restTemplate")
public class ProfileRestTemplateController {

    private final ProfileRestTemplateService profileRestTemplateService;

    @PostMapping("/add")
    public ResponseEntity<?> addData(@RequestBody ProfileRequest profileRequest){

        ProfileResponse pr = profileRestTemplateService.addProfile(profileRequest);

        return ResponseEntity.ok(ApiResponse.success("Success",pr));
    }

    @PostMapping("/addExchange")
    public ResponseEntity<?> addDataExchange(@RequestBody ProfileRequest profileRequest){

        ProfileResponse pr = profileRestTemplateService.addProfileExchange(profileRequest);

        return ResponseEntity.ok(ApiResponse.success("Success",pr));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){

        ProfileResponse findById = profileRestTemplateService.findById(id);

        return ResponseEntity.ok(ApiResponse.success("Success" , findById));
    }

    @GetMapping("/findByIdExchange/{id}")
    public ResponseEntity<?> findByIdExchange(@PathVariable String id){

        ProfileResponse findById = profileRestTemplateService.findByIdExchange(id);

        return ResponseEntity.ok(ApiResponse.success("Success" , findById));
    }

    @PutMapping("/updated")
    public ResponseEntity<?>updated(@RequestBody ProfileRequest profileRequest){

        ProfileResponse pr = profileRestTemplateService.updatedExchange(profileRequest);

        return ResponseEntity.ok(pr);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?>findAll(){
        List<ProfileResponse> res=profileRestTemplateService.findAllExecute();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/findAllExchange")
    public ResponseEntity<?>findAllExchange(){
        List<ProfileResponse> res=profileRestTemplateService.findAllExchange();

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?>deletedExchaange(@PathVariable String id){
        String res = profileRestTemplateService.deletedExchange(id);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/updatedExecute")
    public ResponseEntity<?> updatedExecute(@RequestBody ProfileRequest profileRequest){

        ProfileResponse profileResponse = profileRestTemplateService.updatedExecute(profileRequest);

        return ResponseEntity.ok(profileResponse);
    }

    @PostMapping("/postExecute")
    public ResponseEntity<?> addProfileExe(@RequestBody ProfileRequest profileRequest){

        ProfileResponse profileResponse = profileRestTemplateService.addProfileExecute(profileRequest);

        return ResponseEntity.ok(profileResponse);
    }

    @GetMapping("/findByIdExe/{id}")
    public ResponseEntity<?>findByIdExe(@PathVariable String id){

        ProfileResponse profileResponsen = profileRestTemplateService.findByIdExecut(id);
        return ResponseEntity.ok(profileResponsen);
    }

    @DeleteMapping("/deletedExe/{id}")
    public ResponseEntity<?>deletedExe(@PathVariable String id){
        String res = profileRestTemplateService.deletedExecute(id);
        return ResponseEntity.ok(res);

    }
}
