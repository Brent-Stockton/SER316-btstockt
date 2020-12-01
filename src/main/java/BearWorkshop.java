package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface{
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> BearCart;

    /**
     * Default constructor for the Bear Workshop
     */
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop
     * @param state customer is in
     */
    public BearWorkshop(String state) {
        BearCart = new LinkedList<>();
        customer = new Customer(state);
    }

    /**
     * This is a convenience method to calculate the cost of bears in the
     * shopping cart for a customer in the BearFactory.
     * @param bear to get cost of
     * @return double representation of bear cost
     * TODO: test me and fix me in assignment 3
     */
    @Override
    public double getCost(Bear bear) {
        Collections.sort(bear.clothing);
        int numFree = bear.clothing.size() / 3;
        ArrayList<Clothing> freeClothes = new ArrayList<>();

        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            if (i < numFree) {
                freeClothes.add(clothes);
            } else {
                bear.price += clothes.price;
            }
        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;

        return bear.price;
    }

    // Function to get the raw cost of a bear without any discounts
   // TODO: test me and fix me in assignment 3
    public double getRawCost(Bear bear) {
        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            bear.price += clothes.price;

        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;

        double bearPrice = bear.price;
        bear.price = 0;
        return bearPrice;
    }

    /**
     * Utility method to calculate tax for purchases by customers in different
     * states.
     * You can assume these taxes are what we want, so they are not wrong.
     * @return
     */
    public double calculateTax() {
        double tax;
        switch (customer.state) {
            case "AZ":
                tax = 1.07;
                break;
            case "NY":
                tax = 1.09;
                break;
            case "VA":
                tax = 1.05;
                break;
            case "DC":
                tax = 1.105;
                break;
            case "CA":
                tax = 1.1;
                break;
            default:
                tax = 1.05;
                break;
        }
        return tax;
    }

    /**
     * Utility method to add a bear to the BearFactory.
     * @param bear to add
     * @return true if successful, false otherwise
     * TODO: test me and fix me in assignment 3
     */
    @Override
    public boolean addBear(Bear bear)       {
        if (this.BearCart.add(bear))        {
            return true;
                                            }
        else                                {
            return false;
                                            }
    }
    // Simple means to remove a bear
    @Override
    public boolean removeBear(Bear bear)    {
        if (this.BearCart.remove(bear))     {
            return true;
                                            }
        else                                {
            return false;
                                            }
    }

    /**
     * This is a function to allow customers to checkout with their
     * TODO: Test me and fix me in assignment 3
     * @return
     */
    @Override
    public double checkout() {
        if (this.customer.age <= 13) {
            if (this.customer.parent.age < 18)
                System.out.println("Guardian is too young");
                return -1;
        }
        double temp = 0;
        Double Cost = Double.valueOf(0.00);
        for (Bear bear: BearCart) {
            Cost = Cost + getRawCost(bear);
        }
        for (Bear bear: this.BearCart) {
            temp += getCost(bear);
        }


        double savings = 0;
        // calculate total cost
        double rawCost = 0;
        for (Bear bear: BearCart) {
            rawCost += this.getRawCost(bear);
        }

        // calculate adjusted cost
        double cost = 0;
        for (Bear bear: this.BearCart) {
            cost += this.getCost(bear);
        }
        savings += rawCost - cost; // calc delta between raw and prorated cost

        List<Bear> nonFreeBears = new LinkedList<>();
        int counter = 0;
        int numberOfFreeBearsInBearCart = BearCart.size() / 3;
        double discountedCost = 0;
        Bear freeBear = null;

        for (int count = 0; count <= numberOfFreeBearsInBearCart; ++count) {
            for (Bear bear : BearCart) {
                if (freeBear != null && bear.price < freeBear.price)
                    freeBear = bear;
                    temp += temp - temp * 2 + bear.price;

            }
        }
        cost = temp;

        return cost * calculateTax();
    }


    /**
     * This method returns the savings for advertised bundle savings.
     * Specifically, 
     * - Bears are Buy 2 bears, get a third one free. It is always the cheapest bear that is free
     * - It is 10% off the cost of a bear when a single bear has 10 or more accessories (clothes and otherwise) that the customer pays for (so if clothes are free these do not count). 
     * - Clothes are buy 2, get one free on each bear. Always the cheapest clothes are free
     *  TIP: the implemented savings method in the BearWorkshop1-5 do not use the getCost method implemented in this base class. They implement their own savings calculation
     *  		 All of them do however use the getRawCost method implemented in this base class. 
     * @return the savings if the customer would check out as double
     */
    public double calculateSavings() {
        System.out.println("TODO: Implement me in Assignment 3");
        return 0.0;
    }
}