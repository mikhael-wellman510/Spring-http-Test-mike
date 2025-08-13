package geteway.controller;

import geteway.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class SportController {

    private final SportService sportService;

    @GetMapping("/async")
    public void result(){

        sportService.service();
    }

    @GetMapping("/async2")
    public void result2(){

        sportService.combineFuture();
    }

    @GetMapping("/sportCb")
    public CompletableFuture<?>circuitBrake(){

        return sportService.callExternalApi()
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                }).exceptionally(ex-> {
                    return ResponseEntity.internalServerError().build();
                });
    }

}
