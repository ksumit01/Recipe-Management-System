package com.masai.ui;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.masai.entity.LoggedInUserId;
import com.masai.entity.Recipe;
import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;
import com.masai.service.CustomerSer;
import com.masai.service.CustomerSerImp;
import com.masai.service.RecipeSer;
import com.masai.service.RecipeSerImp;

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
		System.out.println("1. View All Recipe");
		System.out.println("2. Find Recipe With Given Ingredients");
		System.out.println("3. Like Or Bookmark ");
		System.out.println("0. Logout");
		System.out.println("-1. Previous Menu");
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
//			case 3:
//				//code to purchase a new policy
//				likeOrBookmark(sc);
//				break;
//			case 4:
//				//code to view policies purchased by logged in user
//				
//				break;
//			case 5:
//				deleteAccount(sc);
//				System.out.println("Logging you out");
//				choice = 0;
			case -1:
				MainRunner.main(new String[0]);
				break;
			case 0:
				LoggedInUserId.loggedInUserId = -1; // -1 id cannot belong to any customer
				System.out.println("Bye Bye User");
				break;
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
		} catch (NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	private static void viewAllRecipe() {
		RecipeSer recipeSer = new RecipeSerImp();
		try {
			List<Recipe> recipeList = recipeSer.viewAllRecipe();
			recipeList.forEach(t -> System.out.println(t));
		} catch (NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
