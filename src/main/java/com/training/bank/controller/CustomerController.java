package com.training.bank.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.bank.DAO.DAO;
import com.training.bank.model.Account;
import com.training.bank.model.Customer;
import com.training.bank.model.Locker;
import com.training.bank.model.Login;
import com.training.bank.model.MiniStatement;

@RestController
@RequestMapping("/bank/v1")
public class CustomerController {
	
//	@Autowired
//	CustomerRepo crepo;
//	@Autowired
//	AccountRepo arepo;
	@Autowired
	DAO dao;
	
	@GetMapping(path = "/customers")
	public List<Customer> getAllCustomers() {
		return dao.getCustomers();
	}
	@GetMapping(path = "/account/{id}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Account getAccount(@PathVariable Long id) {
		return dao.getAccountDetails(id);
	}
//	
	@GetMapping(path = "/customer/{id}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Customer getCustomerbyId(@PathVariable String id){
		return dao.getCustomer(Long.parseLong(id));
	}
	
	 @PostMapping(path="/customer",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 public boolean createCustomer(@RequestBody Customer customer){
		 
		  if(customer.getLocker()) {
			  Iterator<Account> iterator = customer.getAccount().iterator();
			  Account account = iterator.next();
			  Locker locker = new Locker();
			  locker.setAvailable(true);
			  account.setLocker(locker);
			  
		  }
		 return dao.saveCustomer(customer);
	 }
	 @PutMapping(path = "/customer/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 public boolean updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
		 return dao.updateCustomer(id, customer);
	 }
	 @DeleteMapping(path = "/customer/{id}")
	 public boolean deleteCustomer(@PathVariable Long id) {
		 return dao.deleteCustomer(id);
		 
		 
	 }
	 @PostMapping(path="/transaction",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 public Account getTransaction(@RequestBody Account account) {
		 return dao.transactionDetail(account);
	 }
	 @PostMapping(path="/withdraw",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 public Account getWithdraw(@RequestBody Account account) {
		 System.out.println(account.getWithdraw());
		 return dao.withdraw(account);
	 }
	 @GetMapping(path = "/statement/{id}/{date}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
		public MiniStatement getAccount(@PathVariable("id") Long id,@PathVariable("date") String date) {
			return dao.getStatement(id, date);
	 }
	 @PostMapping(path="/login")
	 public Customer Login(@RequestBody Login login) {
		 return dao.signIn(login);
	 }
}
	 
