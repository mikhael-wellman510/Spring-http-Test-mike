package geteway.controller;

import geteway.service.BigQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BigQueryController {

    private final BigQueryService bigQueryService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        var data =  bigQueryService.findAllBigQuery();
        return ResponseEntity.ok(data);
    }
}
