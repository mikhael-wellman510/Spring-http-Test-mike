package geteway.IocAndDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderIocController {


    @GetMapping("/di/{name}")
    public ResponseEntity<?>testing(@PathVariable String name){
        OrderIocServiceImpl oi = new OrderIocServiceImpl();
        String res = oi.testDi(name);

        return ResponseEntity.ok(res);
    }
}
