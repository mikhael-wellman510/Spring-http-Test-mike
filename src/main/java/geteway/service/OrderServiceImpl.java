package geteway.service;

import geteway.dto.CustomerResponse;
import geteway.dto.OrderDto;
import geteway.dto.OrderResponse;
import geteway.entity.Customer;
import geteway.entity.Order;
import geteway.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    @Override
    public OrderResponse addOrder(OrderDto orderDto) {
        try {
            Customer findCust = customerService.findCustById(orderDto.getCustomerId());
            log.info("hasil find : {} " , findCust);
            // Todo save order
            Order saveOrder = orderRepository.save(Order.builder()
                    .customer(findCust)
                    .product(orderDto.getProduct())
                    .build());
            return OrderResponse.builder()
                    .orderId(saveOrder.getId())
                    .productName(saveOrder.getProduct())
                    .customerResponse(CustomerResponse.builder()
                            .customerId(saveOrder.getCustomer().getId())
                            .customerName(saveOrder.getCustomer().getName())
                            .build())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("error : " , e);
        }
    }

    @Override
    public List<Order> findAllOrder() {


        return orderRepository.findAll();
    }
}
