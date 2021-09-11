/*
 * Test for Bill and Cart
 * Author: Lexi LaMonica
 */
package edu.depaul.shoppingapp;

import edu.depaul.se433.shoppingapp.Bill;
import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.ShoppingCart;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForBillAndCart {
    ShoppingCart shopping_cart = new ShoppingCart();

    /* Testing Bill.Java */
    @Test
    @DisplayName("Returned Total")
    void Test_For_Return_Total(){
        Bill receipt = TotalCostCalculator.calculate(shopping_cart, "NY", ShippingType.NEXT_DAY);
        double expected = 26.5;
        assertEquals(expected, receipt.getTotal());
    }
    @Test
    @DisplayName("Returned Initial Cost")
    void Test_For_Initial_Cost(){
        Bill receipt = TotalCostCalculator.calculate(shopping_cart, "NY", ShippingType.NEXT_DAY);
        double expected = 0.0;
        assertEquals(expected, receipt.getInitialCost());
    }
    @Test
    @DisplayName("Returned Shipping")
    void Test_For_Shipping(){
        Bill receipt = TotalCostCalculator.calculate(shopping_cart, "NY", ShippingType.NEXT_DAY);
        double expected = 25;
        assertEquals(expected, receipt.getShipping());
    }
    @Test
    @DisplayName("Returned Tax")
    void Test_For_Tax(){
        Bill receipt = TotalCostCalculator.calculate(shopping_cart, "NY", ShippingType.NEXT_DAY);
        double expected = 1.5;
        assertEquals(expected, receipt.getTax());
    }
    /* Testing for ShoppingCart.Java */
    @Test
    @DisplayName("Cart is cleared")
    void Test_For_Cart_Clear(){
        shopping_cart.clear();
        assertEquals(shopping_cart.itemCount(),0);
    }
    @Test
    @DisplayName("Amount of what's the cart")
    void Test_For_Cart_Count(){
        int Count_Cart = shopping_cart.itemCount();
        assertEquals(Count_Cart,0);
    }
}
