package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private static CheckoutSolution solution;

    @BeforeAll
    static void setUp() {
        solution = new CheckoutSolution();
    }

    @Test
    void testEmptyInput() {
        Integer basketPrice = solution.checkout("");

        assertEquals(0, basketPrice);
    }

    @Test
    void testInvalidInput() {
        Integer basketPrice = solution.checkout("a");

        assertEquals(-1, basketPrice);
    }

    @Test
    void testOnlyNormalPrices() {
        Integer expectedPrice = 115;

        Integer basketPrice = solution.checkout("ABCD");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testOfferPrices() {
        Integer expectedPrice = 130 + 45;

        Integer basketPrice = solution.checkout("AAABB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices1() {
        Integer expectedPrice = 250;

        Integer basketPrice = solution.checkout("AAAAAA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices2() {
        Integer expectedPrice = 90;

        Integer basketPrice = solution.checkout("BBBB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices3() {
        Integer expectedPrice = 600 + 130 + 50;

        Integer basketPrice = solution.checkout("AAAAAAAAAAAAAAAAAAA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices4() {
        Integer expectedPrice = 200;

        Integer basketPrice = solution.checkout("AAAAA");

        assertEquals(expectedPrice, basketPrice);
    }


}