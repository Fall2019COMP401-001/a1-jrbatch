package a1;

import java.util.Scanner;



public class A1Novice {





	public static void main(String[] args) {

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