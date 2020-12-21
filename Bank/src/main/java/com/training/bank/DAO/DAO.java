package com.training.bank.DAO;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.bank.model.Account;
import com.training.bank.model.Customer;
import com.training.bank.model.Transaction;

@Repository
public class DAO {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(customer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Customer> getCustomers() {
		List<Customer> customerList;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query1= session.createQuery("from Customer");
			customerList = query1.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return customerList;
	}
	@Transactional
	public Customer getCustomer(Long id) {
		Customer customer;
		Session session =sessionFactory.getCurrentSession();
		try {
			customer=session.get(Customer.class, id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return customer;
	}
	@Transactional
	public boolean updateCustomer(Long id,Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		Customer oldCustomer = session.get(Customer.class, id);
		oldCustomer.setFullName(customer.getFullName());
		oldCustomer.setDob(customer.getDob());
		oldCustomer.setAddress(customer.getAddress());
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setMobile(customer.getMobile());
		oldCustomer.setAadharNo(customer.getAadharNo());
		oldCustomer.setAccount(customer.getAccount());
		oldCustomer.setLocker(customer.getLocker());
		
		try {
			session.update(oldCustomer);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	@Transactional
	public boolean deleteCustomer(Long id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query2=session.createQuery("delete Customer where id="+id);
			query2.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	@Transactional
	public Account transactionDetail(Account account) {
		Session session = sessionFactory.getCurrentSession();
		Account oldAccount = session.get(Account.class, account.getId());
		Iterator<Transaction> iterator = account.getTransaction().iterator();
		Transaction newTransaction=iterator.next();
		oldAccount.getTransaction().add(newTransaction);
		oldAccount.setBalance(oldAccount.getBalance()-newTransaction.getAmount());
		try {
			
			session.update(oldAccount);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return oldAccount;
		
		
		
	}
}
