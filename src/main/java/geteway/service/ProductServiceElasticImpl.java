//package geteway.service;
//
//import geteway.dto.PageResponse;
//import geteway.dto.ProductDTO;
//import geteway.entity.ProductElastic;
//import geteway.repository.ProductElasticRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.client.elc.NativeQuery;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class ProductServiceElasticImpl {
//
////    private final ProductElasticRepository productRepository;
//    private final ProductElasticRepository productRepository;
//    private final ElasticsearchOperations elasticsearchOperations;
//
//    public PageResponse<ProductDTO> fullTextSearch(String keyword , int page , int size){
//        Pageable pageable = PageRequest.of(page, size);
//        log.info("Key : {} , page : {} ,size : {} " ,keyword ,page , size );
//        NativeQuery query = NativeQuery.builder()
//                .withQuery(q -> q
//                        .match(m -> m
//                                .field("store_name") // ini harus sama dengan nama di ENTIty
//                                .query(keyword)
//                                .fuzziness("AUTO")
//                        )
//                )
//                .withPageable(pageable)
//                .build();
//
//        SearchHits<ProductElastic> searchHits = elasticsearchOperations.search(query, ProductElastic.class);
//
//        List<ProductDTO> results = searchHits.stream().map(hit -> {
//            log.info("Hit : {} " , hit);
//            ProductElastic p = hit.getContent();
//            return ProductDTO.builder()
//                    .id(p.getId())
//                    .productName(p.getProductName())
//                    .storeName(p.getStoreName())
//                    .price(p.getPrice())
//                    .createdAt(LocalDateTime.ofInstant(p.getCreatedAt(), ZoneId.systemDefault()))
//                    .build();
//        }).toList();
//
//        long totalHits = searchHits.getTotalHits();
//        int totalPages = (int) Math.ceil((double) totalHits / size);
//
//        return PageResponse.<ProductDTO>builder()
//                .content(results)
//                .page(page)
//                .size(size)
//                .totalElements(totalHits)
//                .totalPages(totalPages)
//                .build();
//    }
//
//    public ProductDTO addProduct(ProductDTO productDTO){
//
//        ProductElastic save = productRepository.save(ProductElastic.builder()
//                        .productName(productDTO.getProductName())
//                        .storeName(productDTO.getStoreName())
//                        .price(productDTO.getPrice())
//                        .createdAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
//                .build());
//
//        log.info("Hasil product : {} " , save);
//        return ProductDTO.builder()
//                .id(save.getId())
//                .productName(save.getProductName())
//                .storeName(save.getStoreName())
//                .price(save.getPrice())
//                .createdAt(LocalDateTime.ofInstant(save.getCreatedAt(), ZoneId.systemDefault()))
//                .build();
//    }
//
//    public Iterable<ProductElastic>getAllProduct(){
//
//        return productRepository.findAll();
//    }
//
//    public void deletedAll(){
//        productRepository.deleteAll();
//    }
//
//    public void insertDummyData(int total) {
//        List<String> productNames = List.of("Kopi Arabika", "Teh Hijau", "Roti Tawar", "Minyak Goreng", "Beras Premium", "Mie Instan", "Gula Pasir", "Susu UHT", "Sabun Mandi", "Shampoo Herbal");
//        List<String> storeNames = List.of("Toko Sumber Rejeki", "Minimarket Berkah", "Warung Bu Tini", "Grosir Sejahtera", "Supermart", "Toko Andalan", "Mart Santosa", "Warung Sederhana", "Toko Sederhana" ,"Toko Sumber","Toserba Makmur", "Fresh Market");
//
//        Random random = new Random();
//        List<ProductElastic> dummyList = new ArrayList<>();
//
//        for (int i = 1; i <= total; i++) {
//            String productName = productNames.get(random.nextInt(productNames.size()));
//            String storeName = storeNames.get(random.nextInt(storeNames.size()));
//            double price = 5000 + random.nextInt(50000); // harga acak antara 5000 - 55000
//
//            ProductElastic product = ProductElastic.builder()
//                    .productName(productName)
//                    .storeName(storeName)
//                    .price(price)
//                    .createdAt(LocalDateTime.now().minusDays(random.nextInt(30)).atZone(ZoneId.systemDefault()).toInstant()) // tanggal random 30 hari terakhir
//                    .build();
//
//            dummyList.add(product);
//        }
//
//        productRepository.saveAll(dummyList);
//        log.info("✅ Berhasil insert {} produk ke Elasticsearch", total);
//    }
//}
