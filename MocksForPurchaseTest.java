/*
 * Mocks for purchaseItem
 * Author: Lexi LaMonica
 */
package edu.depaul.shoppingapp;
import edu.depaul.se433.shoppingapp.Purchase;
import edu.depaul.se433.shoppingapp.PurchaseDBO;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class MocksForPurchaseTest {
    @Test
    @DisplayName("This test is saving a purchase into the database")
    void Test_For_Save_Purchase(){
        PurchaseDBO record = mock(PurchaseDBO.class);
        Purchase mockTester = mock(Purchase.class);
        when(mockTester.getCustomerName()).thenReturn("Lexi");
            when(mockTester.getPurchaseDate()).thenReturn(LocalDate.now());
                when(mockTester.getCost()).thenReturn(45.0);
                    when(mockTester.getShipping()).thenReturn("Standard");
                         when(mockTester.getState()).thenReturn("NY");
        record.savePurchase(mockTester);
        verify(mockTester).getCustomerName();
        verify(mockTester).getPurchaseDate();
        verify(mockTester).getCost();
        verify(mockTester).getShipping();
        verify(mockTester).getState();
    }
    @Test
    @DisplayName("This test is getting the purchase from the database")
    void Test_For_Get_Purchase(){
        PurchaseDBO record = mock(PurchaseDBO.class);
        Purchase mockTester = mock(Purchase.class);
        when(mockTester.getCustomerName()).thenReturn("Lexi");
            when(mockTester.getPurchaseDate()).thenReturn(LocalDate.now());
                when(mockTester.getCost()).thenReturn(20.0);
                     when(mockTester.getShipping()).thenReturn("Standard");
                         when(mockTester.getState()).thenReturn("NY");
        record.savePurchase(mockTester);
        List<Purchase> actualResult = record.getPurchases("");
        assertNotNull(actualResult);
    }
    @Test
    @DisplayName("This test is a Mockito test for CustomerName in the Purchase database")
    void Test_For_Customer_Name(){
        Purchase mockTester = mock(Purchase.class);
        Mockito.doCallRealMethod().when(mockTester).setCustomerName(" ");
        mockTester.setCustomerName(" ");
        Mockito.doCallRealMethod().when(mockTester).getCustomerName();
        String actualResult = mockTester.getCustomerName();
        assertEquals(actualResult," ");
    }
    @Test
    @DisplayName("This test is a Mockito test for shipping in the Purchase database")
    void Test_For_Shipping(){
        Purchase mockTester = mock(Purchase.class);
        Mockito.doCallRealMethod().when(mockTester).setShipping("Standard");
        mockTester.setShipping("Standard");
        Mockito.doCallRealMethod().when(mockTester).getShipping();
        String actualResult = mockTester.getShipping();
        assertEquals(actualResult,"Standard");
    }
    @Test
    @DisplayName("This test is a Mockito test for cost in the Purchase database")
    void Test_For_Cost(){
        Purchase mockTester = mock(Purchase.class);
        Mockito.doCallRealMethod().when(mockTester).setCost(50.0);
        mockTester.setCost(50.0);
        Mockito.doCallRealMethod().when(mockTester).getCost();
        double actualResult = mockTester.getCost();
        assertEquals(actualResult,50.0);
    }
    @Test
    @DisplayName("This test is a Mockito test for date in the Purchase database")
    void Test_For_Date(){
        Purchase mockTester = mock(Purchase.class);
        Mockito.doCallRealMethod().when(mockTester).setPurchaseDate(LocalDate.now());
        mockTester.setPurchaseDate(LocalDate.now());
        Mockito.doCallRealMethod().when(mockTester).getPurchaseDate();
        LocalDate actualResult = mockTester.getPurchaseDate();
        assertEquals(actualResult, LocalDate.now());
    }
    @Test
    @DisplayName("This test is a Mockito test for state in the Purchase database")
    void Test_For_State(){
        Purchase mockTester = mock(Purchase.class);
        Mockito.doCallRealMethod().when(mockTester).setState("NY");
        mockTester.setState("NY");
        Mockito.doCallRealMethod().when(mockTester).getState();
        String actualResult = mockTester.getState();
        assertEquals(actualResult, "NY");
    }
}
