package com.example.calculator_220809.CalculatorAll;

public class AddClass {

    //public String text1;
    /*
    public AddClass(double n1, double n2) {
        int add = (int)(n1 + n2);
        text1 = Integer.toString(add);
    }
   */
    // 메소드 넣기
    // public void add(double n1, double n2) { int value = (int)(n1 + n2); }
    public String add(double n1, double n2) {
        int result = (int)(n1 + n2);
        return Integer.toString(result);
    }

}
