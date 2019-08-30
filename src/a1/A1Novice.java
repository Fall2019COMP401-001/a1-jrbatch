package a1;

import java.util.Scanner;

//class Person {
//    String firstName;
//    String lastName;
//
//    int numberOfItemsInList;
//
//    int[] quantityOfItem;
//    String[] itemDescription;
//    double[] itemPrice;
//
//    double totalSpent;
//    String totalSpentPrint;
//    static double calculateTotalSpent(int amount, double price) {
//        double total = amount * price;
//        return total;
//    }
//
//}

public class A1Novice {



	public static void main(String[] args) {
		System.out.println("***** Scanner a1/jrbatch Novice*****");
		System.out.println("Java version: " + System.getProperty("java.version"));
		System.out.println("Onyen: jrbatch");
		System.out.println("Justin Batchelor");
		System.out.println("08/26/2019 ");
		System.out.println("COMP 401.001.FA19");
		System.out.println();
		System.out.println("Please enter your list:");

		// Your code follows here.
		int loopVariable;
		Scanner scan = new Scanner(System.in);

		int initalCustomerCheck = scan.nextInt();

		Person[] people = new Person[initalCustomerCheck];

		for (loopVariable = 0; loopVariable < people.length; loopVariable++) {
			Person person = new Person();
			people[loopVariable] = person;
		}

		for (loopVariable = 0; loopVariable < people.length; loopVariable++) {
			String firstName = scan.next();
			String lastName = scan.next();
			int totalItems = scan.nextInt();
			people[loopVariable].firstName = firstName;
			people[loopVariable].lastName = lastName;
			people[loopVariable].numberOfItemsInList = totalItems;
			people[loopVariable].quantityOfItem = new int[totalItems];
			people[loopVariable].itemDescription = new String[totalItems];
			people[loopVariable].itemPrice = new double[totalItems];
			int x;
			for (x = 0; x < totalItems; x++) {
				int quantiy = scan.nextInt();
				String description = scan.next();
				double price = scan.nextDouble();
				people[loopVariable].quantityOfItem[x] = quantiy;
				people[loopVariable].itemDescription[x] = description;
				people[loopVariable].itemPrice[x] = price;
			}


		}
		scan.close();
		for (loopVariable=0; loopVariable<people.length; loopVariable++) {
			int x;
			for (x=0; x<people[loopVariable].numberOfItemsInList; x++){
				double totalSpent = (people[loopVariable].quantityOfItem[x] * people[loopVariable].itemPrice[x]);
				people[loopVariable].totalSpent += totalSpent;
			}
		}


		for (loopVariable=0; loopVariable<people.length; loopVariable++){
			// format code here
			String format = String.format("%.2f", people[loopVariable].totalSpent);
			people[loopVariable].totalSpentPrint = format;
			System.out.println(people[loopVariable].firstName.charAt(0) + ". " + people[loopVariable].lastName + ": " + people[loopVariable].totalSpentPrint);
		}
	}


}