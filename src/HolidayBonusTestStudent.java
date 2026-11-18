package asaghir1_Assignment5;

import static org.junit.Assert.*;
import org.junit.Test;

public class HolidayBonusTestStudent {
    private static final double DELTA = 0.001;

    @Test
    public void calculateHolidayBonusAwardsHighestLowestAndOther() {
        double[][] sales = {{20, 0}, {10, -5}, {15, 8, 7}};

        assertArrayEquals(new double[] {5000, 1000, 12000},
                HolidayBonus.calculateHolidayBonus(sales), DELTA);
    }

    @Test
    public void calculateHolidayBonusSkipsIneligibleSales() {
        double[][] sales = {{0}, {-3}, {9}};
        assertArrayEquals(new double[] {0, 0, 5000},
                HolidayBonus.calculateHolidayBonus(sales), DELTA);

        double[][] noPositiveSales = {{0}, {-3}};
        assertArrayEquals(new double[] {0, 0},
                HolidayBonus.calculateHolidayBonus(noPositiveSales), DELTA);
    }

    @Test
    public void calculateTotalHolidayBonusAddsStoreBonuses() {
        double[][] sales = {{20, 0}, {10, -5}, {15, 8, 7}};
        assertEquals(18000, HolidayBonus.calculateTotalHolidayBonus(sales), DELTA);
    }
}