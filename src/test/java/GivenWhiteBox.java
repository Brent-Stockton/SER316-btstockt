import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(5.35, ans, 0.005);
    }
    
    /*Edge Covering test that covers every edge*/
    @Test
    public void coverEdges() {
        Bear bear = new Bear();
        oneBear.addBear(new Bear());
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.noisemakers.add(new NoiseMaker());
        Double ans = oneBear.getCost(bear);
        assertEquals(53.0, ans, 0.005);
    }  
    
    /*Full node covering test*/
    @Test
    public void coverNodes() {
        Bear bear = new Bear();
        oneBear.addBear(new Bear());
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.clothing.add(new Clothing(4, "Hat"));
        bear.noisemakers.add(new NoiseMaker());     
        Double ans = oneBear.getCost(bear);
        assertEquals(53.0, ans, 0.005);
    }  
}
