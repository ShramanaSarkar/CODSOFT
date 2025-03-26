import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class currencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
        try {
            String urlString = API_URL + baseCurrency;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract exchange rate using simple String parsing
            String json = response.toString();
            String searchKey = "\"" + targetCurrency + "\":";
            int index = json.indexOf(searchKey);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid currency: " + targetCurrency);
            }
            int start = index + searchKey.length();
            int end = json.indexOf(",", start);
            if (end == -1) {
                end = json.indexOf("}", start); // Last element case
            }
            double exchangeRate = Double.parseDouble(json.substring(start, end).trim());

            return amount * exchangeRate;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);

        if (convertedAmount != -1) {
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        }

        scanner.close();
    }
}
