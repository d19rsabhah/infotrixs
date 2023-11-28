# infotrixs
# Real-Time Currency Converter Documentation
## Overview
#### The Real Time Currency Converter is a command-line-based Java application that allows users to perform currency conversion, view exchange rates, add favorite currencies, and view their favorite currency list. The application utilizes an external API to fetch live exchange rate data.

## Features
### 1. Convert Currency
#### Users can convert an amount from one currency to another by specifying the source and target currencies. The application fetches live exchange rates to perform the conversion.

### 2. Show Exchange Rates
#### Users can view the available exchange rates for various currencies. The application retrieves this information from the external API and displays it in the console.

### 3. Add Favorite Currency
#### Users can add their favorite currencies to a list. The added currencies are stored in uppercase format and can be viewed later.

### 4. View Favorite Currencies
#### Users can view the list of their favorite currencies. The application displays the favorite currencies in the console.

### 5. Quit
#### Allows users to exit the application.

## Implementation
### 1. API Integration
#### The application integrates with the exchangerate.host API to fetch live exchange rate data.

### 2. Exchange Rate Retrieval
#### Exchange rates are retrieved from the API using HTTP requests. The data is processed and stored in a JSON object.
#### private static void retrieveExchangeRates() {...}

### 3. Currency Conversion
#### Currency conversion is performed based on the user's input. The application calculates the converted amount using the fetched exchange rates.
#### private static void convertCurrency() {...}

### 4. Display Exchange Rates
#### The application displays the available exchange rates to the user.
#### private static void showExchangeRates() {...}

### 5. Manage Favorite Currencies
#### Users can add favorite currencies to a set and view the list of their favorite currencies.
#### private static void addFavoriteCurrency(String currency) {...}
#### private static void viewFavoriteCurrencies() {...}

### Usage
#### Run the application.
#### Choose from the menu options:
#### 1: Convert Currency
#### 2: Show Exchange Rates
#### 3: Add Favorite Currency
#### 4: View Favorite Currencies
#### 5: Quit

### Dependencies
#### Java
#### exchangerate.host API
#### Future Enhancements
#### User authentication for personalized favorite currency lists.
#### Graphical user interface (GUI) for a more user-friendly experience.
#### Support for historical exchange rates.

### Conclusion
#### The Real Time Currency Converter provides a convenient way for users to perform currency conversions and stay updated on live exchange rates. With the integration of an external API, the application ensures accurate and real-time currency information.
