package com.masai.service;

import com.masai.dao.RecipeDao;
import com.masai.dao.RecipeDaoImp;
import com.masai.entity.User;
import com.masai.exception.SomeThingWentWrongException;

public class RecipeSerImp implements RecipeSer{

	RecipeDao recipeDao = new RecipeDaoImp();
	@Override
	public String addUser(User user) throws SomeThingWentWrongException {
		recipeDao.addUser(user);
		return "User Added SuccessFully";
	}

}
