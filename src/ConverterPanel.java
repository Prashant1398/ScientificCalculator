import javax.swing.*;
import java.awt.*;

public class ConverterPanel extends JPanel {
    private JComboBox<String> typeBox;
    private JTextField inputField = new JTextField(10);
    private JTextField outputField = new JTextField(10);
    private JButton convertBtn = new JButton("Convert");

    public ConverterPanel() {
        setLayout(new GridLayout(5, 2));

        typeBox = new JComboBox<>(new String[] {
            "cm to inches", "inches to cm",
            "kg to lb", "lb to kg",
            "C to F", "F to C"
        });

        add(new JLabel("Conversion Type:"));
        add(typeBox);
        add(new JLabel("Input:"));
        add(inputField);
        add(new JLabel("Output:"));
        outputField.setEditable(false);
        add(outputField);
        add(convertBtn);

        convertBtn.addActionListener(e -> {
            try {
                double input = Double.parseDouble(inputField.getText());
                double result = convert(input, (String) typeBox.getSelectedItem());
                outputField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                outputField.setText("Invalid");
            }
        });
    }

    private double convert(double value, String type) {
        switch (type) {
            case "cm to inches": return value / 2.54;
            case "inches to cm": return value * 2.54;
            case "kg to lb": return value * 2.20462;
            case "lb to kg": return value / 2.20462;
            case "C to F": return value * 9/5 + 32;
            case "F to C": return (value - 32) * 5/9;
            default: return 0;
        }
    }
}
