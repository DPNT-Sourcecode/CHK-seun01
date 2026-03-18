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
    private static final List<String> VALID_SKUS = List.of("A", "B", "C", "D", "E", "F");

    +------+-------+------------------------+
            | Item | Price | Special offers         |
            +------+-------+------------------------+
            | A    | 50    | 3A for 130, 5A for 200 |
            | B    | 30    | 2B for 45              |
            | C    | 20    |                        |
            | D    | 15    |                        |
            | E    | 40    | 2E get one B free      |
            | F    | 10    | 2F get one F free      |
            | G    | 20    |                        |
            | H    | 10    | 5H for 45, 10H for 80  |
            | I    | 35    |                        |
            | J    | 60    |                        |
            | K    | 80    | 2K for 150             |
            | L    | 90    |                        |
            | M    | 15    |                        |
            | N    | 40    | 3N get one M free      |
            | O    | 10    |                        |
            | P    | 50    | 5P for 200             |
            | Q    | 30    | 3Q for 80              |
            | R    | 50    | 3R get one Q free      |
            | S    | 30    |                        |
            | T    | 20    |                        |
            | U    | 40    | 3U get one U free      |
            | V    | 50    | 2V for 90, 3V for 130  |
            | W    | 20    |                        |
            | X    | 90    |                        |
            | Y    | 10    |                        |
            | Z    | 50    |                        |
            +------+-------+------------------------+

    private final Map<String, Item> priceTable = new HashMap<>();

    public Store() {
        priceTable.put("A", new Item(50, List.of(new SpecialOffer(3, 130), new SpecialOffer(5, 200))));
        priceTable.put("B", new Item(30, List.of(new SpecialOffer(2, 45))));
        priceTable.put("C", new Item(20));
        priceTable.put("D", new Item(15));
        priceTable.put("E", new Item(40));
        priceTable.put("F", new Item(10));
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
        applyItemEBonus();
        applyItemFBonus();
    }

    private void applyItemEBonus() {
        int itemEQuantity = amountBySku.getOrDefault("E", 0);
        int itemBBonus = itemEQuantity / 2;
        int currentItemBQuantity = amountBySku.getOrDefault("B", 0);

        int billedItemBQuantity = Math.max(0, currentItemBQuantity - itemBBonus);

        amountBySku.put("B", billedItemBQuantity);
    }

    private void applyItemFBonus() {
        int itemFQuantity = amountBySku.getOrDefault("F", 0);
        int itemFBonus = itemFQuantity / 3;
        int currentItemFQuantity = amountBySku.getOrDefault("F", 0);

        int billedItemBQuantity = Math.max(0, currentItemFQuantity - itemFBonus);

        amountBySku.put("F", billedItemBQuantity);
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


