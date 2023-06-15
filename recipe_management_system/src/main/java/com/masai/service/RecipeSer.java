package com.masai.service;

import com.masai.entity.User;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeSer {
	String addUser(User user) throws SomeThingWentWrongException;
}
