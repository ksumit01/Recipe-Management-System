package com.masai.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.masai.Utility.EMUtils;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class RecipeReport {

	public static void generateTopLikedRecipesReport() throws SomeThingWentWrongException, NoRecordFoundException {
	    EntityManager entityManager = EMUtils.getEntityManager();
	    String jpql = "SELECT r.recipeId, r.recipeName, COUNT(rl.likeId) AS likesCount " +
	            "FROM Recipe r " +
	            "LEFT JOIN r.recipeLikes rl " +
	            "GROUP BY r.recipeId, r.recipeName " +
	            "ORDER BY likesCount DESC " +
	            "LIMIT 5";

	    List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();

	    if (results == null || results.isEmpty()) {
	        throw new NoRecordFoundException("No records found for top liked recipes.");
	    }

	    try {
	        for (Object[] result : results) {
	            int recipeId = (int) result[0];
	            String recipeName = (String) result[1];
	            Long likesCount = (Long) result[2];

	            if (likesCount > 0) {
	                // Process the results as needed
	                System.out.println(
	                        "Recipe ID: " + recipeId + ", Recipe Name: " + recipeName + ", Likes Count: " + likesCount);
	            }
	        }
	    } catch (IllegalArgumentException ex) {
	        throw new SomeThingWentWrongException("Something went wrong while generating top liked recipes report.");
	    }
	}

	public static void viewTrendingRecipesForLast7Days() throws SomeThingWentWrongException, NoRecordFoundException {
	    EntityManager entityManager = EMUtils.getEntityManager();
	    String jpql = "SELECT r.recipeId, r.recipeName, COUNT(rl.likeId) AS likesCount " +
	            "FROM Recipe r " +
	            "LEFT JOIN r.recipeLikes rl " +
	            "WHERE rl.createdAt >= :startDate " +
	            "GROUP BY r.recipeId, r.recipeName " +
	            "ORDER BY likesCount DESC";

	    Date startDate = calculateStartDate(); // Calculate the start date (e.g., 7 days ago)

	    Query query = entityManager.createQuery(jpql);
	    query.setParameter("startDate", startDate);
	    query.setMaxResults(5); // Limit the results to the top 5 trending recipes

	    List<Object[]> results = query.getResultList();

	    if (results == null || results.isEmpty()) {
	        throw new NoRecordFoundException("No records found for trending recipes in the last 7 days.");
	    }

	    try {
	        for (Object[] result : results) {
	            int recipeId = (int) result[0];
	            String recipeName = (String) result[1];
	            Long likesCount = (Long) result[2];

	            if (likesCount > 0) {
	                // Process the results as needed
	                System.out.println(
	                        "Recipe ID: " + recipeId + ", Recipe Name: " + recipeName + ", Likes Count: " + likesCount);
	            }
	        }
	    } catch (IllegalArgumentException e) {
	        throw new SomeThingWentWrongException("Something went wrong while viewing trending recipes for the last 7 days.");
	    }
	}


	

	public static void generateLikesByIngredient() throws SomeThingWentWrongException, NoRecordFoundException {
	    EntityManager entityManager = EMUtils.getEntityManager();
	    String jpql = "SELECT r.ingredients, COUNT(rl.likeId) AS likesCount " +
	            "FROM Recipe r " +
	            "LEFT JOIN r.recipeLikes rl " +
	            "GROUP BY r.ingredients " +
	            "ORDER BY likesCount DESC";

	    Query query = entityManager.createQuery(jpql);

	    List<Object[]> results = query.getResultList();

	    if (results == null || results.isEmpty()) {
	        throw new NoRecordFoundException("No records found for likes by ingredient.");
	    }

	    try {
	        List<String> allIngredients = new ArrayList<>();
	        for (Object[] result : results) {
	            String ingredients = (String) result[0];
	            Long likesCount = (Long) result[1];

	            if (likesCount > 0 && ingredients != null) {
	                // Process the results as needed
	                System.out.println("Ingredients: " + ingredients + ", Likes Count: " + likesCount);

	                String[] ingredientArray = ingredients.split("\\s*,\\s*|\\s*or\\s*");
	                allIngredients.addAll(Arrays.asList(ingredientArray));
	            }
	        }

	        // Find the three main ingredients
	        List<String> mainIngredients = findMainIngredients(allIngredients);

	        // Print the three main ingredients
	        System.out.println("Three Main Ingredients: " + mainIngredients);
	    } catch (IllegalArgumentException e) {
	        throw new SomeThingWentWrongException("Something went wrong while generating the likes by ingredient report.");
	    }
	}

	private static List<String> findMainIngredients(List<String> ingredientsList) {
	    Map<String, Integer> ingredientCountMap = new HashMap<>();

	    // Count the occurrences of each ingredient
	    for (String ingredient : ingredientsList) {
	        ingredientCountMap.put(ingredient.trim(), ingredientCountMap.getOrDefault(ingredient.trim(), 0) + 1);
	    }

	    // Sort the ingredients based on their occurrence count in descending order
	    List<Map.Entry<String, Integer>> sortedIngredients = new ArrayList<>(ingredientCountMap.entrySet());
	    sortedIngredients.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    // Get the top three ingredients
	    List<String> mainIngredients = new ArrayList<>();
	    for (int i = 0; i < Math.min(3, sortedIngredients.size()); i++) {
	        mainIngredients.add(sortedIngredients.get(i).getKey());
	    }

	    return mainIngredients;
	}

	
	public static void generateLikesOverTime() throws SomeThingWentWrongException, NoRecordFoundException {
	    EntityManager entityManager = EMUtils.getEntityManager();
	    String jpql = "SELECT DATE(rl.createdAt) AS likeDate, COUNT(rl.likeId) AS likesCount " +
	            "FROM RecipeLike rl " +
	            "GROUP BY likeDate " +
	            "ORDER BY likeDate";

	    Query query = entityManager.createQuery(jpql);

	    List<Object[]> results = query.getResultList();

	    if (results == null) {
	        throw new NoRecordFoundException("No records found for likes over time.");
	    }

	    try {
	        for (Object[] result : results) {
	            LocalDate likeDate = ((java.sql.Date) result[0]).toLocalDate();
	            Long likesCount = (Long) result[1];

	            // Process the results as needed
	            System.out.println("╔══════════════════════════════════════════════════════════╗");
	            System.out.printf("║ Date: %-12s Likes Count: %-4d                           ║%n", likeDate, likesCount);
	            System.out.println("╚══════════════════════════════════════════════════════════╝");


	        }

	        if (results.isEmpty()) {
	            throw new NoRecordFoundException("No likes found over time.");
	        }
	    } catch (IllegalArgumentException e) {
	        throw new SomeThingWentWrongException("Something went wrong while generating the likes over time report.");
	    }
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
