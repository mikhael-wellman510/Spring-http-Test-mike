package geteway.controller;

import geteway.entity.ProductPesimistic;
import geteway.repository.ProductPesimisticRepository;
import geteway.service.ProductPesimisticImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class ProductPesimisticController {

    private final ProductPesimisticImpl productPesimistic;
    private final ProductPesimisticRepository productPesimisticRepository;
    @PostMapping("/sendPost")
    public void post(){

        for (int i = 0 ; i < 10  ; i ++ ){
            int random = ThreadLocalRandom.current().nextInt();
            ProductPesimistic pp = ProductPesimistic.builder()
                    .name("Handuk" + random)
                    .stock(100)
                    .build();

            productPesimisticRepository.save(pp);


        }

    }

    @GetMapping("/pesimis/{id}")
    public void testingPesimis(@PathVariable Long id){

        int thread = 10;

        ExecutorService ex = Executors.newFixedThreadPool(thread);

        for (int i = 0 ; i < thread ; i++){
            ex.submit(()->{
                productPesimistic.testPesimistic(id);
            });
        }
        ex.shutdown();

    }

    @PostMapping("/rabbit/{id}")
    public void messageBrokerRaceCondition(@PathVariable Long id){


        int thread = 10;

        ExecutorService ex = Executors.newFixedThreadPool(thread);
//        productPesimistic.testMessageBroker(id);
        for (int i = 0 ; i < thread ; i++){

            ex.submit(()->{
                productPesimistic.testMessageBroker(id);
            });
        }
        ex.shutdown();

    }

    @PostMapping("/rabbit2/{id}")
    public void messageBrokerRaceCondition2(@PathVariable Long id){


//        int thread = 10;
//
//        ExecutorService ex = Executors.newFixedThreadPool(thread);
//        productPesimistic.testMessageBroker(id);
        for (int i = 0 ; i < 10; i++){


                productPesimistic.testMessageBroker(id);

        }


    }

}
