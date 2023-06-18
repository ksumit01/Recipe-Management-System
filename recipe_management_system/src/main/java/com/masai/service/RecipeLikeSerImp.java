package com.masai.service;

import com.masai.dao.RecipeLikeDao;
import com.masai.dao.RecipeLikeDaoImp;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public class RecipeLikeSerImp implements RecipeLikeSer{

	RecipeLikeDao recipeDao = new RecipeLikeDaoImp();
	@Override
	public int getLikesCountByRecipeId(int recipeId) throws SomeThingWentWrongException, NoRecordFoundException {
		
		return recipeDao.getLikesCountByRecipeId(recipeId);
	}

}
