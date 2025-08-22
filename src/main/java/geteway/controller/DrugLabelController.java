package geteway.controller;

import geteway.service.DrugLabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class DrugLabelController {

    private final DrugLabelService drugLabelService;



    @GetMapping("/findLabelDrug")
    public CompletableFuture<?>findAllLabel(){
        return drugLabelService.findAllDrugLabel()
                .thenApply(res -> {
                    return ResponseEntity.ok(res);
                }).exceptionally(ex -> {
                    return null;
                });
    }

    @GetMapping("/cek")
    public String testings(){

        return "Hallo guys";
    }

}
