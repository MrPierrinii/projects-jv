// CSMC 215 Intermediate Programming 
// Cedrick Pierre 
// University of Maryland 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project3 extends JFrame implements ActionListener {

    private JLabel distanceLabel, gasCostLabel, gasMileageLabel, hotelCostLabel, foodCostLabel, daysLabel, attractionsLabel, outputLabel;
    private JTextField distanceEntry, gasCostEntry, gasMileageEntry, hotelCostEntry, foodCostEntry, daysEntry, attractionsEntry;
    private JComboBox<String> distanceUnit, gasCostUnit, gasMileageUnit;
    private JButton calculateButton;

    public Project3() {
        super("Road Trip Cost Estimator");

        // Initialize components
        distanceLabel = new JLabel("Distance:");
        gasCostLabel = new JLabel("Gas Cost:");
        gasMileageLabel = new JLabel("Gas Mileage:");
        hotelCostLabel = new JLabel("Hotel Cost/Day:");
        foodCostLabel = new JLabel("Food Cost/Day:");
        daysLabel = new JLabel("Number of Days:");
        attractionsLabel = new JLabel("Attractions:");
        outputLabel = new JLabel("Total Cost:");

        distanceEntry = new JTextField(10);
        gasCostEntry = new JTextField(10);
        gasMileageEntry = new JTextField(10);
        hotelCostEntry = new JTextField(10);
        foodCostEntry = new JTextField(10);
        daysEntry = new JTextField(10);
        attractionsEntry = new JTextField(10);

        distanceUnit = new JComboBox<>(new String[]{"miles", "kilometers"});
        gasCostUnit = new JComboBox<>(new String[]{"<span class="math-inline">/gallon", "</span>/liter"});
        gasMileageUnit = new JComboBox<>(new String[]{"mpg", "km/l"});

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        // Layout components
        JPanel inputPanel = new JPanel(new GridLayout(8, 3, 5, 5));
        inputPanel.add(distanceLabel);
        inputPanel.add(distanceEntry);
        inputPanel.add(distanceUnit);
        inputPanel.add(gasCostLabel);
        inputPanel.add(gasCostEntry);
        inputPanel.add(gasCostUnit);
        inputPanel.add(gasMileageLabel);
        inputPanel.add(gasMileageEntry);
        inputPanel.add(gasMileageUnit);
        inputPanel.add(hotelCostLabel);
        inputPanel.add(hotelCostEntry);
        inputPanel.add(new JLabel());
        inputPanel.add(foodCostLabel);
        inputPanel.add(foodCostEntry);
        inputPanel.add(new JLabel());
        inputPanel.add(daysLabel);
        inputPanel.add(daysEntry);
        inputPanel.add(new JLabel());
        inputPanel.add(attractionsLabel);
        inputPanel.add(attractionsEntry);
        inputPanel.add(new JLabel());

        JPanel outputPanel = new JPanel(new FlowLayout());
        outputPanel.add(outputLabel);
        outputPanel.add(new JLabel());

        add(inputPanel, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
        add(outputPanel, BorderLayout.NORTH);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                double distance = Double.parseDouble(distanceEntry.getText());
                double gasCost = Double.parseDouble(gasCostEntry.getText());
                double gasMileage = Double.parseDouble(gasMileageEntry.getText());
                double hotelCost = Double.parseDouble(hotelCostEntry.getText());
                double foodCost = Double.parseDouble(foodCostEntry.getText());
                int days = Integer.parseInt(daysEntry.getText());
                double attractions = Double.parseDouble(attractionsEntry.getText());

                double totalCost = calculateTripCost(distance, gasCost, gasMileage, hotelCost, foodCost, days, attractions);
                outputLabel.setText("Total Cost: $" + String.format("%.2f", totalCost));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double calculateTripCost(double distance, double gasCost, double gasMileage,
