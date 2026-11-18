package asaghir1_Assignment5;

import static org.junit.Assert.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.Test;

public class TwoDimRaggedArrayUtilityTestStudent {
    private static final double DELTA = 0.001;
    private final double[][] sales = {{-5, 2, 8}, {3}, {0, -4}};

    @Test
    public void fileMethodsPreserveRaggedRows() throws Exception {
        File file = File.createTempFile("sales", ".txt");
        try {
            Files.write(file.toPath(),
                    "-5 2 8\n3\n0 -4\n".getBytes(StandardCharsets.UTF_8));

            double[][] loaded = TwoDimRaggedArrayUtility.readFile(file);
            assertEquals(3, loaded.length);
            for (int row = 0; row < sales.length; row++) {
                assertArrayEquals(sales[row], loaded[row], DELTA);
            }

            TwoDimRaggedArrayUtility.writeToFile(sales, file);
            assertEquals(java.util.Arrays.asList("-5.0 2.0 8.0", "3.0", "0.0 -4.0"),
                    Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        } finally {
            Files.deleteIfExists(file.toPath());
        }
    }

    @Test
    public void totalsAndAverageIncludeEveryExistingValue() {
        assertEquals(4, TwoDimRaggedArrayUtility.getTotal(sales), DELTA);
        assertEquals(4.0 / 6,
                TwoDimRaggedArrayUtility.getAverage(sales), DELTA);
        assertEquals(5, TwoDimRaggedArrayUtility.getRowTotal(sales, 0), DELTA);
        assertEquals(-2, TwoDimRaggedArrayUtility.getColumnTotal(sales, 1), DELTA);
    }

    @Test
    public void rowMethodsFindValuesAndColumnIndexes() {
        assertEquals(8, TwoDimRaggedArrayUtility.getHighestInRow(sales, 0), DELTA);
        assertEquals(2, TwoDimRaggedArrayUtility.getHighestInRowIndex(sales, 0));
        assertEquals(-5, TwoDimRaggedArrayUtility.getLowestInRow(sales, 0), DELTA);
        assertEquals(0, TwoDimRaggedArrayUtility.getLowestInRowIndex(sales, 0));
    }

    @Test
    public void columnMethodsSkipRowsThatAreTooShort() {
        assertEquals(2, TwoDimRaggedArrayUtility.getHighestInColumn(sales, 1), DELTA);
        assertEquals(0, TwoDimRaggedArrayUtility.getHighestInColumnIndex(sales, 1));
        assertEquals(-4, TwoDimRaggedArrayUtility.getLowestInColumn(sales, 1), DELTA);
        assertEquals(2, TwoDimRaggedArrayUtility.getLowestInColumnIndex(sales, 1));
    }

    @Test
    public void arrayMethodsFindOverallHighestAndLowest() {
        assertEquals(8, TwoDimRaggedArrayUtility.getHighestInArray(sales), DELTA);
        assertEquals(-5, TwoDimRaggedArrayUtility.getLowestInArray(sales), DELTA);
    }
}
