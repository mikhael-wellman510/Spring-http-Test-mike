package geteway.RaceConditionExample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping("/addQty")
    public ResponseEntity<?> add(@RequestBody ProductRequest productRequest) throws InterruptedException {

        ProductResponse productResponse = productService.editQty(productRequest);

        return ResponseEntity.ok(productResponse);
    }

}
