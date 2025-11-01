import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import com.google.gson.*;

public class Currency_Converter {
    private static double currentRate = 87.82;

    public static void main(String[] args){
        fetchExchangeRate();

        JFrame frame = new JFrame("Currency Converter");
        JLabel labelINR = new JLabel("INR:");
        labelINR.setBounds(50, 50, 100, 30);
        JTextField textINR = new JTextField();
        textINR.setBounds(150, 50, 100, 30);

        JLabel labelUSD = new JLabel("USD:");
        labelUSD.setBounds(50, 100, 100, 30);
        JTextField textUSD = new JTextField();
        textUSD.setBounds(150, 100, 100, 30);

        JLabel labelRate = new JLabel("Rate: 1 USD = " + String.format("%.2f", currentRate) + " INR");
        labelRate.setBounds(50, 20, 250, 25);
        frame.add(labelRate);

        JButton btnINRtoUSD = new JButton("Convert INR to USD");
        btnINRtoUSD.setBounds(50, 150, 200, 30);
        btnINRtoUSD.addActionListener(e -> {
            try {
                double inr = Double.parseDouble(textINR.getText());
                double usd = inr / currentRate;
                textUSD.setText(String.format("%.2f", usd));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });

        JButton btnUSDtoINR = new JButton("Convert USD to INR");
        btnUSDtoINR.setBounds(50, 200, 200, 30);
        btnUSDtoINR.addActionListener(e -> {
            try {
                double usd = Double.parseDouble(textUSD.getText());
                double inr = usd * currentRate;
                textINR.setText(String.format("%.2f", inr));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });

        JButton btnRefresh = new JButton("Refresh Rate");
        btnRefresh.setBounds(260, 150, 80, 80);
        btnRefresh.addActionListener(e -> {
            btnRefresh.setEnabled(false);
            btnRefresh.setText("...");
            new Thread(() -> {
                fetchExchangeRate();
                SwingUtilities.invokeLater(() -> {
                    labelRate.setText("Rate: 1 USD = " + String.format("%.2f", currentRate) + " INR");
                    btnRefresh.setText("Refresh Rate");
                    btnRefresh.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, "Exchange rate updated!");
                });
            }).start();
        });
        frame.add(btnRefresh);

        frame.add(labelINR);
        frame.add(textINR);
        frame.add(labelUSD);
        frame.add(textUSD);
        frame.add(btnINRtoUSD);
        frame.add(btnUSDtoINR);

        frame.setSize(370, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void fetchExchangeRate() {
        try {
            String urlStr = "https://api.frankfurter.app/latest?from=USD&to=INR";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(response.toString()).getAsJsonObject();
            JsonObject rates = jsonObj.getAsJsonObject("rates");
            currentRate = rates.get("INR").getAsDouble();

            System.out.println("Exchange rate updated: 1 USD = " + currentRate + " INR");

        } catch (Exception e) {
            System.out.println("Failed to fetch exchange rate. Using default rate.");
            e.printStackTrace();
        }
    }
}
