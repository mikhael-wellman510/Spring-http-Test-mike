//package geteway.controller;
//
//import geteway.dto.ProductDTO;
//import geteway.service.ProductServiceElasticImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class ProductElasticController {
//
//    private final ProductServiceElasticImpl productService;
//
//    @GetMapping("/full-text-search")
//    public ResponseEntity<?>fullTextSearch(@RequestParam(name = "keyword" , defaultValue = "") String keyword, @RequestParam(name = "page" , defaultValue = "0")Integer page , @RequestParam(name = "size" , defaultValue = "5") Integer size){
//        log.info("Start");
//        var res = productService.fullTextSearch(keyword,page,size);
//
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/saveProduct")
//    public ResponseEntity<?>saveProduct(@RequestBody ProductDTO productDTO){
//
//        var result = productService.addProduct(productDTO);
//
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<?>findAll(){
//        var res = productService.getAllProduct();
//
//        return ResponseEntity.ok(res);
//    }
//
//    @DeleteMapping("/deleted")
//    public void deleted(){
//        productService.deletedAll();
//    }
//
//    @PostMapping("/dummy")
//    public void insert(){
//        productService.insertDummyData(10000);
//    }
//
//}
