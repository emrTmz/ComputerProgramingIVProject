package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        System.out.println("unit cost value = ");
        double c=scan.nextDouble();
        System.out.println("lead time value = ");
        double lt=scan.nextDouble();
        System.out.println("Annual interest rate value = ");
        double i=scan.nextDouble();
        System.out.println("penalty cost value = ");
        double p=scan.nextDouble();
        System.out.println("ordering cost value = ");
        double k=scan.nextDouble();
        System.out.println("Average sells of four months value = ");
        double m=scan.nextDouble();
        System.out.println("Standart deviation of demand value =");
        double q=scan.nextDouble();


        Formulas data = new Formulas(c, lt, i, p, k, m, q);
        data.loop();
    }
}