package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class A1Jedi {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ItemList mainList = new ItemList();
		FinalPrintObject finalResults = new FinalPrintObject();
		FinalJediObject finalJedi = new FinalJediObject();

		// Your code follows here.

		// inital scan to see how many items will be in the list
		int initalItemCheck = scan.nextInt();

		// creating two arrays one to represent the name and one for the price
		// using the variable from above to keep the program dynamic
		mainList.itemDescription = new String[initalItemCheck];
		mainList.itemPrices = new double[initalItemCheck];


		int loopVariable; // not sure why I had to do this...
		for (loopVariable = 0; loopVariable < initalItemCheck; ++loopVariable) {
			String gItem = scan.next(); // knowing the format the data is going to be in helps.
			double gPrice = scan.nextDouble(); // yeah what he said

			// "Appending" (if it could be called that)
			mainList.itemDescription[loopVariable] = gItem;
			mainList.itemPrices[loopVariable] = gPrice;
			mainList.actualList.put(gItem, gPrice);
			Set<Map.Entry<String, Double>> set = mainList.actualList.entrySet();
		}

		// We have the first part of the data saved now. (The list and prices).
		// Now I need to work on getting the next data check working

		// Thats how we are going to do it
		int conditionalCheck1 = scan.nextInt();
		Customer[] customers = new Customer[conditionalCheck1];

		// I know this looks long winded but all we are doing here is getting to block of code that has our
		// customer information such as the the item and the amount of that item.
		// we read the conditional check that tell us how many customers we have
		// once we know that we need to check how many items that individual customer has.
		// lastly in the final lines of the first for loop, we are creating a new array of Strings and Integers
		// and instantiating them based on the individual customer and how many different items they plan to purchase
		for (loopVariable = 0; loopVariable < conditionalCheck1; loopVariable++) {
			customers[loopVariable] = new Customer();
			customers[loopVariable].fName = scan.next();
			customers[loopVariable].lName = scan.next();

			int conditionalCheck2 = scan.nextInt();

			customers[loopVariable].shoppingCartNumber = conditionalCheck2;
			customers[loopVariable].itemDescription = new String[conditionalCheck2];
			customers[loopVariable].quantityOfItem = new int[conditionalCheck2];
			int x; // NSNullPointer if you use loopVariable

			// Once we make it here, we can just loop through the data becasue we know exactly how the data
			// will be formatted.  once this loop is complete set up a hash map so that way I can
			// reference the ItemList later
			for (x = 0; x < conditionalCheck2; x++) {
				int itemQuantity = scan.nextInt();
				String itemDescription = scan.next();
				// Creating a check so I can remove any duplicates and then update the list
				if (customers[loopVariable].shoppingCartList.containsKey(itemDescription)){
					int quantity = customers[loopVariable].shoppingCartList.get(itemDescription) + itemQuantity;
					customers[loopVariable].itemDescription[x] = itemDescription;
					customers[loopVariable].shoppingCartList.put(itemDescription, quantity);
					Set<Map.Entry<String, Integer>> set = customers[loopVariable].shoppingCartList.entrySet();
				} else {
					customers[loopVariable].quantityOfItem[x] = itemQuantity;
					customers[loopVariable].itemDescription[x] = itemDescription;
					customers[loopVariable].shoppingCartList.put(itemDescription, itemQuantity);
					Set<Map.Entry<String, Integer>> set = customers[loopVariable].shoppingCartList.entrySet();
				}

			}
		}

		scan.close();

		// Now we have all the data saved
		// Array of customers
		// [0, 1, 2]
		// each index contains an individual customer that inherits from the Customer Class
		// and now all we need to do is figure out how to calculate the min max and average

		for (loopVariable = 0; loopVariable < customers.length; loopVariable++) {
			int x;
			customers[loopVariable].comparisonMoneySpent = new double[customers[loopVariable].shoppingCartNumber];
			for (x = 0; x < customers[loopVariable].shoppingCartList.size(); x++) {
				String itemDescription = customers[loopVariable].itemDescription[x];
				int amountOfItem = customers[loopVariable].shoppingCartList.get(itemDescription);
				double price = mainList.actualList.get(itemDescription);
				double total = (price * amountOfItem);
				customers[loopVariable].comparisonMoneySpent[x] = total;
			}
		}
		// This loop has now updated the array in each customer class, so that we can now run
		// calculations on these arrays and then get them displaying correctly

		// Totaling up the comparison array to get a single value so i can sort the data
		for (loopVariable = 0; loopVariable < customers.length; loopVariable++) {
			int x;
			for (x = 0; x < customers[loopVariable].comparisonMoneySpent.length; x++) {
				double total = customers[loopVariable].comparisonMoneySpent[x];
				customers[loopVariable].actualTotalSpent += total;
			}
		}

		double currentMaxValue = 100000000;
		// getting max value and customer info
		for (loopVariable = 0; loopVariable < customers.length; loopVariable++) {
			if (customers[loopVariable].actualTotalSpent > currentMaxValue) {
				currentMaxValue = customers[loopVariable].actualTotalSpent;
				String format = String.format("%.2f", currentMaxValue);
				String firstname = customers[loopVariable].fName;
				String lastname = customers[loopVariable].lName;
				finalResults.biggestSpender = firstname + " " + lastname;
				finalResults.biggestSpenderAmount = format;
			}
		}

		double currentMinValue = 0;

		// getting lowest customer info
		for (loopVariable = 0; loopVariable < customers.length; loopVariable++) {
			if (customers[loopVariable].actualTotalSpent < currentMinValue) {
				currentMinValue = customers[loopVariable].actualTotalSpent;
				String format = String.format("%.2f", currentMinValue);
				String firstname = customers[loopVariable].fName;
				String lastname = customers[loopVariable].lName;
				finalResults.lowerstSpender = firstname + " " + lastname;
				finalResults.lowestSpenderAmount = format;
			}
		}
		// creating the average for each customer
		for (loopVariable = 0; loopVariable < customers.length; loopVariable++) {
			double customerAverage = customers[loopVariable].actualTotalSpent;
			finalResults.average += customerAverage;
		}


		finalResults.average = finalResults.average / customers.length;
		String formattedAverage = String.format("%.2f", finalResults.average);
		finalResults.averagePrint = formattedAverage;

		finalJedi.numberOfItems = new int[conditionalCheck1];
		finalJedi.itemDescription = new String[conditionalCheck1];

		// adding code for jedi
		// creating a loop to go through and hash map and add the values purchased
		// so the result will be how many of each item was purchased
		finalJedi.numberOfCustomers = new int[mainList.actualList.size()];
		for (loopVariable=0; loopVariable<mainList.itemDescription.length; loopVariable++){
			int runningTotal = 0;
			int runningCustomerCount = 0;
			String description = mainList.itemDescription[loopVariable];
			int x;
			for (x=0; x<customers.length; x++) {
				boolean checker = customers[x].shoppingCartList.containsKey(description);
				if (checker == true) {
					int quantity = customers[x].shoppingCartList.get(description);
					runningTotal += quantity;
					runningCustomerCount += 1;
				} else {
					runningTotal += 0;
				}
			}
			finalJedi.customersList.put(description, runningTotal);
			finalJedi.numberOfCustomers[loopVariable] = runningCustomerCount;
			Set<Map.Entry<String, Integer>> set = finalJedi.customersList.entrySet();

			// this should work because we are parsing back through the data we saved in the same order
		}
		// now we have our hashmap of total amount purchased to the exact item description and we have an array
		// that is keeping track of how many customers have purchased each item

		for (loopVariable=0;loopVariable<1; loopVariable++){
			int x;
			for(x=0; x<mainList.itemDescription.length; x++){
				String description = mainList.itemDescription[x];
				int amount = finalJedi.customersList.get(description);
				if (amount == 0) {
					System.out.println("No customers bought " + description);
				} else {
					System.out.println(finalJedi.numberOfCustomers[x] + " customers bought " + finalJedi.customersList.get(description) + " " + description);
				}
			}
		}
	}
}
