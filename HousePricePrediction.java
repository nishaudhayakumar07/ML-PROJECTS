import java.util.Scanner;

public class HousePricePrediction {

    // Function to calculate mean
    static double mean(double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Training dataset
        // Features: Area(sq.ft), Rooms
        double[][] houses = {
            {1000, 2},
            {1500, 3},
            {2000, 4},
            {2500, 4},
            {3000, 5}
        };

        // House prices in lakhs
        double[] prices = {
            50,
            75,
            100,
            125,
            150
        };

        int n = houses.length;

        // Calculate coefficients for multiple linear regression

        double sumArea = 0;
        double sumRooms = 0;
        double sumPrice = 0;

        for(int i = 0; i < n; i++) {
            sumArea += houses[i][0];
            sumRooms += houses[i][1];
            sumPrice += prices[i];
        }

        double meanArea = sumArea / n;
        double meanRooms = sumRooms / n;
        double meanPrice = sumPrice / n;


        double numeratorArea = 0;
        double denominatorArea = 0;

        double numeratorRooms = 0;
        double denominatorRooms = 0;


        for(int i = 0; i < n; i++) {

            numeratorArea += (houses[i][0] - meanArea) * 
                             (prices[i] - meanPrice);

            denominatorArea += Math.pow(houses[i][0] - meanArea, 2);


            numeratorRooms += (houses[i][1] - meanRooms) * 
                              (prices[i] - meanPrice);

            denominatorRooms += Math.pow(houses[i][1] - meanRooms, 2);
        }


        // Regression coefficients
        double areaCoefficient = numeratorArea / denominatorArea;
        double roomCoefficient = numeratorRooms / denominatorRooms;


        // Intercept
        double intercept = meanPrice -
                (areaCoefficient * meanArea) -
                (roomCoefficient * meanRooms);


        System.out.println("===== House Price Prediction System =====");


        // User input
        System.out.print("Enter house area (sq.ft): ");
        double userArea = sc.nextDouble();


        System.out.print("Enter number of rooms: ");
        double userRooms = sc.nextDouble();


        // Prediction formula
        double predictedPrice = intercept +
                (areaCoefficient * userArea) +
                (roomCoefficient * userRooms);


        System.out.println("\nPredicted House Price = "
                + String.format("%.2f", predictedPrice)
                + " Lakhs");


        sc.close();
    }
}