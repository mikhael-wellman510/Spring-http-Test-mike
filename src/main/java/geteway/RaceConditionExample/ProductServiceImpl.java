package geteway.RaceConditionExample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    // Pesimistic
    @Override
    @Transactional
    public ProductResponse editQty(ProductRequest productRequest) {

        Product product = productRepository.findById(productRequest.getId()).orElse(null);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assert product != null;
        product.setQty(productRequest.getQty() + product.getQty());

        Product editProduct = productRepository.save(product);
        return ProductResponse.builder()
                .id(editProduct.getId())
                .name(editProduct.getName())
                .qty(editProduct.getQty())
                .build();
    }

}
