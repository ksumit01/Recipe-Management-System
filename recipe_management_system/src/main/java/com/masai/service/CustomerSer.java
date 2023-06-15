package com.masai.service;

import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface CustomerSer {

	void addCustomer(User user) throws SomeThingWentWrongException;
	void login(String username, String password) 
			throws SomeThingWentWrongException, NoRecordFoundException;
}
