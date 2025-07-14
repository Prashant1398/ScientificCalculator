import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class CalculatorPanel extends JPanel {
    private JTextField display = new JTextField();

    public CalculatorPanel() {
        setLayout(new BorderLayout());
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        String[] buttons = {
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "*", "pow",
            "1", "2", "3", "-", "log",
            "0", ".", "=", "+", "sin", "C"
        };

        JPanel panel = new JPanel(new GridLayout(5, 5));
        for (String b : buttons) {
            JButton button = new JButton(b);
            button.addActionListener(e -> onPress(b));
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);
    }

    private void onPress(String value) {
    if (value.equals("=")) {
        try {
            String expr = display.getText();
            if (expr.isEmpty()) {
                display.setText("");
                return;
            }
            double result = eval(expr);
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                display.setText("Error");
            } else {
                display.setText(String.valueOf(result));
            }
        } catch (Exception e) {
            display.setText("Error");
        }
    } else if (value.equals("sqrt")) {
        try {
            double val = eval(display.getText());
            if (val < 0) {
                display.setText("Error");
            } else {
                display.setText(String.valueOf(Math.sqrt(val)));
            }
        } catch (Exception e) {
            display.setText("Error");
        }
    } else if (value.equals("log")) {
        try {
            double val = eval(display.getText());
            if (val <= 0) {
                display.setText("Error");
            } else {
                display.setText(String.valueOf(Math.log10(val)));
            }
        } catch (Exception e) {
            display.setText("Error");
        }
    } else if (value.equals("sin")) {
        try {
            double val = eval(display.getText());
            display.setText(String.valueOf(Math.sin(Math.toRadians(val))));
        } catch (Exception e) {
            display.setText("Error");
        }
    } else if (value.equals("pow")) {
        display.setText(display.getText() + "^");
    } else if (value.equals("C")) {
        display.setText("");
    } else {
        display.setText(display.getText() + value);
    }
}

private double eval(String expr) throws Exception {
    if (expr == null || expr.trim().isEmpty()) throw new Exception("Empty");
    // exp4j supports ^ for power, so no need for manual split
    Expression expression = new ExpressionBuilder(expr).build();
    double result = expression.evaluate();
    if (Double.isNaN(result) || Double.isInfinite(result)) {
        throw new Exception("Invalid expression");
    }
    return result;
}


}
