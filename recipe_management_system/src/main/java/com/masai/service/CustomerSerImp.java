package com.masai.service;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImp;
import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public class CustomerSerImp implements CustomerSer{

	CustomerDao cusDao = new CustomerDaoImp();
	@Override
	public void addCustomer(User user) throws SomeThingWentWrongException {
		cusDao.addCustomer(user);
		
	}
	@Override
	public void login(String username, String password) throws SomeThingWentWrongException, NoRecordFoundException {
		
		cusDao.login(username, password);
	}
	@Override
	public User findCustomerWithID(int id) throws SomeThingWentWrongException, NoRecordFoundException {
		
		return cusDao.findCustomerWithID(id);
	}
	
}
