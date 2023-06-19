package com.masai.ui;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.masai.Utility.EMUtils;
import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;
import com.masai.service.AdminSer;
import com.masai.service.AdminSerImp;
import com.masai.service.RecipeLikeSer;
import com.masai.service.RecipeLikeSerImp;
import com.masai.service.RecipeSer;
import com.masai.service.RecipeSerImp;

import jakarta.persistence.EntityManager;



public class AdminUI {

    public static void addRecipe(Scanner sc) {
        System.out.println("Enter Recipe Name");
        sc.nextLine();
        String recipeName = sc.nextLine().trim();  // Read the recipe name
        
        System.out.println("Enter Ingredients");
        String ingredients = sc.nextLine().trim();

        System.out.println("Enter Preparation Steps");
        String preparationStep = sc.nextLine().trim();

        Recipe recipe = new Recipe(recipeName, ingredients, preparationStep, new Date(), new Date());

//        System.out.println(recipe);
        AdminSer adminSer = new AdminSerImp();

        try {
            adminSer.addRecipe(recipe);
            System.out.println("Recipe Added Successfully");
        } catch (SomeThingWentWrongException ex) {
            System.out.println(ex.getMessage());
        }
    }



	
	public static void updateRecipe(Scanner sc) {
		
		
			//code to take company details input
			System.out.print("Enter id ");
			int id = sc.nextInt();
			System.out.println("Enter Recipe Name");
			sc.nextLine();
			String recipeName = sc.nextLine();
			
//			sc.nextLine();
			
			System.out.println("Enter Ingredients");
			String ingredients = sc.nextLine();
			
//			sc.nextLine();
			
			System.out.println("Enter Preparation Steps");
			String preparationStep = sc.nextLine();
			
			
			//code to create Company Entity object
			Recipe recipe = new Recipe(recipeName, ingredients, preparationStep, new Date(), new Date());
			recipe.setRecipeId(id);
			
			//Create an object of Service Layer here
			AdminSer adminSer = new AdminSerImp();
			try {
				adminSer.updateRecipe(recipe);;
				System.out.println("Recipe updated successfully");
			}catch(SomeThingWentWrongException | NoRecordFoundException ex) {
				System.out.println(ex.getMessage());
			}
		
		
	}
	
	public static void deleteRecipe(Scanner sc) {
		
			System.out.print("Are you sure you want to delete your account?[y/n] ");
			char choice = sc.next().toLowerCase().charAt(0);
			if(choice == 'y') {
				System.out.println("Enter the Recipe Id you want To delete");
				int id  = sc.nextInt();
				AdminSer adminSer = new AdminSerImp();
				try {
					adminSer.deleteRecipe(id);
					System.out.println("Recipe Deleted successfully");
				}catch(SomeThingWentWrongException | NoRecordFoundException ex) {
					System.out.println(ex.getMessage());
				}
			}
	}
	
	public static void viewLikes() {
	    RecipeSer recipeSer = new RecipeSerImp();
	    RecipeLikeSer recipeLikeSer = new RecipeLikeSerImp();

	    try {
	        List<Recipe> recipeList = recipeSer.viewAllRecipe();

	        for (Recipe recipe : recipeList) {
	            if (!recipe.isDeleted()) {
	                int recipeId = recipe.getRecipeId();
	                int likeCount = recipeLikeSer.getLikesCountByRecipeId(recipeId);
	                System.out.println("Recipe: " + recipe.getRecipeName());
	                System.out.println("Likes: " + likeCount);
	                System.out.println();
	            }
	        }
	    } catch (NoRecordFoundException | SomeThingWentWrongException ex) {
	        System.out.println(ex.getMessage());
	    }
	}

	
	// Method to generate a report of top liked recipes
	

	
	public static void showReports(Scanner sc) {
		
		    int choice = 0;
		    do {
		    	
		    	System.out.println("╔══════════════════════════════╗");
		    	System.out.println("║         Menu Options         ║");
		    	System.out.println("╠══════════════════════════════╣");
		    	System.out.println("║  1. Top 5 Recipe             ║");
		    	System.out.println("║                              ║");
		    	System.out.println("║  2. Trending Recipe          ║");
		    	System.out.println("║                              ║");
		    	System.out.println("║  3. Trending Ingredients     ║");
		    	System.out.println("║                              ║");
		    	System.out.println("║  4. Likes Over Time          ║");
		    	System.out.println("║                              ║");
		    	System.out.println("║  0. Go Back To Previous Menu ║");
		    	System.out.println("╚══════════════════════════════╝");

		        
		        System.out.print("Enter selection: ");
		        choice = sc.nextInt();
		        switch (choice) {
		            case 1:
					try {
						RecipeReport.generateTopLikedRecipesReport();
					} catch (SomeThingWentWrongException | NoRecordFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                break;
		            case 2:
					try {
						RecipeReport.viewTrendingRecipesForLast7Days();
					} catch (SomeThingWentWrongException | NoRecordFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                break;
		            case 3:
					try {
						RecipeReport.generateLikesByIngredient();
					} catch (SomeThingWentWrongException | NoRecordFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		                break;
		            case 4:
					try {
						RecipeReport.generateLikesOverTime();
					} catch (SomeThingWentWrongException | NoRecordFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		                break;
//		            case 5:
//		            	
//		                break;
//		            case 6:
//		               
//		                break;
		            
		            
		            case 0:
//		            	MainRunner.adminMenu(sc);
		            	return;
//		                break;
		            default:
		                System.out.println("Invalid Selection, try again");
		        }
		    } while (choice != 0);
	

	}
}
