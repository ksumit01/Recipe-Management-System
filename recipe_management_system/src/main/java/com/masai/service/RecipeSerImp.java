package com.masai.service;

import java.util.List;

import com.masai.dao.RecipeDao;
import com.masai.dao.RecipeDaoImp;
import com.masai.entity.Recipe;
import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public class RecipeSerImp implements RecipeSer{

	RecipeDao recipeDao = new RecipeDaoImp();
	@Override
	public List<Recipe> viewAllRecipe() throws SomeThingWentWrongException, NoRecordFoundException {
		
		return recipeDao.viewAllRecipe();
	}
	@Override
	public List<Recipe> viewRecipesWithGivenIngredients(String ingredients) throws SomeThingWentWrongException, NoRecordFoundException {
		return recipeDao.viewRecipesWithGivenIngredients(ingredients);
		 
	}
	@Override
	public Recipe getRecipeByID(int id) throws SomeThingWentWrongException, NoRecordFoundException {
		
		return recipeDao.getRecipeByID(id);
	}

	

}
