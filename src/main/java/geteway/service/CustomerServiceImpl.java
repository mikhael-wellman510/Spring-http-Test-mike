package geteway.service;

import geteway.dto.CustomerDto;
import geteway.entity.Customer;
import geteway.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(CustomerDto customerDto) {

        return customerRepository.save(Customer.builder()
                        .name(customerDto.getName())
                .build());
    }

    @Override
    public Customer findCustById(String customerId) {

        return customerRepository.findById(customerId).orElseThrow(()-> new IllegalArgumentException("Customer not found"));
    }


    @Override
    public List<Customer> findAllCustomer() {

        return customerRepository.findAll();
    }
}
