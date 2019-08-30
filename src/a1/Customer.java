package a1;

import java.util.HashMap;

public class Customer {
    String fName;
    String lName;
    int shoppingCartNumber;

    String[] itemDescription;
    int[] quantityOfItem;
    HashMap<String, Integer> shoppingCartList = new HashMap<String, Integer>();
    double[] comparisonMoneySpent;
    double actualTotalSpent;
    double averageSpent;
}
