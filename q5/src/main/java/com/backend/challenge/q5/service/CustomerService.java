package com.backend.challenge.q5.service;

import com.backend.challenge.q5.entity.Customer;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer getCustomer(Long id);

}
