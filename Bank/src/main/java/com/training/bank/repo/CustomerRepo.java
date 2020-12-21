package com.training.bank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.training.bank.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Long>{

//	void delete(Optional<Customer> c);


}
