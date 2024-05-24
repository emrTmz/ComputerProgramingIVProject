package org.example;

import java.io.IOException;
import java.text.DecimalFormat;

public class Formulas {
    private double  p, k, m, q, l, h, nR, S, T;

    public Formulas(double c, double lt, double i, double p, double k, double m, double q) {

        this.p = p;
        this.k = k;
        this.m = m;
        this.q = q;
        this.l = (12/lt)*m;
        this.h = (c*i);
        this.nR = 0;
        this.S = 0;
        this.T = 0;
    }


    public double funcQ() {
        return Math.ceil(Math.sqrt((2 * this.l * (this.k + (this.p * this.nR))) / this.h));
    }

    public double funcFR() {
        double num = 1 - (funcQ() * h) / (this.p * this.l);
        return Math.ceil(num * 10000.0) / 10000.0;
    }

    public double Z() {
        Reader read = new Reader(funcFR());
        try {
            read.read3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return read.getZ();
    }

    public double LZ() {
        Reader read = new Reader(funcFR());
        try {
            read.read3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return read.getLZ();
    }

    public double funcR() {
        return this.m + (this.q * Z());
    }

    public double funcNR() {
        return this.q * LZ();
    }

    public void loop() {
        double previousNumber = -1;
        double currentNumber = funcR();

        while (previousNumber != currentNumber) {
            previousNumber = currentNumber;
            this.nR = funcNR();
            currentNumber = funcR();
        }

        System.out.println("Last F(R) = " + funcFR());
        System.out.println("Last Z = " + Z());
        System.out.println("Last L(Z) = " + LZ());
        System.out.println("----------------------------------------");

        int funcr = (int) funcR();
        System.out.println("Q, R = (" + funcQ() + ", " + funcr + ")");
        System.out.println("nR = " + funcNR());
        System.out.println("----------------------------------------");

        this.S = funcr - this.m;
        this.T = 12 * (funcQ() / this.l);
        DecimalFormat df = new DecimalFormat("#.####");

        System.out.println("The Safety Stock = " + this.S + " units");
        System.out.println("Average Annual Holding = " + df.format(this.h * (funcQ() / 2 + this.S)) + "/year");
        System.out.println("Average Annual Ordering = " + df.format((double) (this.k * l) / funcQ()) + "/year");
        System.out.println("Average Annual Penalty = " + df.format(this.p * this.l * funcNR() / funcQ()) + "/year");
        System.out.println("The Average Time Between The Placement of Orders = " + df.format(T) + " months");
        System.out.println("The Proportion of Order Cycles In Which No Stock Out Occurs = %" + df.format(funcFR() * 100));
        System.out.println("The Proportion of Demands That Are Not Met = " + df.format(funcNR() / funcQ()));
    }


}