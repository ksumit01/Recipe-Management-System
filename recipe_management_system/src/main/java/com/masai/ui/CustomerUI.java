package com.masai.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.masai.Utility.EMUtils;
import com.masai.entity.LoggedInUserId;
import com.masai.entity.Recipe;
import com.masai.entity.RecipeLike;
import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;
import com.masai.service.CustomerSer;
import com.masai.service.CustomerSerImp;
import com.masai.service.RecipeSer;
import com.masai.service.RecipeSerImp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CustomerUI {

	public static void customerRegistration(Scanner sc) {
		System.out.println("Enter UserName");
		String userName = sc.next();

		System.out.println("Enter Email");
		String email = sc.next();

		System.out.println("Password");
		String password = sc.next();

		User user = new User(userName, email, password, new Date(), new Date());

		CustomerSer customerSer = new CustomerSerImp();

		try {
			customerSer.addCustomer(user);
			System.out.println("User Added Successfully");
		} catch (SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}

	}

	static void userLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		try {
			CustomerSer customerService = new CustomerSerImp();
			customerService.login(username, password);
			userMenu(sc);
		} catch (NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
	}

	static void displayUserMenu() {
		CustomerSer cusSer = new CustomerSerImp();
		try {
		    System.out.println("╔═════════════════════════════════════════════════╗");
		    System.out.println("║ Welcome " + cusSer.findCustomerWithID((int) LoggedInUserId.loggedInUserId).getUsername() + " ".repeat(40 - cusSer.findCustomerWithID((int) LoggedInUserId.loggedInUserId).getUsername().length()) + "║");
		    System.out.println("╚═════════════════════════════════════════════════╝\n");
		} catch (SomeThingWentWrongException | NoRecordFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		System.out.println("╔════════════════════════════════╗");
		System.out.println("║ 1. View All Recipes            ║");
		System.out.println("║ 2. Find Recipes by Ingredients ║");
		System.out.println("║ 3. Like/Unlike a Recipe        ║");
		System.out.println("║ 4. My Liked Recipes            ║");
		System.out.println("║ 0. Logout                      ║");
		System.out.println("║ -1. Previous Menu              ║");
		System.out.println("╚════════════════════════════════╝");

	}

	public static void userMenu(Scanner sc) {
		int choice = 0;
		do {
			displayUserMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// this code is same as we have used on the admin side
				// so we are using here as it is
				viewAllRecipe();
				break;
			case 2:
				findRecipeWithGivenIngredients(sc);

				break;
			case 3:
				//code to purchase a new policy
				showRecipeOptions(sc);
				break;
			case 4:
				fetchLikedRecipesByUser();
//				
				break;
//			case 5:
//				deleteAccount(sc);
//				System.out.println("Logging you out");
//				choice = 0;
			case -1:
				return;
//				break;
			case 0:
				System.out.println("Bye 👋");
				LoggedInUserId.loggedInUserId = -1; // -1 id cannot belong to any customer
				return;
//				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	private static void findRecipeWithGivenIngredients(Scanner sc) {

		System.out.println("Enter the Ingrident ");
		String ingridents = sc.next();
		RecipeSer recipeSer = new RecipeSerImp();
		try {
			List<Recipe> recipeList = recipeSer.viewRecipesWithGivenIngredients( ingridents);
			recipeList.forEach(t -> System.out.println(t));
			likeOrUnlike();
			
		} catch (NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	public static void viewAllRecipe() {
		RecipeSer recipeSer = new RecipeSerImp();
		try {
			List<Recipe> recipeList = recipeSer.viewAllRecipe();
			recipeList.forEach(t -> System.out.println(t));
		} catch (NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	public static void likeOrUnlike() {
		
		Scanner scanner = new Scanner(System.in);
	    
	    int choice ;
	    do {
	    	System.out.println("╔══════════════════════════════════╗");
	    	System.out.println("║ You Want to Like or Save Recipe? ║");
	    	System.out.println("║ 1. Yes                           ║");
	    	System.out.println("║ 0. No                            ║");
	    	System.out.println("╚══════════════════════════════════╝");

		    
		    choice =  scanner.nextInt();
		    switch (choice) {
		        case 1:
		        	showRecipeOptions(scanner);
		            break;
		        case 0:
		        	return;
		        default:
		            System.out.println("Invalid choice!");
		    }
	    }while(choice != 0);
		
		
				
		
	}
	
	public static void showRecipeOptions(Scanner scanner) {
//	    Scanner scanner = new Scanner(System.in);
	    
	    int choice ;
	    do {
	    	System.out.println("╔═════════════════════════════════╗");
	    	System.out.println("║ 1. Add Like                     ║");
	    	System.out.println("║ 2. Remove Like                  ║");
	    	System.out.println("║ 0. Go back to the previous menu ║");
	    	System.out.println("╚═════════════════════════════════╝");

		    choice =  scanner.nextInt();
		    switch (choice) {
		        case 1:
		            addLike();
		            break;
		        case 2:
		            removeLike();
		            break;
		        case 0:
		            
		        	return;
//		            break;
		        default:
		            System.out.println("Invalid choice!");
		    }
	    }while(choice != 0);
	    
	}

	private static void removeLike() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter the recipe ID you want to remove the like from: ");
	    int recipeId = scanner.nextInt();
	    User user = null;
	    CustomerSer customerSer = new CustomerSerImp();

	    // Retrieve the User entity for the current logged-in user
	    try {
	        user = customerSer.findCustomerWithID((int) LoggedInUserId.loggedInUserId);
	    } catch (SomeThingWentWrongException | NoRecordFoundException e) {
	        System.out.println(e.getMessage());
	    }

	    RecipeSer recipeSer = new RecipeSerImp();
	    EntityManager em = null;
	    try {
	        em = EMUtils.getEntityManager();
	        Recipe recipe = recipeSer.getRecipeByID(recipeId);
	        
//	        System.out.println(recipe);
	        // Find the RecipeLike associated with the User and Recipe
	        RecipeLike recipeLike = user.getLikeByRecipe(recipe);
	        
//	        System.out.println(recipeLike);
	        
	        if (recipeLike != null) {
	            EntityTransaction et = em.getTransaction();
	            et.begin();

	            // Remove the RecipeLike entity from the User
	            user.removeLike(recipeLike);

	            recipeLike = em.merge(recipeLike);
	            // Remove the RecipeLike entity from the EntityManager
	            em.remove(recipeLike);

	            et.commit();
	            System.out.println("╔═══════════════════════════╗");
	            System.out.println("║ Like Removed Successfully ║");
	            System.out.println("╚═══════════════════════════╝");

	        } else {
	        	System.out.println("╔══════════════════════════════╗");
	        	System.out.println("║ You haven't liked this recipe before. ║");
	        	System.out.println("╚══════════════════════════════╝");

	        }

	    } catch (NoRecordFoundException | SomeThingWentWrongException ex) {
	        System.out.println(ex.getMessage());
	    } finally {
	        em.close();
	    }
	}



	private static void addLike() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter the recipe ID you want to like: ");
	    int recipeId = scanner.nextInt();
	    User user = null;
	    CustomerSer customerSer = new CustomerSerImp();

	    // Retrieve the User entity for the current logged-in user
	    try {
	        user = customerSer.findCustomerWithID((int) LoggedInUserId.loggedInUserId);
	    } catch (SomeThingWentWrongException | NoRecordFoundException e) {
	        System.out.println(e.getMessage());
	    }

	    RecipeSer recipeSer = new RecipeSerImp();
	    EntityManager em = null;
	    try {
	        em = EMUtils.getEntityManager();
	        Recipe recipe = recipeSer.getRecipeByID(recipeId);

	        // Check if the user has already liked the recipe
	        boolean alreadyLiked = false;
	        for (RecipeLike like : user.getLikes()) {
	            if (like.getRecipe().getRecipeId() == recipeId) {
	                alreadyLiked = true;
	                break;
	            }
	        }

	        if (alreadyLiked) {
	            System.out.println("You have already liked this recipe before.");
	            return;
	        }

	        // Check if the like associated with the recipe is deleted
	        for (RecipeLike like : recipe.getLikes()) {
	            if (like.getUser().getUserId() == user.getUserId()) {
	                if (like.getUser().isDeleted()) {
	                    like.getUser().setIsdeleted(true); // Undelete the like
	                    System.out.println("Successfully undeleted the like.");
	                }
	                return;
	            }
	        }

	        // Create a new RecipeLike and associate it with the User and Recipe
	        RecipeLike recipeLike = new RecipeLike(user, recipe, new Date());
	        user.addLike(recipeLike);
	        recipe.getLikes().add(recipeLike);

	        EntityTransaction et = em.getTransaction();
	        et.begin();

	        // Since the User and Recipe entities are already retrieved and managed, you don't need to persist them again.
	        // However, you need to update the RecipeLike entity to persist the like association.

	        em.persist(recipeLike);
	        et.commit();
	        System.out.println("Like Added Successfully");
	    } catch (NoRecordFoundException | SomeThingWentWrongException ex) {
	        System.out.println(ex.getMessage());
	    } finally {
	        em.close();
	    }
	}



	
	private static void fetchLikedRecipesByUser() {
	    User user = null;
	    CustomerSer customerSer = new CustomerSerImp();

	    // Retrieve the User entity for the current logged-in user
	    try {
	        user = customerSer.findCustomerWithID((int) LoggedInUserId.loggedInUserId);
	    } catch (SomeThingWentWrongException | NoRecordFoundException e) {
	        System.out.println(e.getMessage());
	        return;
	    }

	    Set<Integer> uniqueRecipeIds = new HashSet<>();
	    List<Recipe> uniqueLikedRecipes = new ArrayList<>();

	    for (RecipeLike recipeLike : user.getLikes()) {
	        Recipe recipe = recipeLike.getRecipe();
	        if (recipe.isDeleted()) {
	            continue; // Skip deleted recipes
	        }
	        if (!recipeLike.getRecipe().isDeleted() && !recipeLike.getUser().isIsdeleted() && !uniqueRecipeIds.contains(recipe.getRecipeId())) {
	            uniqueLikedRecipes.add(recipe);
	            uniqueRecipeIds.add(recipe.getRecipeId());
	        }
	    }

	    if (!uniqueLikedRecipes.isEmpty()) {
	        System.out.println("Recipes liked by the user:");

	        for (Recipe recipe : uniqueLikedRecipes) {
	            System.out.println("Recipe ID: " + recipe.getRecipeId());
	            System.out.println("Recipe Name: " + recipe.getRecipeName());
	            System.out.println("Ingredients: " + recipe.getIngredients());
	            System.out.println("Preparation Steps: " + recipe.getPreparationSteps());
	            System.out.println();
	        }
	    } else {
	        System.out.println("No recipes liked by the user.");
	    }
	}



}
