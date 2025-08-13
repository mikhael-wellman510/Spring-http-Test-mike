package geteway.service;

import geteway.entity.ProductOptimistic;
import geteway.repository.ProductRaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceOptimisticImpl {

    private final ProductRaceRepository productRepository;


    /*
    * todo-> Optimistick Lock
    * Jika ada 10 orang yg order bersamaan
    * 1 orang akan berhasil , dan 9 orang akan gagal
    * */
    @Transactional
    public ProductOptimistic order(Long id) throws InterruptedException {

            ProductOptimistic pr = productRepository.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
            Integer currStock = pr.getStock();
            pr.setStock(currStock-1);
            productRepository.save(pr);
             log.info("Tes : {} " , pr.getVersion());
            return pr;
    }


    // Todo -> Ini testing doang
    @Transactional
    public void test(Long id) throws InterruptedException {

        ProductOptimistic pr = productRepository.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
        Thread.sleep(2000);
        log.info("cek {} " ,pr);

        Integer currStock = pr.getStock();
        pr.setStock(currStock-1);

        productRepository.save(pr);
        log.info("Tes : {} " , pr.getVersion());
    }
}
