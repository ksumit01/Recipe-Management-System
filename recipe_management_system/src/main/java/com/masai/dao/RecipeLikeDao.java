package com.masai.dao;

import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeLikeDao {

	int getLikesCountByRecipeId(int recipeId) throws SomeThingWentWrongException,NoRecordFoundException;
}
