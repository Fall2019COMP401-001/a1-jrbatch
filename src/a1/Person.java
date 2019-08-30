package a1;

public class Person {
    String firstName;
    String lastName;

    int numberOfItemsInList;

    int[] quantityOfItem;
    String[] itemDescription;
    double[] itemPrice;

    double totalSpent;
    String totalSpentPrint;
    static double calculateTotalSpent(int amount, double price) {
        double total = amount * price;
        return total;
    }

}
