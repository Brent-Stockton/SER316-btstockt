import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 *
 * Bears are Buy 2 bears, get 1 free. It is 10% off the cost of a bear when a
 * single bear has 10 or more accessories (Note that embroidery, stuffing, and
 * the material used for the bear casing is not considered an accessory).
 * Additionally, clothes are buy 2, get one free on each bear.
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BearWorkshop1.class},
                {BearWorkshop2.class},
                {BearWorkshop3.class},
                {BearWorkshop4.class},
                {BearWorkshop5.class}

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {

        // One Bear base stuffing, no saving expected
        oneBear = createBearWorkshop("NY");
        oneBear.addBear(new Bear(Stuffing.stuffing.BASE));
        oneBearExpected = 0.00; // no savings
        
        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 30.00;
        
        
    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test

    /**
     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30
     * and there are no savings.
     */
    @Test
    public void oneBearNoSavings() {
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }
    
    /**
     * Test examines a BearFactory with 1 simple bear in it with 1 clothing. 
     * Expect the savings to be 0.0;
     */  
    @Test
    public void oneBaseBearWithOneClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear = new Bear(Stuffing.stuffing.BASE);
            bears.addBear(customBear);
            customBear.clothing.add(new Clothing(4, "Hat"));
            double bearExpected = 0.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
  ///////////////////////////////////Clothing Savings test///////  
    /**
     * Test examines a BearFactory with 1 simple bear in it with 3 clothing at diffrent pricing. ///Brent
     * Expect the savings to be 4.0;
     */ 
    
    @Test 
    public void oneBaseBear3clothingsDiffPricesExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 simple bear in it with 3 clothing at diffrent pricing - one number negative. ///Brent
     * Expect the savings to be -1 or an error;
     */ 
    
    @Test 
    public void oneBaseBear3clothingsNegativeExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    
        customBear.clothing.add(new Clothing(-1, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        
        Double bearsExpected = -1.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
      /**
     * Test examines a BearFactory with 1 simple bear in it with 3 clothing at diffrent pricing - one number 0. ///Brent
     * Expect the savings to be 0 or an error;
     */ 
    
    @Test 
    public void oneBaseBear3clothingsoneFreeExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    
        customBear.clothing.add(new Clothing(0, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(6, "Shoes"));
        
        Double bearsExpected = 0.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 simple bear in it with 3 clothing at diffrent pricing - all  very large numbers. ///Brent
     * Expect the savings to be 1.8E+307;
     */ 
    
    @Test 
    public void oneBaseBear3clothingsLargeNumberExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
    
        customBear.clothing.add(new Clothing(1.8E+307, "Hat"));
        customBear.clothing.add(new Clothing(1.8E+307, "Sunglasses"));
        customBear.clothing.add(new Clothing(1.8E+307, "Shoes"));
        
        Double bearsExpected = 1.8E+307;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    
    /**
     * Test examines a BearFactory with 2 simple bearsin it with no clothing. // brent
     * Expect the savings to be 0.0;
     */  
    @Test
    public void twoBaseBearWithNoClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
            Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
            bears.addBear(customBear1);
            bears.addBear(customBear2);
           
            double bearExpected = 0.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
    
    /**
     * Test examines a BearFactory with 1 base bear in it with 10 clothing pieces.    //Brent
     * Expect the savings to be 10% off bear + fee accesorys $12.00;
     */  
    @Test
    public void oneBearTest10clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Pants"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        customBear.clothing.add(new Clothing(4, "Socks"));
        customBear.clothing.add(new Clothing(4, "Watch"));
        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Necklace"));
        customBear.clothing.add(new Clothing(4, "Sweater"));
        customBear.clothing.add(new Clothing(4, "Belt"));
        
        Double bearsExpected = 12.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
  
    /**
     * Test examines a BearFactory with 1 base bear in it with 10 clothing pieces at different prices.    //Brent
     * Expect the savings to be 10% off bear + fee accesorys $15.00;
     */  
    @Test
    public void oneBearTest10clothingsDiffPriceExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(5, "Hat"));
        customBear.clothing.add(new Clothing(5, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Pants"));
        customBear.clothing.add(new Clothing(5, "Shoes"));
        customBear.clothing.add(new Clothing(5, "Socks"));
        customBear.clothing.add(new Clothing(4, "Watch"));
        customBear.clothing.add(new Clothing(5, "Hat"));
        customBear.clothing.add(new Clothing(5, "Necklace"));
        customBear.clothing.add(new Clothing(4, "Sweater"));
        customBear.clothing.add(new Clothing(5, "Belt"));
        
        Double bearsExpected = 15.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 down bear in it with no clothing. 
     * Expect the savings to be 0.0;
     */  
    @Test
    public void oneDownBearWithnoClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear = new Bear(Stuffing.stuffing.DOWN);
            bears.addBear(customBear);
            double bearExpected = 0.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
    
    /**
     * Test examines a BearFactory with 1 foam bear in it with no clothing. 
     * Expect the savings to be 0.0;
     */  
    @Test
    public void oneDownFoamWithnoClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear = new Bear(Stuffing.stuffing.FOAM);
            bears.addBear(customBear);
            double bearExpected = 0.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
  
    
    /**
     * Test examines a BearFactory with 1 simple bear in it with no clothing. 
     * Expect the savings to be 0.0;
     */  
    @Test
    public void oneBaseBearWithnoClothing() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear = new Bear(Stuffing.stuffing.BASE);
            bears.addBear(customBear);
            double bearExpected = 0.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
    
    // sample test
    @Test
    public void threeBearsSaveOnCheapest() {
        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }
    
    /**
     * Test examines a BearFactory with all 3 bearsin it with 3 diferent clothing each clothing. // brent
     * Expect the savings to be 34.0;
     */  
    @Test
    public void threeDiffBaseBearThreeClothingEach() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } 
        catch (Exception e){
            Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
            Bear customBear2 = new Bear(Stuffing.stuffing.FOAM);
            Bear customBear3 = new Bear(Stuffing.stuffing.DOWN);
            bears.addBear(customBear1);
            bears.addBear(customBear2);
            bears.addBear(customBear3);
            
            customBear1.clothing.add(new Clothing(4, "Hat"));
            customBear1.clothing.add(new Clothing(4, "Sunglasses"));
            customBear1.clothing.add(new Clothing(4, "Pants"));
            
            customBear2.clothing.add(new Clothing(4, "Hat"));
            customBear2.clothing.add(new Clothing(4, "Sunglasses"));
            customBear2.clothing.add(new Clothing(4, "Pants"));
            
            customBear3.clothing.add(new Clothing(4, "Hat"));
            customBear3.clothing.add(new Clothing(4, "Sunglasses"));
            customBear3.clothing.add(new Clothing(4, "Pants"));
           
            double bearExpected = 34.0;
            double ans = bears.calculateSavings();
            assertEquals(bearExpected, ans, 0.005);
            
        } 
    }
    
 

    // sample test
 
    @Test
    public void oneBearTest3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
 // sample test
    
    @Test
    public void oneBear3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

        customBear.clothing.add(new Clothing(4, "Hat"));
        customBear.clothing.add(new Clothing(4, "Sunglasses"));
        customBear.clothing.add(new Clothing(4, "Shoes"));
        
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

}
