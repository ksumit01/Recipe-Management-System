package com.masai.ui;

import java.util.Date;
import java.util.Scanner;


import com.masai.entity.Recipe;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;
import com.masai.service.AdminSer;
import com.masai.service.AdminSerImp;



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

        System.out.println(recipe);
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
		
	}
	
	public static void viewReview() {
		
	}
}
