package com.masai.service;

import java.util.List;

import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeSer {
	
	 List<Recipe> viewAllRecipe() throws SomeThingWentWrongException,NoRecordFoundException;
	 List<Recipe> viewRecipesWithGivenIngredients(String ingredients) throws SomeThingWentWrongException,NoRecordFoundException;
}
