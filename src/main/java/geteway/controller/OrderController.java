package geteway.controller;

import geteway.dto.OrderDto;
import geteway.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MessageSource messageSource;

    @GetMapping("/translate")
    ResponseEntity<?>testings(Locale locale){

        return ResponseEntity.ok(messageSource.getMessage("greeting" , null , locale));

    }

    @PostMapping("/saveOrder")
    ResponseEntity<?>saveOrder(@RequestBody OrderDto orderDto){

        var res = orderService.addOrder(orderDto);


        return ResponseEntity.ok(res);
    }

    @GetMapping("/findAllOrder")
    ResponseEntity<?>findAllOrder(){
        var res = orderService.findAllOrder();

        return ResponseEntity.ok(res);
    }
}
