package geteway.service;

import geteway.dto.CustomerDto;
import geteway.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(CustomerDto customerDto);
    Customer findCustById(String customerId);
    List<Customer>findAllCustomer();
}
