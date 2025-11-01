# Currency Converter 💱

A Java Swing-based desktop application that converts between USD and INR currencies using real-time exchange rates fetched from the Frankfurter API.

![Java](https://img.shields.io/badge/Java-8+-orange?style=flat&logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=flat)
![API](https://img.shields.io/badge/API-Frankfurter-yellow?style=flat)

## Features ✨

- 🔄 **Real-time exchange rate fetching** using Frankfurter API
- 💵 **Bidirectional conversion** between INR and USD
- 🔃 **Manual refresh** to update exchange rates on demand
- 📊 **Live rate display** at the top of the interface
- 🖥️ **Responsive GUI** built with Java Swing
- ⚡ **Asynchronous API calls** to prevent UI freezing
- 🛡️ **Error handling** for network failures and invalid inputs
- 💾 **Rate caching** to minimize API calls

## Technologies Used 🛠️

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 8+ | Core programming language |
| **Swing** | Built-in | GUI framework for desktop interface |
| **GSON** | 2.10.1 | JSON parsing and serialization |
| **Frankfurter API** | v1 | RESTful API for exchange rates |
| **HttpURLConnection** | Built-in | HTTP client for API requests |
| **SwingWorker** | Built-in | Background thread management |

## Architecture 🏗️

### Application Design Pattern

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (JFrame, JTextField, JButton, etc.)    │
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
# Should show Java 8 or higher
```

### 3. Open in IntelliJ IDEA

```
# Option 1: Command line
idea .

# Option 2: GUI
# File → Open → Select Currency_Converter folder
```

### 4. Configure GSON Library

#### Method A: Using IntelliJ Project Structure

1. Navigate to **File → Project Structure** (Ctrl + Alt + Shift + S)
2. Select **Libraries** from left panel
3. Click **+** → **Java**
4. Browse to `libraries/` folder and add GSON JAR
5. Click **Apply** → **OK**

#### Method B: Maven (Alternative)

If converting to Maven project, add to `pom.xml`:

```
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```

### 5. Run the Application

```
# Compile
javac -cp ".:libraries/gson-2.10.1.jar" src/Currency_Converter.java

# Run
java -cp ".:libraries/gson-2.10.1.jar:src" Currency_Converter
```

Or use IntelliJ:
- Right-click `Currency_Converter.java` → **Run 'Currency_Converter.main()'**
- Keyboard shortcut: `Shift + F10`

## API Documentation 🌐

### Frankfurter API Details

**Base URL**: `https://api.frankfurter.app`

#### Endpoint Used

```
GET /latest?from=USD&to=INR
```

#### Request Example

```
GET https://api.frankfurter.app/latest?from=USD&to=INR HTTP/1.1
Host: api.frankfurter.app
Accept: application/json
```

#### Response Format

```
{
  "amount": 1.0,
  "base": "USD",
  "date": "2025-11-01",
  "rates": {
    "INR": 84.12
  }
}
```


## Future Enhancements 🔮

### Phase 1: Core Features
- [ ] Support for multiple currency pairs (EUR, GBP, JPY, etc.)
- [ ] Dropdown selection for currencies
- [ ] Swap button to reverse conversion direction
- [ ] Copy result to clipboard

### Phase 2: Data Features
- [ ] Historical exchange rate graphs (using JFreeChart)
- [ ] 30-day rate trend visualization
- [ ] CSV export of conversion history
- [ ] Offline mode with last known rates

### Phase 3: UI Enhancements
- [ ] Dark mode / Light mode toggle
- [ ] Custom themes
- [ ] Resizable window with responsive layout
- [ ] System tray integration

### Phase 4: Advanced Features
- [ ] Multi-currency comparison table
- [ ] Currency calculator with formulas
- [ ] Alert notifications for rate changes
- [ ] Integration with Google Sheets/Excel

### Phase 5: Architecture
- [ ] Migrate to MVC pattern
- [ ] Add database for history (SQLite)
- [ ] RESTful API wrapper module
- [ ] Plugin architecture for custom rate providers


**⭐ If you found this project helpful, please give it a star!**

**💡 Have suggestions? Open an issue or submit a pull request!**

---

*Made with ❤️ by Lavanya*
