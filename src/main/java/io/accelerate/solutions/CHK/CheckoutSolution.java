package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


record SpecialOffer(
        int quantity,
        Integer offerPrice
) {
}

class Item {

    private final Integer price;
    private final List<SpecialOffer> specialOffers;


    public Item(Integer price, List<SpecialOffer> specialOffers) {
        this.price = price;
        this.specialOffers = specialOffers;
    }

    public Item(Integer price) {
        this.price = price;
        this.specialOffers = new ArrayList<>();
    }

    public Integer calculateFinalPrice(int quantity) {
        if (quantity == 0) {
            return 0;
        }

        // If there is no special offers, or the quantity request is lower than the first special offer,
        // use the regular price
        if (specialOffers.isEmpty() || quantity < specialOffers.getFirst().quantity()) {
            return quantity * price;
        }

        // Otherwise, iterate special offers list to calculate the final price
        // Starts from the end, to ensure that we give clients the best offer

        int finalPrice = 0;
        int remaining = quantity;

        int i = specialOffers.size() - 1;

        while (remaining > 0 && i >= 0) {
            SpecialOffer specialOffer = specialOffers.get(i);

            int offerUses = remaining / specialOffer.quantity();
            remaining = remaining - (offerUses * specialOffer.quantity());

            finalPrice += specialOffer.offerPrice() * offerUses;

            i--;
        }

        return finalPrice + (remaining * price);
    }
}

class Store {
    private static final List<String> VALID_SKUS = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    private final Map<String, Item> priceTable = new HashMap<>();

    public Store() {
        priceTable.put("A", new Item(50, List.of(new SpecialOffer(3, 130), new SpecialOffer(5, 200))));
        priceTable.put("B", new Item(30, List.of(new SpecialOffer(2, 45))));
        priceTable.put("C", new Item(20));
        priceTable.put("D", new Item(15));
        priceTable.put("E", new Item(40));
        priceTable.put("F", new Item(10));
        priceTable.put("G", new Item(20));
        priceTable.put("H", new Item(10));
        priceTable.put("I", new Item(35));
        priceTable.put("J", new Item(60));
        priceTable.put("K", new Item(80));
        priceTable.put("L", new Item(90));
        priceTable.put("M", new Item(15));
        priceTable.put("N", new Item(40));
        priceTable.put("O", new Item(10));
        priceTable.put("P", new Item(50));
        priceTable.put("Q", new Item(30));
        priceTable.put("R", new Item(50));
        priceTable.put("S", new Item(30));
        priceTable.put("T", new Item(20));
        priceTable.put("U", new Item(40));
        priceTable.put("V", new Item(50));
        priceTable.put("W", new Item(20));
        priceTable.put("X", new Item(90));
        priceTable.put("Y", new Item(10));
        priceTable.put("Z", new Item(50));
    }

    public Integer calculateItemPrice(String sku, Integer quantity) {
        Item item = priceTable.get(sku);

        if (item == null) {
            return 0;
        }

        return item.calculateFinalPrice(quantity);
    }

    public boolean isSKUValid(String sku) {
        return VALID_SKUS.contains(sku);
    }
}

class Basket {

    private final Store store;
    private final Map<String, Integer> amountBySku;

    public Basket(Store store, Map<String, Integer> amountBySku) {
        this.store = store;
        this.amountBySku = amountBySku;
    }

    public Integer calculatePrice() {
        applyBonuses();

        Integer basketPrice = 0;

        for (String sku : amountBySku.keySet()) {
            int quantity = amountBySku.get(sku);

            basketPrice += store.calculateItemPrice(sku, quantity);
        }

        return basketPrice;
    }

    private void applyBonuses() {
        applyItemBonus("E", "B", 2);
        applyItemBonus("F", "F", 3);
    }

    private void applyItemBonus(String sourceItem, String targetItem, int quantity) {
        int sourceQuantity = amountBySku.getOrDefault(sourceItem, 0);
        int targetBonus = sourceQuantity / quantity;
        int currentTargetQuantity = amountBySku.getOrDefault(targetItem, 0);

        int billedItemBQuantity = Math.max(0, currentTargetQuantity - targetBonus);

        amountBySku.put(targetItem, billedItemBQuantity);
    }
}

public class CheckoutSolution {

    public Integer checkout(String skus) {
        if (skus == null || skus.isBlank()) {
            return 0;
        }

        Store store = new Store();

        try {
            validateSkus(skus, store);

            Map<String, Integer> amountBySku = groupItemsBySku(skus);
            Basket bascket = new Basket(store, amountBySku);

            return bascket.calculatePrice();
        } catch (IllegalArgumentException exception) {
            return -1;
        }
    }

    private void validateSkus(String skus, Store store) {
        for (int i = 0; i < skus.length(); i++) {
            String sku = "" + skus.charAt(i);

            if (!store.isSKUValid(sku)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static Map<String, Integer> groupItemsBySku(String skus) {
        Map<String, Integer> amountBySku = new HashMap<>();

        for (int i = 0; i < skus.length(); i++) {
            String sku = "" + skus.charAt(i);

            amountBySku.put(sku, amountBySku.getOrDefault(sku, 0) + 1);
        }

        return amountBySku;
    }
}



