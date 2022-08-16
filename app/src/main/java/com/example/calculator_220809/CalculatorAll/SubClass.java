package com.example.calculator_220809.CalculatorAll;

public class SubClass {

    //public String text2;
/*
    public SubClass(double n1, double n2) {
        int sub = (int)(n1 - n2);
        text2 = Integer.toString(sub);
    }
*/
    public String sub(double n1, double n2) {
        int result = (int)(n1 - n2);
        return Integer.toString(result);
    }

}
