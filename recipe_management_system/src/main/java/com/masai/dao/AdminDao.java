package com.masai.dao;

import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface AdminDao {
 
	void addRecipe(Recipe recipe) throws SomeThingWentWrongException;
	void updateRecipe(Recipe recipe) throws SomeThingWentWrongException,NoRecordFoundException;
	void deleteRecipe(int id) throws SomeThingWentWrongException, NoRecordFoundException;
}
