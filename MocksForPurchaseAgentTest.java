/*
 * Mocks
 * Author: Lexi LaMonica
 * All rights reserved
 */
package edu.depaul.shoppingapp;
import edu.depaul.se433.shoppingapp.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import static org.mockito.Mockito.times;

public class MocksForPurchaseAgentTest {

    @Test
    @DisplayName("Testing for adding an item, shipping, tax, and total into the system")
    void Test_For_Adding_Purchases() {
        PurchaseDBO record = mock(PurchaseDBO.class);
        PurchaseAgent saveP = new PurchaseAgent(record);
        Bill receipt = new Bill(48.00, 10, 0.06, 60.6);
        Purchase list = Purchase.make("Lexi", LocalDate.now(), receipt.total(), "IL", "STANDARD");
        saveP.save(list);
        verify(record, times(1)).savePurchase(list);
}
    @Test
    @DisplayName("Test for saving an entry that contains nothing")
    void Test_For_ReturnNothing_ContainsNothing() {
        PurchaseDBO get = mock(PurchaseDBO.class);
        PurchaseAgent agent = new PurchaseAgent(get);
        ArrayList<Purchase> purchasesList = new ArrayList<>();
        Purchase entryRecord = Purchase.make("Sophie", LocalDate.now(), 10.00, "NY", "STANDARD");
        purchasesList.add(entryRecord);
        when(get.getPurchases("")).thenReturn(purchasesList);
        assertEquals(purchasesList, agent.getPurchases(""));
        verify(get, times(1)).getPurchases("");
    }
    @Test
    @DisplayName("Test for average entries into the system")
    void Test_For_Average_Shopping(){
        PurchaseDBO shoppingAverage = mock(PurchaseDBO.class);
        PurchaseAgent overallAverage = new PurchaseAgent(shoppingAverage);
        ArrayList<Purchase> purchasesList = new ArrayList<>();
        Purchase purchaseNumOne = Purchase.make("Emma", LocalDate.now(), 15.06, "IL", "STANDARD");
        Purchase purchaseNumTwo = Purchase.make("Amelia", LocalDate.now(), 55.00, "CA", "NEXT_DAY");
        Purchase purchaseNumThree = Purchase.make("Charlotte", LocalDate.now(), 85.00, "NY", "STANDARD");
        Purchase purchaseNumFour = Purchase.make("Isabella", LocalDate.now(), 100.00, "NY", "STANDARD");
        Purchase purchaseNumFive = Purchase.make("Mia", LocalDate.now(), 125.00, "NY", "NEXT_DAY");
        purchasesList.add(purchaseNumOne); purchasesList.add(purchaseNumTwo); purchasesList.add(purchaseNumThree); purchasesList.add(purchaseNumFour); purchasesList.add(purchaseNumFive);
        when(shoppingAverage.getPurchases("")).thenReturn(purchasesList);
        double totalAverageCost = (purchaseNumOne.getCost() + purchaseNumTwo.getCost() + purchaseNumThree.getCost() + purchaseNumFour.getCost() + purchaseNumFive.getCost()) / 5;
        assertEquals(totalAverageCost, overallAverage.averagePurchase(""));
    }
    @Test
    @DisplayName("Test when both the name field and the state field are empty")
    void Test_For_Name_And_State_Are_Empty(){
        ShoppingCart purchasesList = new ShoppingCart();
        Bill receipt = TotalCostCalculator.calculate(purchasesList, " ", ShippingType.NEXT_DAY);
        Purchase.make(" ", LocalDate.now(), receipt.total(), " ", "NEXT_DAY");
        purchasesList.clear();
    }
    @Test
    @DisplayName("Test when name field is empty")
    void Test_For_When_Name_Is_Empty(){
        ShoppingCart purchasesList = new ShoppingCart();
        Bill receipt = TotalCostCalculator.calculate(purchasesList, "MT", ShippingType.NEXT_DAY);
        Purchase.make(" ", LocalDate.now(), receipt.total(), "MT", "NEXT_DAY");
        purchasesList.clear();
    }
    @Test
    @DisplayName("Test when state field is empty")
    void Test_For_When_State_Is_Empty() {
        ShoppingCart purchasesList = new ShoppingCart();
        Bill receipt = TotalCostCalculator.calculate(purchasesList, " ", ShippingType.NEXT_DAY);
        Purchase.make("Amelia", LocalDate.now(), receipt.total(), " ", "NEXT_DAY");
        purchasesList.clear();
    }
}
