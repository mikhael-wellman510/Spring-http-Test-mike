package geteway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import geteway.dto.ProfileRequest;
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

}
