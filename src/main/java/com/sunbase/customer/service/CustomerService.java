package com.sunbase.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunbase.customer.model.Customer;
import com.sunbase.customer.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public void save(Customer c) {
		customerRepo.save(c);
	}

	public List<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}

	public Page<Customer> getAllCustomer(Pageable pageable) {
		return customerRepo.findAll(pageable);
	}

	public Customer getCustomerById(int id) {
		return customerRepo.findById(id).get();
	}

	public void deleteById(int id) {
		customerRepo.deleteById(id);
	}

	public Customer findByUserId(int id) {
		return customerRepo.findById(id).get();
	}

	public Customer get(int id) {
		return customerRepo.findById(id).get();
	}

	public Customer searchCustomerByIdAndFirstName(Integer id, String firstName) {
		return customerRepo.findByIdAndFirstName(id, firstName);
	}

}
