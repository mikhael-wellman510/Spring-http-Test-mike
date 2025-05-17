package geteway.service;


import geteway.dto.OrderDto;
import geteway.dto.OrderResponse;
import geteway.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponse addOrder(OrderDto orderDto);
    List<Order> findAllOrder();
}
