package com.sunbase.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sunbase.customer.model.MyCustomerList;

@Repository
public interface MyCustomerRepository extends JpaRepository<MyCustomerList, Integer> {

}
