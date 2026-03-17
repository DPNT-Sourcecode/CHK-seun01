package io.accelerate.solutions.CHK;

import java.util.*;


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

        return finalPrice;
    }
}

class Store {
    private static final List<String> VALID_SKUS = List.of("A", "B", "C", "D", "E");

    private final Map<String, Item> priceTable = new HashMap<>();

    public Store() {
        priceTable.put("A", new Item(50, List.of(new SpecialOffer(3, 130), new SpecialOffer(5, 200))));
        priceTable.put("B", new Item(30, List.of(new SpecialOffer(2, 45))));
        priceTable.put("C", new Item(20));
        priceTable.put("D", new Item(15));
        priceTable.put("E", new Item(40));
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
    private final Map<String, Long> amountBySku;

    public Basket(Store store, Map<String, Long> amountBySku) {
        this.store = store;
        this.amountBySku = amountBySku;
    }

    public Integer calculatePrice() {
        Integer basketPrice = 0;

        for (String sku : amountBySku.keySet()) {
            int quantity = amountBySku.get(sku).intValue();

            basketPrice += store.calculateItemPrice(sku, quantity);
        }

        return basketPrice;
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

            Map<String, Long> amountBySku = groupItemsBySku(skus);
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

    private static Map<String, Long> groupItemsBySku(String skus) {
        Map<String, Long> amountBySku = new HashMap<>();

        for (int i = 0; i < skus.length(); i++) {
            String sku = "" + skus.charAt(i);

            amountBySku.put(sku, amountBySku.getOrDefault(sku, 0L) + 1);
        }

        return amountBySku;
    }
}
