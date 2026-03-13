package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


record SpecialOffer(
        int quantity,
        Integer offerPrice
) {
}

class Item {

    private final Integer price;
    private final SpecialOffer specialOffer;

    public Item(Integer price, SpecialOffer specialOffer) {
        this.price = price;
        this.specialOffer = specialOffer;
    }

    public Item(Integer price) {
        this.price = price;
        this.specialOffer = null;
    }

    public Integer calculateFinalPrice(int quantity) {
        if (quantity == 0) {
            return 0;
        }

        if (specialOffer == null || quantity < specialOffer.quantity()) {
            return quantity * price;
        }

        int quantityAboveOffer = quantity - specialOffer.quantity();

        return specialOffer.offerPrice() + (quantityAboveOffer * price);
    }
}


public class CheckoutSolution {

    private final Map<String, Item> priceTable = new HashMap<>();

    public CheckoutSolution() {
        priceTable.put("A", new Item(50, new SpecialOffer(3, 130)));
        priceTable.put("B", new Item(30, new SpecialOffer(2, 45)));
        priceTable.put("C", new Item(20));
        priceTable.put("D", new Item(15));
    }

    public Integer checkout(String skus) {
        if (skus == null || skus.isBlank()) {
            return -1;
        }

        List<String> skusList = Arrays.stream(skus.split(",")).toList();

        if (skusList.isEmpty()) {
            return -1;
        }

        for (String sku : skusList) {
            if (sku.isBlank() || sku.length() != 1 || !Character.isLetter(sku.charAt(0))) {
                return -1;
            }
        }

        Integer basketPrice = 0;

        Map<String, Long> skusInBasket = skusList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (String sku : skusInBasket.keySet()) {
            int quantity = skusInBasket.get(sku).intValue();

            Item item = priceTable.get(sku);
            basketPrice += item.calculateFinalPrice(quantity);
        }

        return basketPrice;
    }
}


