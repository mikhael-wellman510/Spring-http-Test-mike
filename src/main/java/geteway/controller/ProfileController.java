package geteway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import geteway.dto.ProfileRequest;
import geteway.dto.ScopeDto;
import geteway.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/scope")
    public String scope(@RequestBody ScopeDto scopeDto){
        log.info("cek");
        return profileService.scope(scopeDto);
    }

    @PostMapping("/add")
    public CompletableFuture<?> addProfile(@RequestBody ProfileRequest profileRequest) throws JsonProcessingException {

        return profileService.addProfile(profileRequest)
                .thenApply(res -> {
                    log.info("Res : {} ", res);
                    return ResponseEntity.ok(res);
                }).exceptionally(ex -> {
                    return null;
                });
    }

    @GetMapping("/findById/{id}")
    public CompletableFuture<?> findById(@PathVariable String id) throws JsonProcessingException {
        return profileService.findById(id)
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                })
                .exceptionally(ex-> {
                    return null;
                });
    }

    @GetMapping("/findNameAndWeight")
    public CompletableFuture<?> paggingFindNameAndWeight(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "weight", required = false , defaultValue = "0") Integer weight, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "5")Integer size){
        log.info("weight {} " , weight);
        return profileService.paggingFindByName(name, weight, page, size)
                .thenApply(res-> {
                    return ResponseEntity.ok(res);
                }).exceptionally(ex -> {
                    return null;
                });
    }

    @PutMapping("/updated")
    public CompletableFuture<?> updated(@RequestBody ProfileRequest profileRequest) throws JsonProcessingException{
        return profileService.updated(profileRequest)
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                })
                .exceptionally(ex -> {
                    log.error(" masuk : {}" , ex);
                    return null;
                });
    }

    @GetMapping("/findAll")
    public CompletableFuture<?>findAll() throws JsonProcessingException {
        return profileService.findAll()
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                })
                .exceptionally(ex -> {
                    return null;
                });
    }

    @DeleteMapping("/deleted/{id}")
    public CompletableFuture<?> delete(@PathVariable String id) throws JsonProcessingException {
        return profileService.deleted(id)
                .thenApply(res -> {
                    log.info("res controlelr {} " , res);
                    return ResponseEntity.ok(res);
                })
                .exceptionally(res -> {
                    return null;
                });
    }

    @GetMapping("/findBigQuery")
    public CompletableFuture<?> findAllBigQuery() throws JsonProcessingException {

        return profileService.findAllBigQuery()
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                })
                .exceptionally(ex ->{
                    return  null;
                });
    }

}
