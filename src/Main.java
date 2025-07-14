import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scientific Calculator + Unit Converter");
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Calculator", new CalculatorPanel());
        tabs.addTab("Converter", new ConverterPanel());

        frame.add(tabs);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
