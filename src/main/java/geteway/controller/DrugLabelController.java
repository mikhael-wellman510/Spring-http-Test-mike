package geteway.controller;

import geteway.service.DrugLabelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class DrugLabelController {

    private final DrugLabelService drugLabelService;

    public DrugLabelController(DrugLabelService drugLabelService){
        this.drugLabelService = drugLabelService;
    }

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
