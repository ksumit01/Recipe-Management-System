package com.masai.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainRunner {

	static void displayAdminMenu() {
	    System.out.println("╔══════════════════════════════╗");
	    System.out.println("║         Admin Menu           ║");
	    System.out.println("╠══════════════════════════════╣");
	    System.out.println("║  1. Add Recipes              ║");
	    System.out.println("║  2. Update the Recipes       ║");
	    System.out.println("║  3. Delete Recipes           ║");
	    System.out.println("║  4. View All Recipe Likes    ║");
	    System.out.println("║  5. View Recipe Review       ║");
	    System.out.println("║ -1. Previous Menu            ║");
	    System.out.println("║  0. Logout                   ║");
	    System.out.println("╚══════════════════════════════╝");
	}



	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch (choice) {
    			case 1:
    				AdminUI.addRecipe(sc);
    				break;
    			case 2:
    				AdminUI.updateRecipe(sc);
    				break;
    			case 3:
    				AdminUI.deleteRecipe(sc);
    				break;
    			case 4:
    				AdminUI.viewLikes();
    				break;
    			case 5:
    				AdminUI.showReports(sc);
    				break;
    			case -1:
    				System.out.println("Bye Bye Admin");
    				MainRunner.main(new String[0]);
    				break;
			case 0:
				System.out.println("Bye Bye Admin");
				MainRunner.main(new String[0]);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	static void adminLogin(Scanner sc) {
	
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		if (username.equals("admin") && password.equals("admin")) {
			adminMenu(sc);
		} else {
			System.out.println("Invalid Username of Password");
		}
	}

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int choice = 0;

	    do {
	    	System.out.println("╔════════════════════════════════════════════════╗");
	    	System.out.println("║       Welcome To Recipe Management System      ║");
	    	System.out.println("╠════════════════════════════════════════════════╣");
	    	System.out.println("║ 1. Admin Login                                 ║");
	    	System.out.println("║ 2. Customer Login                              ║");
	    	System.out.println("║ 3. Customer Registration                       ║");
	    	System.out.println("║ 0. Exit                                        ║");
	    	System.out.println("╚════════════════════════════════════════════════╝");


	        System.out.println("Enter Selection: ");
	       
//	        String input = sc.nextLine();

//	        choice = Integer.parseInt(input);
	         choice = sc.nextInt();
	        try {

	            switch (choice) {
	                case 1:
	                    adminLogin(sc);
	                    break;
	                case 2:
	                    CustomerUI.userLogin(sc);
	                    break;
	                case 3:
	                    CustomerUI.customerRegistration(sc);
	                    break;
	                case 0:
	                    System.out.println("Thanks for using the services");
	                    break;
	                default:
	                    System.out.println("Invalid Selection, try again");
	            }
	        } catch (Exception e) {
//	            System.out.println("Invalid input. Please enter a valid integer.\n");
	            System.out.println(e.getMessage());
	        }
	    } while (choice != 0);

//	    sc.close();
	}



}
