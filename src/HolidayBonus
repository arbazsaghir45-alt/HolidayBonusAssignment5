package asaghir1_Assignment5;

public class HolidayBonus {
    private static final double HIGHEST_BONUS = 5000.0;
    private static final double LOWEST_BONUS = 1000.0;
    private static final double OTHER_BONUS = 2000.0;

 
    public static double[] calculateHolidayBonus(double[][] data) {
        double[] bonuses = new double[data.length];
        int categoryCount = 0;

        for (double[] store : data) {
            categoryCount = Math.max(categoryCount, store.length);
        }

        for (int column = 0; column < categoryCount; column++) {
            int highestStore =
                    TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, column);
            int lowestEligibleStore = -1;

            for (int store = 0; store < data.length; store++) {
                if (column >= data[store].length || data[store][column] <= 0) {
                    continue;
                }

                if (lowestEligibleStore == -1
                        || data[store][column] < data[lowestEligibleStore][column]) {
                    lowestEligibleStore = store;
                }
            }

            for (int store = 0; store < data.length; store++) {
                if (column >= data[store].length || data[store][column] <= 0) {
                    continue;
                }

                if (store == highestStore) {
                    bonuses[store] += HIGHEST_BONUS;
                } else if (store == lowestEligibleStore) {
                    bonuses[store] += LOWEST_BONUS;
                } else {
                    bonuses[store] += OTHER_BONUS;
                }
            }
        }
        return bonuses;
    }

    public static double calculateTotalHolidayBonus(double[][] data) {
        double total = 0.0;
        for (double bonus : calculateHolidayBonus(data)) {
            total += bonus;
        }
        return total;
    }
}
