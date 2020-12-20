package com.training.bank.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.bank.model.Account;
import com.training.bank.model.Customer;

public interface AccountRepo extends JpaRepository<Account, Long> {
	
//	List<Account> findByCustomer(Customer customer,Sort sort);

}
