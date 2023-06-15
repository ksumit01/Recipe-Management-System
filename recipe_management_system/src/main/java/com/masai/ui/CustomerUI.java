package com.masai.ui;

import java.util.Date;
import java.util.Scanner;

import com.masai.entity.LoggedInUserId;
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
		
		
		
		
		
//		Recipe recipe = new Recipe(recipeName, ingredients, preparingSteps, new Date(), new Date(), new HashSet<>());
//		Like like = new Like( null,recipe,new Date());
//		recipe.getLikes().add(like);
//		User user = new User("theAlchemist", "sk@gmail.com", "123", new Date(), new Date(), role.getRoleName(), new HashSet<>());
		User user = new User(userName, email, password, new Date(), new Date());
		
//		role.getUsers().add(user);
		

//		user.getLikes().add(like);
//		like.setUser(user);
//		role.getUsers().add(user);
		//Create an object of Service Layer here
		RecipeSer recipeSer = new RecipeSerImp();
		
		try {
			System.out.println(recipeSer.addUser(user));
		}catch(SomeThingWentWrongException ex) {
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
		}catch(NoRecordFoundException | SomeThingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void displayUserMenu() {
		System.out.println("1. View All Recipe");
		
		System.out.println("2. Filter And Find Recipe");
		System.out.println("3. Like Or Bookmark ");
		System.out.println("4. Renew Existing Policy");
		System.out.println("5. Delete Account");
		System.out.println("0. Logout");
	}
	
	public static void userMenu(Scanner sc) {
		int choice = 0;
		do {
			displayUserMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
    		switch(choice) {
//    		case 1:
//				//this code is same as we have used on the admin side
//				//so we are using here as it is
//				viewRecipe();
//				break;
//			case 2:
//				filterAndFind(sc);
//				
//				break;
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
				
    			case 0:
    				LoggedInUserId.loggedInUserId = -1;	//-1 id cannot belong to any customer
    				System.out.println("Bye Bye User");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);
	}

	
}
