package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private double number;
    private double Z;
    private double LZ;

    Reader(double number) {
        this.number = number;
        this.Z = 0;
        this.LZ = 0;
    }

    public double getZ() {
        return Z;
    }

    public double getLZ() {
        return LZ;
    }

    public void read3() throws IOException {
        try (BufferedReader bReader = new BufferedReader(new FileReader("src/Data.txt"))) {
            String line;
            double FInt;
            double sub = Double.MAX_VALUE;
            double subtract;

            while ((line = bReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length < 3) {
                    continue;  // Ensure there are enough parts in the line
                }

                try {
                    FInt = Double.parseDouble(parts[1]);
                    subtract = Math.abs(this.number - FInt);

                    if (sub > subtract) {
                        sub = subtract;
                        this.Z = Double.parseDouble(parts[0]);
                        this.LZ = Double.parseDouble(parts[2]);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    // Simply ignore lines with parsing errors
                }
            }
        }
    }
}