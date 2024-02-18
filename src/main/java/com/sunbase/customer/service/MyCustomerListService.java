package com.sunbase.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sunbase.customer.model.Customer;
import com.sunbase.customer.model.MyCustomerList;
import com.sunbase.customer.repository.MyCustomerRepository;

@Service
public class MyCustomerListService {

	@Autowired
	private MyCustomerRepository myCustomer;

	public void saveMyCustomers(MyCustomerList customer) {
		myCustomer.save(customer);
	}

//	public List<MyCustomerList> getAllMyCustomers() {
//		return myCustomer.findAll();
//	}
	
	 public Page<MyCustomerList> getAllMyCustomers(Pageable pageable) {
	        return myCustomer.findAll(pageable);
	    }

	public void deleteById(int id) {
		myCustomer.deleteById(id);
	}
}
