# Currency Converter 💱

A Java Swing-based desktop application that converts currencies (USD, INR, EUR, GBP, JPY) in real-time using exchange rates fetched from the Frankfurter API.

![Java](https://img.shields.io/badge/Java-8+-orange?style=flat&logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=flat)
![API](https://img.shields.io/badge/API-Frankfurter-yellow?style=flat)

- 🔄 **Real-time exchange rate fetching** using Frankfurter API for multiple currencies (USD, INR, EUR, GBP, JPY)
- 💵 **Bidirectional conversion** with selectable "From" and "To" currencies
- 🔃 **Manual refresh** to update exchange rates on demand
- 📊 **Live rate display** for the selected currency pair at the top
- 🖥️ **Responsive GUI** built with Java Swing and GridBagLayout
- ⚡ **Asynchronous API calls** to prevent UI freezing during data fetching
- 🛡️ **Error handling** with fallback and user-friendly messages
- 💾 **Rate caching** for minimizing redundant API calls
- 🔄 **Swap currencies** with a dedicated button to reverse conversion direction
- 📋 **Copy to clipboard** buttons for both input and output amounts
- 🌗 **Light/Dark mode toggle** for UI theme customization
- 🖥️ **System Tray integration** for minimizing and restoring the app seamlessly

## Technologies Used 🛠️

| Technology           | Version  | Purpose                                  |
|---------------------|-----------|----------------------------------------|
| **Java**             | 8+        | Core programming language               |
| **Swing**            | Built-in  | GUI framework for desktop interface     |
| **GSON**             | 2.10.1    | JSON parsing and serialization          |
| **Frankfurter API**  | v1        | RESTful API for currency exchange rates |
| **HttpURLConnection**| Built-in  | HTTP client to perform API requests     |
| **SwingWorker**      | Built-in  | Background thread management             |

## Architecture 🏗️

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (JFrame, JComboBox, JTextField, etc.) │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│         Business Logic Layer            │
│  (Currency conversion calculations)     │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│         Service Layer                   │
│  (fetchExchangeRate() method)           │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│         External API Layer              │
│  (Frankfurter API - HTTP requests)      │
└─────────────────────────────────────────┘
```

## Installation & Setup 🚀

### 1. Clone the Repository

```
git clone https://github.com/lavanya1486/Currency_Converter.git
cd Currency_Converter
```

### 2. Verify Java Installation

```
java -version
# Ensure output shows Java 8 or higher
```

### 3. Open in IntelliJ IDEA

- Via Command Line: `idea .`
- Or via GUI: File → Open → Select `Currency_Converter` folder

### 4. Configure GSON Library

#### Method A: IntelliJ Project Structure

- File → Project Structure (Ctrl + Alt + Shift + S)
- Libraries → + → Java → Add `libraries/gson-2.10.1.jar`
- Apply and OK

#### Method B: Maven (Optional)

Add in `pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
  </dependency>
</dependencies>
```

### 5. Compile & Run

```
# Compile
javac -cp ".:libraries/gson-2.10.1.jar" src/Currency_Converter.java

# Run
java -cp ".:libraries/gson-2.10.1.jar:src" Currency_Converter
```

Or run directly from IntelliJ by right-clicking `Currency_Converter.java` and selecting **Run**.

## Application Usage

- Select currencies in "From" and "To" dropdowns.
- Enter an amount in the "Amount" field.
- Click **Convert** to see converted value.
- Use **Swap** button to reverse currencies.
- Use **Refresh Rate** to update exchange rates manually.
- Copy values with the respective **Copy** buttons.
- Toggle light/dark mode for interface preference.
- Minimize to system tray, restore via tray icon menu.

## API Documentation 🌐

**Frankfurter API Base URL:** `https://api.frankfurter.app`

Example fetch URL:
```
GET /latest?from=USD&to=INR
```

Example response:
```json
{
  "amount": 1.0,
  "base": "USD",
  "date": "2025-11-01",
  "rates": {
    "INR": 84.12
  }
}
```

**⭐ If you find this project useful, please give it a star!**

**💡 Suggestions or Pull Requests are welcome!**

***

*Made with ❤️ by Lavanya*
