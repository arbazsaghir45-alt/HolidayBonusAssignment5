package asaghir1_Assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TwoDimRaggedArrayUtility {

    public static double[][] readFile(File file) throws FileNotFoundException {
        ArrayList<double[]> rows = new ArrayList<>();

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] values = line.split("\\s+");
                double[] row = new double[values.length];

                for (int column = 0; column < values.length; column++) {
                    row[column] = Double.parseDouble(values[column]);
                }
                rows.add(row);
            }
        }

        return rows.toArray(new double[rows.size()][]);
    }

    public static void writeToFile(double[][] data, File file)
            throws FileNotFoundException {
        try (PrintWriter output = new PrintWriter(file)) {
            for (double[] row : data) {
                for (int column = 0; column < row.length; column++) {
                    if (column > 0) {
                        output.print(" ");
                    }
                    output.print(row[column]);
                }
                output.println();
            }
        }
    }


    public static double getTotal(double[][] data) {
        double total = 0.0;
        for (int row = 0; row < data.length; row++) {
            total += getRowTotal(data, row);
        }
        return total;
    }

    public static double getAverage(double[][] data) {
        int valueCount = 0;
        for (double[] row : data) {
            valueCount += row.length;
        }
        return getTotal(data) / valueCount;
    }

    public static double getRowTotal(double[][] data, int row) {
        double total = 0.0;
        for (double value : data[row]) {
            total += value;
        }
        return total;
    }


    public static double getColumnTotal(double[][] data, int column) {
        double total = 0.0;
        for (double[] row : data) {
            if (column < row.length) {
                total += row[column];
            }
        }
        return total;
    }

    public static double getHighestInRow(double[][] data, int row) {
        return data[row][getHighestInRowIndex(data, row)];
    }

    public static int getHighestInRowIndex(double[][] data, int row) {
        int highest = 0;
        for (int column = 1; column < data[row].length; column++) {
            if (data[row][column] > data[row][highest]) {
                highest = column;
            }
        }
        return highest;
    }

    public static double getLowestInRow(double[][] data, int row) {
        return data[row][getLowestInRowIndex(data, row)];
    }

    public static int getLowestInRowIndex(double[][] data, int row) {
        int lowest = 0;
        for (int column = 1; column < data[row].length; column++) {
            if (data[row][column] < data[row][lowest]) {
                lowest = column;
            }
        }
        return lowest;
    }

    public static double getHighestInColumn(double[][] data, int column) {
        return data[getHighestInColumnIndex(data, column)][column];
    }

    public static int getHighestInColumnIndex(double[][] data, int column) {
        int highest = -1;
        for (int row = 0; row < data.length; row++) {
            if (column < data[row].length
                    && (highest == -1 || data[row][column] > data[highest][column])) {
                highest = row;
            }
        }
        return highest;
    }

    public static double getLowestInColumn(double[][] data, int column) {
        return data[getLowestInColumnIndex(data, column)][column];
    }

    public static int getLowestInColumnIndex(double[][] data, int column) {
        int lowest = -1;
        for (int row = 0; row < data.length; row++) {
            if (column < data[row].length
                    && (lowest == -1 || data[row][column] < data[lowest][column])) {
                lowest = row;
            }
        }
        return lowest;
    }

    public static double getHighestInArray(double[][] data) {
        double highest = Double.NEGATIVE_INFINITY;
        for (int row = 0; row < data.length; row++) {
            if (data[row].length > 0) {
                highest = Math.max(highest, getHighestInRow(data, row));
            }
        }
        return highest;
    }

    public static double getLowestInArray(double[][] data) {
        double lowest = Double.POSITIVE_INFINITY;
        for (int row = 0; row < data.length; row++) {
            if (data[row].length > 0) {
                lowest = Math.min(lowest, getLowestInRow(data, row));
            }
        }
        return lowest;
    }
}
