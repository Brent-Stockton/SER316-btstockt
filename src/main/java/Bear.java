package main.java;

import java.util.LinkedList;
import main.java.Stuffing.stuffing;


public class Bear implements Comparable<Bear> {
    public Casing casing;
    public Stuffing stuff;
    public Embroidery ink; 
    public LinkedList<NoiseMaker> noisemakers; // accessory
    public LinkedList<Clothing> clothing; // accessory
    double price;
    // bear has a shell (requ)
    // bear has stuffing (req)
    // bear has a tattoo/emroider or not (opt)
    // bear has a noisemaker (opt)

    /** Bear Constructor.*/
    public Bear() {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuffing.BASE);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }
    
    /**Bear Constructor.
     * 
     * @param stuff uses stuff object.
     */
    public Bear(stuffing stuff) {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuff);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    /**Sets price.
     * 
     * @param incomingPrice sets incoming price
     */
    public void setPrice(double incomingPrice) {
        this.price = incomingPrice;
    }

    /**Method adds noisemaker to bear.
     * 
     * @param noise is the noise maker object.
     * @return
     */
    public boolean addNoise(NoiseMaker noise) {
        if (this.noisemakers.size() >= 5) {
            return false;
        } else {
            for (NoiseMaker noisemaker: noisemakers) {
                if (noise.spot == noisemaker.spot) {
                    return false;
                }
            }
            noisemakers.add(noise);
            return true;
        }
    }
    
    /** Compare to method for bears.
     * 
     */
    @Override
    public int compareTo(Bear bear) {
        return new Double(this.price).compareTo(bear.price);
    }
}