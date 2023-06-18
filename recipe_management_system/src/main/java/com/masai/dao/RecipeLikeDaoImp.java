package com.masai.dao;

import com.masai.Utility.EMUtils;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class RecipeLikeDaoImp implements RecipeLikeDao{

	@Override
	public int getLikesCountByRecipeId(int recipeId) throws SomeThingWentWrongException, NoRecordFoundException {
		
		EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            Query query = em.createQuery("SELECT COUNT(rl) FROM RecipeLike rl WHERE rl.recipe.recipeId = :recipeId");
            query.setParameter("recipeId", recipeId);
            return ((Number) query.getSingleResult()).intValue();
        } catch (IllegalArgumentException ex) {
            throw new SomeThingWentWrongException("Unable to process request, try again later");
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}

}
