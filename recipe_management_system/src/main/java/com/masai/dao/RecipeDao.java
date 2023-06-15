package com.masai.dao;

import com.masai.entity.User;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeDao {

	void addUser(User user) throws SomeThingWentWrongException;
}
