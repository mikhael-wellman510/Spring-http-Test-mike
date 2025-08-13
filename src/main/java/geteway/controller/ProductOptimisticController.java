package geteway.controller;

import geteway.entity.ProductOptimistic;
import geteway.repository.ProductRaceRepository;
import geteway.service.ProductServiceOptimisticImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductOptimisticController {

    private final ProductServiceOptimisticImpl productService;
    private final ProductRaceRepository productRaceRepository;

    @PostMapping("/post")
    public void dummy(){

        int thread = 10;
        ExecutorService exe = Executors.newFixedThreadPool(thread);


        for (int i = 0 ; i< 30 ; i++){
            exe.submit(()->{
                int random = ThreadLocalRandom.current().nextInt();
                ProductOptimistic pr = ProductOptimistic.builder()
                        .name("Data " + random)
                        .stock(100)
                        .build();

                productRaceRepository.save(pr);

            });
        }

        exe.shutdown();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?>productOrder(@PathVariable Long id){
        int thread = 10;
        ExecutorService exe = Executors.newFixedThreadPool(thread);

        for (int i = 0 ; i< 2 ; i++){
            exe.submit(()->{

                log.info("Task : {} " , Thread.currentThread().getName());
                try {
                    productService.order(id);
                    System.out.println("Success by " + Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println("FAILED by " + Thread.currentThread().getName() + " - " + e.getMessage());
                }
            });
        }

        exe.shutdown();


        return ResponseEntity.ok("Succes");

    }

    @GetMapping("/jos/{id}")
    public void test(@PathVariable Long id) throws InterruptedException {
        productService.test(id);
    }
}
