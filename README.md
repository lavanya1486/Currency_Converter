# Currency Converter 💱

A modern, easy-to-use desktop currency converter built with Java Swing. Instantly convert between popular currencies—USD, INR, EUR, GBP, and JPY—using real-time rates from the Frankfurter API.

![Java](https://img.shields.io/badge/Java-8+-orange?style=flat&logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=flat)
![API](https://img.shields.io/badge/API-Frankfurter-yellow?style=flat)

### Features 

- 🔄 **Live, real-time conversion:** Up-to-the-minute exchange rates, always fresh from the Frankfurter API.
- 💵 **Convert both ways:** Pick any "From" and "To" currency—convert in either direction.
- 🔃 **Manual refresh:** Got new rates? Hit "Refresh" to grab the latest numbers.
- 📊 **Instant rate display:** Always see the current exchange rate for your chosen pair, right up top.
- 🖥️ **Clean, responsive UI:** Built with GridBagLayout for a polished look that adapts to your screen.
- ⚡ **No more frozen screens:** Asynchronous API calls keep everything smooth and snappy.
- 🛡️ **Smart error handling:** If something goes wrong, you get clear, helpful messages (and the app tries again in the background).
- 💾 **Rate caching:** The app remembers rates to cut down on unnecessary API calls.
- 🔄 **One-tap swap:** Instantly flip your conversion direction with the "Swap" button.
- 📋 **Copy with a click:** Easily copy the input or converted amounts right to your clipboard.
- 🌗 **Dark/light mode:** Switch between themes for ultimate comfort.
- 🖥️ **System tray magic:** Minimize the app to your tray, restore it anytime—it stays out of your way until you need it.

***

### How It Works—At a Glance

```
[User Interface]
    ⬇️
[Currency conversion logic]
    ⬇️
[Exchange rate service]
    ⬇️
[Frankfurter API]
```
- **Presentation Layer:** All the Swing components, like windows, dropdowns, and buttons.
- **Business Logic:** Currency calculations and unit conversions.
- **Service Layer:** Handles fetching exchange rates and caching them.
- **External API Connection:** Talks to the Frankfurter API for accurate data.

***

### Getting Started 🚀

#### 1. Clone This Repository

```
git clone https://github.com/lavanya1486/Currency_Converter.git
cd Currency_Converter
```

#### 2. Make Sure Java Is Installed

```
java -version
```
You’ll need Java 8 or above.

#### 3. Open the Project

- **In IntelliJ IDEA**:  
  Open the folder normally, or from the terminal:  
  `idea .`

#### 4. Add the GSON Library

- **IntelliJ**:  
  Go to File → Project Structure → Libraries → Add → Java; select `libraries/gson-2.10.1.jar`, then click OK.  
- **With Maven (optional):**  
  Add to your `pom.xml`:
  ```xml
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
  </dependency>
  ```

#### 5. Build & Run

```
javac -cp ".:libraries/gson-2.10.1.jar" src/Currency_Converter.java
java -cp ".:libraries/gson-2.10.1.jar:src" Currency_Converter
```
Or, just right-click and run `Currency_Converter.java` in your IDE.

***

### Using the App

- Pick currencies in the "From" and "To" menus.
- Enter the amount to convert.
- Click **Convert** for an instant result.
- Use **Swap** to quickly switch directions.
- Refresh for new rates any time.
- Copy values with the **Copy** buttons.
- Toggle between light and dark modes.
- Minimize to the system tray for quick access.

***

### About the API

- **Frankfurter API Base URL:** `https://api.frankfurter.app`
- **Example request:**  
  `/latest?from=USD&to=INR`
- **Sample JSON response:**

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

***

⭐ If this project helped you, a star goes a long way!  
💡 Suggestions and pull requests are always welcome.

***

*Made with ❤️ by Lavanya*
