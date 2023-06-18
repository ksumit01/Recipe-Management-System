package com.masai.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.masai.Utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class RecipeReport {
    
	public static void generateTopLikedRecipesReport() {
		EntityManager entityManager = EMUtils.getEntityManager();
	    String jpql = "SELECT r.recipeId, r.recipeName, COUNT(rl.likeId) AS likesCount " +
	                  "FROM Recipe r " +
	                  "LEFT JOIN r.recipeLikes rl " +
	                  "GROUP BY r.recipeId, r.recipeName " +
	                  "ORDER BY likesCount DESC"+
	                  "LIMIT 2";

	    List<Object[]> results = entityManager.createQuery(jpql, Object[].class)
	            .setMaxResults(10)
	            .getResultList();

	    for (Object[] result : results) {
	        int recipeId = (int) result[0];
	        String recipeName = (String) result[1];
	        Long likesCount = (Long) result[2];

	        // Process the results as needed
	        System.out.println("Recipe ID: " + recipeId + ", Recipe Name: " + recipeName + ", Likes Count: " + likesCount);
	    }
	}
	
	
	public static void viewTrendingRecipesForLast7Days() {
		
		EntityManager entityManager = EMUtils.getEntityManager();
		String jpql = "SELECT r.recipeId, r.recipeName, COUNT(rl.likeId) AS likesCount " +
                "FROM Recipe r " +
                "LEFT JOIN r.likes rl " +
                "WHERE rl.createdAt >= :startDate " +
                "GROUP BY r.recipeId, r.recipeName " +
                "ORDER BY likesCount DESC";

  Date startDate = calculateStartDate(); // Calculate the start date (e.g., 7 days ago)

  Query query = entityManager.createQuery(jpql);
  query.setParameter("startDate", startDate);

  List<Object[]> results = query.getResultList();

  for (Object[] result : results) {
      Long recipeId = (Long) result[0];
      String recipeName = (String) result[1];
      Long likesCount = (Long) result[2];

      // Process the results as needed
      System.out.println("Recipe ID: " + recipeId + ", Recipe Name: " + recipeName + ", Likes Count: " + likesCount);
	}
}
	
	public static void overAllLikes(){
		
	}
	
	public static void generateLikesByIngredient() {
	    // Code to generate the likes by ingredient report
	}
	
	public static void generateLikesOverTime() {
	    // Code to generate the likes over time report
	}

	
	
	private static Date calculateStartDate() {
	    // Get the current date
	    Date currentDate = new Date();

	    // Create a calendar instance and set it to the current date
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(currentDate);

	    // Subtract 7 days from the current date
	    calendar.add(Calendar.DAY_OF_MONTH, -7);

	    // Get the resulting date as the start date
	    Date startDate = calendar.getTime();

	    return startDate;
	}


}
