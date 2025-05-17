package geteway.controller;

import geteway.dto.CustomerDto;
import geteway.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/saveCust")
    public ResponseEntity<?>addCust(@RequestBody CustomerDto customerDto){

        var data = customerService.addCustomer(customerDto);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/findCustAll")
    public ResponseEntity<?>findAll(){

        var data = customerService.findAllCustomer();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?>findById(@PathVariable("id")String id){
        var data = customerService.findCustById(id);

        return ResponseEntity.ok(data);
    }
}
