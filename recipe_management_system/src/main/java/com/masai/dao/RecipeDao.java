package com.masai.dao;

import java.util.List;

import com.masai.entity.Recipe;

import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeDao {

	List<Recipe> viewAllRecipe() throws SomeThingWentWrongException,NoRecordFoundException;
	List<Recipe> viewRecipesWithGivenIngredients(String ingredients) throws SomeThingWentWrongException,NoRecordFoundException;
	Recipe getRecipeByID(int id) throws SomeThingWentWrongException,NoRecordFoundException;
}
