package currency;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CurrencyConverterClass {
    private static JSONObject exchangeRates;

    private static final String ACCESS_KEY = "54ddb09960879e82450b85eae5547b2b";
    private static final String BASE_URL = "http://api.exchangerate.host/live?access_key=" + ACCESS_KEY;

    private static final Set<String> favoriteCurrencies = new HashSet<>();

    public static void main(String[] args) {
        retrieveExchangeRates();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nCurrency Converter Menu :");
            System.out.println("1. Convert Currency");
            System.out.println("2. Show Exchange Rates");
            System.out.println("3. Add Favorite Currency");
            System.out.println("4. View Favorite Currencies");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    convertCurrency();
                    break;
                case 2:
                    showExchangeRates();
                    break;
                case 3:
                    System.out.print("Enter currency to add to favorites: ");
                    String currencyToAdd = scanner.nextLine();
                    addFavoriteCurrency(currencyToAdd);
                    break;
                case 4:
                    viewFavoriteCurrencies();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void retrieveExchangeRates() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();

            exchangeRates = new JSONObject(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertCurrency() {
        if (exchangeRates != null && exchangeRates.getBoolean("success")) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter source currency: ");
            String sourceCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter target currency: ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            String sourceKey = "USD" + sourceCurrency;
            String targetKey = "USD" + targetCurrency;

            JSONObject quotes = exchangeRates.getJSONObject("quotes");

            if (quotes.has(sourceKey) && quotes.has(targetKey)) {
                double sourceRate = quotes.getDouble(sourceKey);
                double targetRate = quotes.getDouble(targetKey);
                double convertedAmount = amount * (1 / sourceRate) * targetRate;

                System.out.println(amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
            } else {
                System.out.println("Invalid source or target currency!");
            }
        } else {
            System.out.println("Failed to fetch exchange rates!");
        }
    }

    private static void showExchangeRates() {
        // Display the available exchange rates from the exchangeRates JSONObject
        if (exchangeRates != null && exchangeRates.getBoolean("success")) {
            JSONObject quotes = exchangeRates.getJSONObject("quotes");
            System.out.println("Available exchange rates: ");
            for (String key : quotes.keySet()) {
                System.out.println(key.substring(3) + ": " + quotes.get(key));
            }
        } else {
            System.out.println("Failed to fetch exchange rates!");
        }
    }

    private static void addFavoriteCurrency(String currency) {
        favoriteCurrencies.add(currency.toUpperCase());
        System.out.println(currency.toUpperCase() + " added to favorite currencies.");
    }

    private static void viewFavoriteCurrencies() {
        if (favoriteCurrencies.isEmpty()) {
            System.out.println("No favorite currencies added yet.");
        } else {
            System.out.println("Favorite currencies: ");
            for (String currency : favoriteCurrencies) {
                System.out.println(currency);
            }
        }
    }
}
