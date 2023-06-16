package com.masai.dao;

import java.util.Date;

import com.masai.Utility.EMUtils;

import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminDaoImp implements AdminDao{

	@Override
	public void addRecipe(Recipe recipe) throws SomeThingWentWrongException {
		
		EntityManager em = null;
		
		
		try {
			em = EMUtils.getEntityManager();
			
			//check if company with same name exists
			Query query = em.createQuery("SELECT count(c) FROM Recipe c WHERE recipeName = :recipeName");
			query.setParameter("recipeName", recipe.getRecipeName());
			if((Long)query.getSingleResult() > 0) {
				//you are here means company with given name exists so throw exceptions
				throw new SomeThingWentWrongException("Recipe already exists with name " + recipe.getRecipeName());
			}
			
			//you are here means no company with given name
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(recipe);
			et.commit();
			
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Not Able to Add recipe, Try Again");
		}finally {
			em.close();
		}
	}

	@Override
	public void updateRecipe(Recipe recipe) throws SomeThingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			//check if company with company with given id exists
			Recipe recipeFromDB = em.find(Recipe.class, recipe.getRecipeId());
			if(recipeFromDB == null)
				throw new NoRecordFoundException("No Recipe found with the given id " + recipe.getRecipeId());

			//You are here means company exists with given id
			//check if company is to be renamed
			if(!recipeFromDB.getRecipeName().equals(recipe.getRecipeName())) {
				//you are here means company is to be renamed, check for no existing company with new name.
				//check if company with same name exists
				Query query = em.createQuery("SELECT count(c) FROM Recipe c WHERE recipeName = :recName");
				query.setParameter("recName", recipe.getRecipeName());
				if((Long)query.getSingleResult() > 0) {
					//you are here means company with given name exists so throw exceptions
					throw new SomeThingWentWrongException("Recipe already exists with name " + recipe.getRecipeName());
				}
			}
			
			//proceed for update operation
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			recipeFromDB.setRecipeName(recipe.getRecipeName());
			recipeFromDB.setIngredients(recipe.getIngredients());
			recipeFromDB.setPreparationSteps(recipe.getPreparationSteps());
			recipeFromDB.setUpdatedAt(new Date());
			
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		
	}

	@Override
	public void deleteRecipe(int id) throws SomeThingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Recipe recipe = em.find(Recipe.class, id);
			
			if(recipe==null) {
				throw new NoRecordFoundException("Recipe with the given Id does not Exist");
			}
			EntityTransaction et = em.getTransaction();
			et.begin();
			recipe.setDeleted(true);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		
	}

}
