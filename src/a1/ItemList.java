package a1;

import java.util.HashMap;

public class ItemList {
    String[] itemDescription;
    double[] itemPrices;

    // Creating hash map (which is a dictionary in every other language)
    // mapping the items to the prices so i can reference this list later
    HashMap<String, Double> actualList = new HashMap<String, Double>();
}
