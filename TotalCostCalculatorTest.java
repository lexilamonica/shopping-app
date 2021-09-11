package edu.depaul.shoppingapp;
import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.se433.shoppingapp.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
public class TotalCostCalculatorTest {
    private TotalCostCalculator TotalCostCalculator;
    ShoppingCart shopping_cart = new ShoppingCart();

    @Test
    @DisplayName("This is computing the tax for the state of CA")
    void tax_For_California() {
        assertEquals(3.5999999999999996, TaxCalculator.calculate(60, "CA"));
    }

    @Test
    @DisplayName("This is computing the tax for the state of NY")
    void tax_For_NewYork() {
        assertEquals(2.4, TaxCalculator.calculate(40, "NY"));
    }

    @Test
    @DisplayName("This is computing the tax for the state of IL")
    void tax_For_Illinois() {
        assertEquals(4.8, TaxCalculator.calculate(80, "IL"));
    }

    @Test
    @DisplayName("This is computing the tax for the state's that do NOT have tax")
    void non_Tax_States() {
        assertEquals(0, TaxCalculator.calculate(20, "MT"));
    }

    /* Problem 1 from checklist || Normal Boundary Test*/
    @ParameterizedTest
    @MethodSource("BoundaryTest")
    void boundary_test(String state, ShippingType shippingType, Double price, Double total) {
        Double overAllTotal = TotalCostCalculator.calculate(price, state, shippingType);
        assertEquals(total, overAllTotal);
    }

    public static Stream<Arguments> WeakNormalTest() {
        return Stream.of(
                Arguments.of(12.00, "NY", ShippingType.NEXT_DAY, 39.22),
                Arguments.of(35.00, "IL", ShippingType.STANDARD, 47.7),
                Arguments.of(51.00, "CA", ShippingType.NEXT_DAY, 54.06));
    }

    private static Stream<Arguments> StrongNormalTest() {
        return Stream.of(
                Arguments.of(5.00, "IL", ShippingType.STANDARD, 15.9),
                Arguments.of(35.00, "IL", ShippingType.STANDARD, 47.7),
                Arguments.of(70.00, "IL", ShippingType.NEXT_DAY, 74.2),
                Arguments.of(105.00, "IL", ShippingType.NEXT_DAY, 111.3),

                Arguments.of(4.50, "NY", ShippingType.STANDARD, 15.370000000000001),
                Arguments.of(59.00, "NY", ShippingType.STANDARD, 62.54),
                Arguments.of(12.00, "NY", ShippingType.NEXT_DAY, 39.22),
                Arguments.of(90.00, "NY", ShippingType.NEXT_DAY, 95.4),

                Arguments.of(30.08, "CA", ShippingType.STANDARD, 42.4848),
                Arguments.of(58.15, "CA", ShippingType.STANDARD, 61.638999999999996),
                Arguments.of(10.00, "CA", ShippingType.NEXT_DAY, 37.1),
                Arguments.of(94.00, "CA", ShippingType.NEXT_DAY, 99.64));

    }

    public static Stream<Arguments> WeakRobustTest() {
        return Stream.of(
                Arguments.of(2.00, "NY", ShippingType.NEXT_DAY, 28.62),
                Arguments.of(10.50, "IL", ShippingType.NEXT_DAY, 37.629999999999995),
                Arguments.of(20.00, "CA", ShippingType.NEXT_DAY, 47.7));
    }

    public static Stream<Arguments> StrongRobustTest() {
        return Stream.of(
                Arguments.of(5.00, "IL", ShippingType.STANDARD, 15.9),
                Arguments.of(35.00, "IL", ShippingType.STANDARD, 47.7),
                Arguments.of(70.00, "IL", ShippingType.NEXT_DAY, 74.2),
                Arguments.of(105.00, "IL", ShippingType.NEXT_DAY, 111.3),

                Arguments.of(4.50, "NY", ShippingType.STANDARD, 15.370000000000001),
                Arguments.of(59.00, "NY", ShippingType.STANDARD, 62.54),
                Arguments.of(12.00, "NY", ShippingType.NEXT_DAY, 39.22),
                Arguments.of(90.00, "NY", ShippingType.NEXT_DAY, 95.4),

                Arguments.of(30.08, "CA", ShippingType.STANDARD, 42.4848),
                Arguments.of(58.15, "CA", ShippingType.STANDARD, 61.638999999999996),
                Arguments.of(10.00, "CA", ShippingType.NEXT_DAY, 37.1),
                Arguments.of(94.00, "CA", ShippingType.NEXT_DAY, 99.64),
                //-1
                Arguments.of(-1.00, "IL", ShippingType.STANDARD, 15.9),
                Arguments.of(35.00, "IL", ShippingType.STANDARD, -1.00),
                Arguments.of(-1.00, "IL", ShippingType.NEXT_DAY, 74.2),
                Arguments.of(105.00, "IL", ShippingType.NEXT_DAY, -1.00),

                Arguments.of(-1.00, "NY", ShippingType.STANDARD, 15.370000000000001),
                Arguments.of(59.00, "NY", ShippingType.STANDARD, -1.00),
                Arguments.of(-1.00, "NY", ShippingType.NEXT_DAY, 39.22),
                Arguments.of(90.00, "NY", ShippingType.NEXT_DAY, -1.00),

                Arguments.of(-1.00, "CA", ShippingType.STANDARD, 42.4848),
                Arguments.of(58.15, "CA", ShippingType.STANDARD, -1.00),
                Arguments.of(-1.00, "CA", ShippingType.NEXT_DAY, 37.1),
                Arguments.of(94.00, "CA", ShippingType.NEXT_DAY, -1.00));
    }

    private static Stream<Arguments> BoundaryTest() {
        return Stream.of(
                Arguments.of("NY", ShippingType.STANDARD, 1.99, 12.7094),
                Arguments.of("NY", ShippingType.STANDARD, 39.99, 52.9894),
                Arguments.of("NY", ShippingType.STANDARD, 40.00, 53.0),
                Arguments.of("NY", ShippingType.STANDARD, 40.01, 53.0106),
                Arguments.of("NY", ShippingType.STANDARD, 50.00, 63.6)
        );
    }

    /* Problem 2: Equivalence partitioning || Strong/Normal Test & Weak/Robust */
    @ParameterizedTest
    @MethodSource("StrongNormalTest")
    void strong_normal_test(double cost, String state, ShippingType shipping, double sum) {
        double overAllTotal = TotalCostCalculator.calculate(cost, state, shipping);
        assertEquals(sum, overAllTotal);
    }

    //   /* Equivalence partitioning + Weak Normal Test*/
    @ParameterizedTest
    @MethodSource("WeakNormalTest")
    void weak_normal_test(double cost, String state, ShippingType shipping, double sum) {
        if (cost < 0.0 || state.equals("") || sum < 0.0) {
            assertThrows(IllegalArgumentException.class, () -> TotalCostCalculator.calculate(cost, state, shipping));
        } else {
            double overAllTotal = TotalCostCalculator.calculate(cost, state, shipping);
            assertEquals(sum, overAllTotal);
        }

    }

    /*Equivalence partitioning + Strong Robust Test*/
    @ParameterizedTest
    @MethodSource("StrongRobustTest")
    void strong_robust_test(double cost, String state, ShippingType shipping, double sum) {
        if (cost < 0.0 || state.equals("") || sum < 0.0) {
            assertThrows(IllegalArgumentException.class, () -> TotalCostCalculator.calculate(cost, state, shipping));
        } else {
            double overAllTotal = TotalCostCalculator.calculate(cost, state, shipping);
            assertEquals(sum, overAllTotal);
        }
    }

    /*Equivalence partitioning + Weak Robust Test*/
    @ParameterizedTest
    @MethodSource("WeakRobustTest")
    void weak_robust_test(double cost, String state, ShippingType shipping, double sum) {
        if (cost < 0.0 || state.equals("") || sum < 0.0) {
            assertThrows(IllegalArgumentException.class, () -> TotalCostCalculator.calculate(cost, state, shipping));
        } else {
            double overAllTotal = TotalCostCalculator.calculate(cost, state, shipping);
            assertEquals(sum, overAllTotal);
        }

    }
}

