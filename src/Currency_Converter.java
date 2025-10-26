import javax.swing.*;
import java.awt.event.*;
public class Currency_Converter {
    public static void main(String[] args){
        JFrame frame = new JFrame("Currency Converter");
        JLabel labelINR = new JLabel("INR:");
        labelINR.setBounds(50, 50, 100, 30);
        JTextField textINR = new JTextField();
        textINR.setBounds(150, 50, 100, 30);

        JLabel labelUSD = new JLabel("USD:");
        labelUSD.setBounds(50, 100, 100, 30);
        JTextField textUSD = new JTextField();
        textUSD.setBounds(150, 100, 100, 30);

        JButton btnINRtoUSD = new JButton("Convert INR to USD");
        btnINRtoUSD.setBounds(50, 150, 200, 30);
        btnINRtoUSD.addActionListener(e -> {
            double inr = Double.parseDouble(textINR.getText());
            double usd = inr / 87.82;
            textUSD.setText(String.format("%.2f", usd));
        });

        JButton btnUSDtoINR = new JButton("Convert USD to INR");
        btnUSDtoINR.setBounds(50, 200, 200, 30);
        btnUSDtoINR.addActionListener(e -> {
            double usd = Double.parseDouble(textUSD.getText());
            double inr = usd * 87.82;
            textINR.setText(String.format("%.2f", inr));
        });

        frame.add(labelINR);
        frame.add(textINR);
        frame.add(labelUSD);
        frame.add(textUSD);
        frame.add(btnINRtoUSD);
        frame.add(btnUSDtoINR);

        frame.setSize(350, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
