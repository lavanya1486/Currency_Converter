import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import com.google.gson.*;

public class Currency_Converter {
    private static double currentRate = 1.0;
    private static final String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY"};
    private static JFrame frame;
    private static JLabel labelRate;
    private static JComboBox<String> comboFrom;
    private static JComboBox<String> comboTo;
    private static JTextField textFrom;
    private static JTextField textTo;
    private static JButton btnToggleTheme;
    private static boolean darkMode = false;
    private static SystemTray tray;
    private static TrayIcon trayIcon;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setupUI();
            fetchAndUpdateRate();
            setupSystemTray();
        });
    }

    private static void setupUI() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(460, 320));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(new JLabel("From:"), gbc);
        comboFrom = new JComboBox<>(currencies);
        comboFrom.setSelectedItem("USD");
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        frame.add(comboFrom, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        frame.add(new JLabel("To:"), gbc);
        comboTo = new JComboBox<>(currencies);
        comboTo.setSelectedItem("INR");
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        frame.add(comboTo, gbc);

        labelRate = new JLabel("Rate: -");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        frame.add(labelRate, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        frame.add(new JLabel("Amount:"), gbc);
        textFrom = new JTextField();
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        frame.add(textFrom, gbc);
        JButton btnCopyFrom = new JButton("Copy");
        gbc.gridx = 3; gbc.gridy = 3; gbc.gridwidth = 1;
        frame.add(btnCopyFrom, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        frame.add(new JLabel("Converted:"), gbc);
        textTo = new JTextField();
        textTo.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        frame.add(textTo, gbc);
        JButton btnCopyTo = new JButton("Copy");
        gbc.gridx = 3; gbc.gridy = 4; gbc.gridwidth = 1;
        frame.add(btnCopyTo, gbc);

        JButton btnConvert = new JButton("Convert");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1;
        frame.add(btnConvert, gbc);
        JButton btnSwap = new JButton("Swap");
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 1;
        frame.add(btnSwap, gbc);
        JButton btnRefresh = new JButton("Refresh Rate");
        gbc.gridx = 2; gbc.gridy = 5; gbc.gridwidth = 1;
        frame.add(btnRefresh, gbc);

        btnToggleTheme = new JButton("Switch to Dark Mode");
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3;
        frame.add(btnToggleTheme, gbc);

        // Event listeners
        comboFrom.addActionListener(e -> {
            fetchAndUpdateRate();
            textTo.setText("");
        });
        comboTo.addActionListener(e -> {
            fetchAndUpdateRate();
            textTo.setText("");
        });
        btnConvert.addActionListener(e -> {
            try {
                double amountFrom = Double.parseDouble(textFrom.getText());
                double amountTo = amountFrom * currentRate;
                textTo.setText(String.format("%.4f", amountTo));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });
        btnSwap.addActionListener(e -> {
            int fromIndex = comboFrom.getSelectedIndex();
            int toIndex = comboTo.getSelectedIndex();
            comboFrom.setSelectedIndex(toIndex);
            comboTo.setSelectedIndex(fromIndex);
            textFrom.setText("");
            textTo.setText("");
            fetchAndUpdateRate();
        });
        btnRefresh.addActionListener(e -> {
            btnRefresh.setEnabled(false);
            btnRefresh.setText("...");
            new Thread(() -> {
                fetchAndUpdateRate();
                SwingUtilities.invokeLater(() -> {
                    btnRefresh.setText("Refresh Rate");
                    btnRefresh.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, "Exchange rate updated!");
                });
            }).start();
        });
        btnCopyFrom.addActionListener(e -> {
            String text = textFrom.getText();
            if (!text.isEmpty()) copyToClipboard(text);
        });
        btnCopyTo.addActionListener(e -> {
            String text = textTo.getText();
            if (!text.isEmpty()) copyToClipboard(text);
        });
        btnToggleTheme.addActionListener(e -> toggleTheme());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void fetchAndUpdateRate() {
        String fromCurrency = (String) comboFrom.getSelectedItem();
        String toCurrency = (String) comboTo.getSelectedItem();
        if (fromCurrency.equals(toCurrency)) {
            currentRate = 1.0;
            labelRate.setText("Rate: 1 " + fromCurrency + " = 1 " + toCurrency);
            return;
        }
        try {
            String urlStr = "https://api.frankfurter.app/latest?from=" + fromCurrency + "&to=" + toCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(response.toString()).getAsJsonObject();
            JsonObject rates = jsonObj.getAsJsonObject("rates");
            currentRate = rates.get(toCurrency).getAsDouble();

            SwingUtilities.invokeLater(() ->
                    labelRate.setText("Rate: 1 " + fromCurrency + " = " +
                            String.format("%.4f", currentRate) + " " + toCurrency));
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> labelRate.setText("Failed to fetch rate."));
            currentRate = 1.0;
            System.out.println("Failed to fetch exchange rate. Using default rate.");
            e.printStackTrace();
        }
    }

    private static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        JOptionPane.showMessageDialog(null, "Copied to clipboard: " + text);
    }

    private static void toggleTheme() {
        if (!darkMode) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            setComponentColors(frame.getContentPane(), Color.LIGHT_GRAY, Color.DARK_GRAY);
            btnToggleTheme.setText("Switch to Light Mode");
            darkMode = true;
        } else {
            frame.getContentPane().setBackground(Color.WHITE);
            setComponentColors(frame.getContentPane(), Color.BLACK, Color.WHITE);
            btnToggleTheme.setText("Switch to Dark Mode");
            darkMode = false;
        }
    }

    private static void setComponentColors(Container container, Color fg, Color bg) {
        for (Component comp : container.getComponents()) {
            comp.setForeground(fg);
            comp.setBackground(bg);
            if (comp instanceof Container) {
                setComponentColors((Container) comp, fg, bg);
            }
        }
    }

    private static void setupSystemTray() {
        if (!SystemTray.isSupported()) return;
        tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // Change to your icon file

        PopupMenu popup = new PopupMenu();
        MenuItem openItem = new MenuItem("Open");
        MenuItem exitItem = new MenuItem("Exit");

        openItem.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                frame.setVisible(true);
                frame.setExtendedState(JFrame.NORMAL);
                tray.remove(trayIcon);
            });
        });
        exitItem.addActionListener(e -> {
            tray.remove(trayIcon);
            System.exit(0);
        });
        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);

        trayIcon = new TrayIcon(image, "Currency Converter", popup);
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                frame.setVisible(true);
                frame.setExtendedState(JFrame.NORMAL);
                tray.remove(trayIcon);
            });
        });
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent e) {
                frame.setVisible(false);
            }
            public void windowClosing(java.awt.event.WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }
}
