package com.training.bank.DAO;

import java.util.ArrayList;
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
import com.training.bank.model.Deposit;
import com.training.bank.model.Login;
import com.training.bank.model.MiniStatement;
import com.training.bank.model.Transaction;
import com.training.bank.model.Withdraw;

@Repository
public class DAO {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	MiniStatement miniStatement;
	
	@Transactional
	public boolean saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(customer);
			session.merge(customer);
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
			for(Customer cust : customerList) {
				for(Account account:cust.getAccount()) {
					for(Transaction trans:account.getTransaction()) {
						
					}
					for(Withdraw with : account.getWithdraw()) {
						
					}
					for(Deposit depo : account.getDeposit()) {
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return customerList;
	}
	@Transactional
	public Account getAccountDetails(Long id) {
		Account account;
		Session session = sessionFactory.getCurrentSession();
		try {
			account=session.get(Account.class, id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return account;
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
			session.merge(oldCustomer);
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
		for(Withdraw with:oldAccount.getWithdraw()) {
			
		}
		for(Deposit dep:oldAccount.getDeposit()) {
			
		}
		oldAccount.setBalance(oldAccount.getBalance()-newTransaction.getAmount());
		try {
			
			session.saveOrUpdate(oldAccount);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return oldAccount;
	}
	@Transactional
	public Account withdraw(Account account) {
		Session session = sessionFactory.getCurrentSession();
		
		Account oldAccount = session.get(Account.class, account.getId());
		Iterator<Withdraw> iterator = account.getWithdraw().iterator();
		Withdraw newWithdraw = iterator.next();
		for(Deposit old:oldAccount.getDeposit()) {
			
		}
		if(account.getWithdraw()==null)
		{		
			List<Withdraw> withList = new ArrayList<Withdraw>();
			withList.add(newWithdraw);
			oldAccount.setWithdraw(withList);
		}
		else {
		for(Withdraw with: account.getWithdraw()) {
		}
		
		oldAccount.getWithdraw().add(newWithdraw);
		}
		oldAccount.setBalance(oldAccount.getBalance()-newWithdraw.getWithdraw_amount());
		try {
			session.saveOrUpdate(oldAccount);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return oldAccount;
	}
	@Transactional
	public Account deposit(Account account) {
		Session session = sessionFactory.getCurrentSession();
		
		
		Account oldAccount = session.get(Account.class, account.getId());
		Iterator<Deposit> iterator = account.getDeposit().iterator();
		Deposit newDeposit = iterator.next();
		if(account.getDeposit()==null)
		{		
			List<Deposit> withList = new ArrayList<Deposit>();
			withList.add(newDeposit);
			oldAccount.setDeposit(withList);
		}
		else {
		for(Deposit with: account.getDeposit()) {
		}
		
		oldAccount.getDeposit().add(newDeposit);
		}
		oldAccount.setBalance(oldAccount.getBalance()+newDeposit.getDeposit_amount());
		try {
			session.saveOrUpdate(oldAccount);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return oldAccount;
	}
	@Transactional
	public MiniStatement getStatement(Long id,String date) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		
		for(Account account :customer.getAccount()) {
			for(Transaction transaction:account.getTransaction()) {
				
				String [] strbuffer= transaction.getDate().toString().split(" ");
				System.out.println(strbuffer[0]);
				if(strbuffer[0].equals(date)) {
					miniStatement.setTransferAccNo(transaction.getTransferAccNo());
					miniStatement.setTransferType(transaction.getTransferType());
					miniStatement.setAmount(transaction.getAmount());
				}
			}
			for(Withdraw withdraw:account.getWithdraw()) {
				String [] strbuffer= withdraw.getDate().toString().split(" ");
				System.out.println(strbuffer[0]);
				if(strbuffer[0].equals(date)) {
					miniStatement.setWithdraw_amount(withdraw.getWithdraw_amount());
				}
			}
			for(Deposit deposit:account.getDeposit()) {
				String [] strbuffer= deposit.getDate().toString().split(" ");
				if(strbuffer[0].equals(date)) {
					miniStatement.setDeposit_amount(deposit.getDeposit_amount());
				}
			}
		}
		return miniStatement;
		
	}
	@Transactional
	public Customer signIn(Login login) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer where email='"+login.getEmail()+"'and password='"+login.getPassword()+"'");
		Customer customer=new Customer();
		try {
			customer=(Customer) query.getSingleResult();
				for(Account account:customer.getAccount()) {
					for(Transaction trans:account.getTransaction()) {
						
					}
					for(Withdraw with : account.getWithdraw()) {
						
					}
					for(Deposit depo : account.getDeposit()) {
						
					}
				}
			if(customer==null) {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customer;
	}
}
