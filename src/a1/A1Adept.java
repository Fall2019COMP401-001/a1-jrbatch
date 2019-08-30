package a1;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.*;

//class Customer {
//    String fName;
//    String lName;
//    int shoppingCartNumber;
//
//    String[] itemDescription;
//    int[] quantityOfItem;
//    HashMap<String, Integer> shoppingCartList = new HashMap<String, Integer>();
//    double[] comparisonMoneySpent;
//    double actualTotalSpent;
//    double averageSpent;
//}

//class ItemList {
//    String[] itemDescription;
//    double[] itemPrices;
//
//    // Creating hash map (which is a dictionary in every other language)
//    // mapping the items to the prices so i can reference this list later
//    HashMap<String, Double> actualList = new HashMap<String, Double>();
//}
//class FinalPrintObject {
//    String biggestSpender;
//    String biggestSpenderAmount;
//    String lowerstSpender;
//    String lowestSpenderAmount;
//    String averagePrint;
//    double average;
//}


public class A1Adept {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        ItemList mainList = new ItemList();
        FinalPrintObject finalResults = new FinalPrintObject();


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
                if (customers[loopVariable].shoppingCartList.containsKey(itemDescription)) {
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



        // Now we have all the data saved
        // Array of customers
        // [0, 1, 2]
        // each index contains an individual customer that inherits from the Customer Class
        // and now all we need to do is figure out how to calculate the min max and average

        for (loopVariable = 0; loopVariable<customers.length; loopVariable++) {
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

        double currentMaxValue = 0;
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

        double currentMinValue = 1000000;
        // getting lowest customer info
        for (loopVariable = 0; loopVariable<customers.length; loopVariable++) {
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

        for (loopVariable = 0; loopVariable < 1; loopVariable++) {
            double runningTotal = 0;
            int customerCount = 0;
            int x;
            for(x=0; x<customers.length; x++){
                double total = customers[x].actualTotalSpent;
                customers[x].averageSpent = total;
            }
            for (x=0; x<customers.length; x++){
                double customerAverage = customers[x].averageSpent;
                runningTotal += customerAverage;
                customerCount += 1;
            }
            finalResults.average = (runningTotal / customerCount);
            String formattedAverage = String.format("%.2f", finalResults.average);
            finalResults.averagePrint = formattedAverage;

        }
        scan.close();

        System.out.println("Biggest: " + finalResults.biggestSpender + " (" + finalResults.biggestSpenderAmount + ")");
        System.out.println("Smallest: " + finalResults.lowerstSpender + " (" + finalResults.lowestSpenderAmount + ")");
        System.out.println("Average: " + finalResults.averagePrint);

    }
}

