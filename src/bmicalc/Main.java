package bmicalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    private JFrame frame;
    private JTextField heightField, weightField, resultField;
    private JButton calculateButton, resetButton, exitButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("BMI Calculator");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("BMI CALCULATOR");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        
        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(240, 248, 255));
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // Height Input
        JLabel heightLabel = new JLabel("Height (meters):");
        heightLabel.setBounds(50, 50, 150, 25);
        heightLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Made bold
        contentPanel.add(heightLabel);
        
        heightField = new JTextField();
        heightField.setBounds(200, 50, 150, 25);
        heightField.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        contentPanel.add(heightField);
        
        // Weight Input
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setBounds(50, 100, 150, 25);
        weightLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Made bold
        contentPanel.add(weightLabel);
        
        weightField = new JTextField();
        weightField.setBounds(200, 100, 150, 25);
        weightField.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        contentPanel.add(weightField);
        
        // Result
        JLabel resultLabel = new JLabel("BMI Result:");
        resultLabel.setBounds(50, 150, 150, 25);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Made bold
        contentPanel.add(resultLabel);
        
        resultField = new JTextField();
        resultField.setBounds(200, 150, 150, 25);
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        contentPanel.add(resultField);
        
        // Buttons
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(50, 200, 100, 30);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
        contentPanel.add(calculateButton);
        
        resetButton = new JButton("Reset");
        resetButton.setBounds(170, 200, 100, 30);
        resetButton.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                heightField.setText("");
                weightField.setText("");
                resultField.setText("");
            }
        });
        contentPanel.add(resetButton);
        
        exitButton = new JButton("Exit");
        exitButton.setBounds(290, 200, 100, 30);
        exitButton.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPanel.add(exitButton);
        
        // BMI Info Panel - Modified for centered text
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK), 
            "BMI Categories", 
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 14))); // Made bold and centered title
        infoPanel.setBounds(400, 50, 150, 200);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(240, 248, 255));
        
        // Center-aligned category labels
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(createCenteredCategoryLabel("Underweight: <18.5", Color.BLUE));
        infoPanel.add(createCenteredCategoryLabel("Normal: 18.5-24.9", Color.GREEN.darker()));
        infoPanel.add(createCenteredCategoryLabel("Overweight: 25-29.9", Color.ORANGE));
        infoPanel.add(createCenteredCategoryLabel("Obese I: 30-34.9", Color.RED));
        infoPanel.add(createCenteredCategoryLabel("Obese II: 35-39.9", Color.RED.darker()));
        infoPanel.add(createCenteredCategoryLabel("Obese III: ≥40", new Color(139, 0, 0)));
        
        contentPanel.add(infoPanel);
    }
    
    // Modified method to create centered category labels
    private JLabel createCenteredCategoryLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER); // Center aligned
        label.setFont(new Font("Arial", Font.BOLD, 12)); // Made bold
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    private void calculateBMI() {
        try {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            
            if (height <= 0 || weight <= 0) {
                JOptionPane.showMessageDialog(frame, "Height and weight must be positive numbers!", 
                    "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double bmi = weight / (height * height);
            resultField.setText(String.format("%.2f", bmi));
            
            String category;
            Color color;
            
            if (bmi < 18.5) {
                category = "Underweight";
                color = Color.BLUE;
            } else if (bmi < 25) {
                category = "Normal weight";
                color = Color.GREEN.darker();
            } else if (bmi < 30) {
                category = "Overweight";
                color = Color.ORANGE;
            } else if (bmi < 35) {
                category = "Obese (Class I)";
                color = Color.RED;
            } else if (bmi < 40) {
                category = "Obese (Class II)";
                color = Color.RED.darker();
            } else {
                category = "Obese (Class III)";
                color = new Color(139, 0, 0);
            }
            
            JLabel resultLabel = new JLabel("Your BMI category: " + category, SwingConstants.CENTER); // Centered
            resultLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Made bold
            resultLabel.setForeground(color);
            
            JOptionPane.showMessageDialog(frame, resultLabel, "BMI Result", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for height and weight!", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}