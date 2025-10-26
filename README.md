# Currency Converter (Java Swing)

A simple **Java Swing-based Currency Converter** that performs conversion between **INR (Indian Rupee)** and **USD (United States Dollar)** using basic GUI components like `JLabel`, `JTextField`, and `JButton`.

***

## 🧰 Features
- Converts **INR → USD** and **USD → INR** using predefined exchange rates.  
- Built with **Swing**, Java’s lightweight GUI toolkit.  
- Interactive interface designed using absolute positioning (`setBounds`).  
- Demonstrates use of `ActionListener` for button click events.  

***

## 🧠 How It Works
- When the user enters an amount in INR and clicks **“Convert INR to USD”**, the program divides the entered value by 87.82.  
- When the user enters an amount in USD and clicks **“Convert USD to INR”**, the program multiplies the value by 87.82.  
- The result is displayed in the respective text field with two decimal precision.

***

## 💻 Code Overview
### Key Components:
| Component | Purpose |
|------------|----------|
| `JLabel` | Displays labels "INR" and "USD" |
| `JTextField` | Accepts user input for currency values |
| `JButton` | Performs conversion on click |
| `ActionListener` | Handles user interaction events |

***

## 🧪 Output Preview
When executed, a simple window appears:
- Users can input either INR or USD.  
- Clicking the corresponding button performs and displays the conversion result instantly.

***

## 📚 Concepts Demonstrated
- `JFrame` for main window  
- `JLabel`, `JTextField`, and `JButton` components  
- Handling events using **lambda expressions** and **ActionListeners**  
- Working with **absolute positioning** using `setBounds()`

***

## 📸 Screenshot
<img width="421" height="354" alt="Screenshot 2025-10-26 203027" src="https://github.com/user-attachments/assets/b7acad29-3403-4968-9829-18e7c0ac004a" />

***

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Currency-Converter.git
   ```
2. Navigate to the project folder:
   ```bash
   cd Currency-Converter
   ```
3. Compile and run:
   ```bash
   javac Currency_Converter.java
   java Currency_Converter
   ```

***
