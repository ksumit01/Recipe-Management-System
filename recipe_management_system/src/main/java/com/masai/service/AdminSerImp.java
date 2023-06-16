package com.masai.service;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImp;
import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public class AdminSerImp implements AdminSer{
	
	AdminDao adminDao = new AdminDaoImp();
	
	@Override
	public void addRecipe(Recipe recipe) throws SomeThingWentWrongException {
		adminDao.addRecipe(recipe);
		
	}

	@Override
	public void updateRecipe(Recipe recipe) throws SomeThingWentWrongException,NoRecordFoundException {
		adminDao.updateRecipe(recipe);
		
	}

	@Override
	public void deleteRecipe(int id) throws SomeThingWentWrongException, NoRecordFoundException {
		adminDao.deleteRecipe(id);
		
	}
	
	
}
