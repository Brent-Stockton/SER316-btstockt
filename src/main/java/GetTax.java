package main.java;

public enum GetTax {

    AZ(1.07),NY(1.09),VA(1.05),DC(1.1),CA(1.05);
    
    double tax;
    
    GetTax(double t) {
        tax = t;
    }
    
    public double getTaxNum() {
        return tax;
    }  
 }


