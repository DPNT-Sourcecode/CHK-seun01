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


    @Test
    void testAboveOfferPrices5() {
        Integer expectedPrice = 80;

        Integer basketPrice = solution.checkout("EEB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices6() {
        Integer expectedPrice = 120;

        Integer basketPrice = solution.checkout("EEEB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices7() {
        Integer expectedPrice = 80;

        Integer basketPrice = solution.checkout("EE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices8() {
        Integer expectedPrice = 160;

        Integer basketPrice = solution.checkout("EEEEBB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices9() {
        Integer expectedPrice = 160;

        Integer basketPrice = solution.checkout("BEBEEE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices10() {
        Integer expectedPrice = 280;

        Integer basketPrice = solution.checkout("ABCDEABCDE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices11() {
        Integer expectedPrice = 280;

        Integer basketPrice = solution.checkout("CCADDEEBBA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices12() {
        Integer expectedPrice = 10;

        Integer basketPrice = solution.checkout("F");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices13() {
        Integer expectedPrice = 20;

        Integer basketPrice = solution.checkout("FF");

        assertEquals(expectedPrice, basketPrice);
    }

}