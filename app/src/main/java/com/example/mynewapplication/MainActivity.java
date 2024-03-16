package com.example.mynewapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText valueEditText;
    private TextView resultTextView;
    private Spinner sourceUnitSpinner;
    private Spinner destinationUnitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueEditText = findViewById(R.id.valueEditText);
        resultTextView = findViewById(R.id.resultTextView);
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        Button convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(this,
                R.array.source_units_array, android.R.layout.simple_spinner_item);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(sourceAdapter);

        ArrayAdapter<CharSequence> destinationAdapter = ArrayAdapter.createFromResource(this,
                R.array.destination_units_array, android.R.layout.simple_spinner_item);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationUnitSpinner.setAdapter(destinationAdapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        // Validate input value
        String inputValueString = valueEditText.getText().toString();
        if (inputValueString.isEmpty()) {
            Toast.makeText(this, "Please enter a valid input value", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputValueString);
            String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
            String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
            double result = 0.0;

            // Handle case where source unit and destination unit are the same
            if (sourceUnit.equals(destinationUnit)) {
                Toast.makeText(this, "Source unit and destination unit cannot be the same", Toast.LENGTH_SHORT).show();
                return;
            }

            // Perform unit conversions based on selected units
            if (sourceUnit.equals("Inch") && destinationUnit.equals("Centimeter")) {
                result = inputValue * 2.54; // 1 inch = 2.54 centimeters
            } else if (sourceUnit.equals("Foot") && destinationUnit.equals("Centimeter")) {
                result = inputValue * 30.48; // 1 foot = 30.48 centimeters
            } else if (sourceUnit.equals("Yard") && destinationUnit.equals("Centimeter")) {
                result = inputValue * 91.44; // 1 yard = 91.44 centimeters
            } else if (sourceUnit.equals("Mile") && destinationUnit.equals("Kilometer")) {
                result = inputValue * 1.60934; // 1 mile = 1.60934 kilometers
            } else if (sourceUnit.equals("Pound") && destinationUnit.equals("Kilogram")) {
                result = inputValue * 0.453592; // 1 pound = 0.453592 kilograms
            } else if (sourceUnit.equals("Ounce") && destinationUnit.equals("Gram")) {
                result = inputValue * 28.3495; // 1 ounce = 28.3495 grams
            } else if (sourceUnit.equals("Ton") && destinationUnit.equals("Kilogram")) {
                result = inputValue * 907.185; // 1 ton = 907.185 kilograms
            } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Fahrenheit")) {
                result = (inputValue * 1.8) + 32; // Celsius to Fahrenheit conversion
            } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Celsius")) {
                result = (inputValue - 32) / 1.8; // Fahrenheit to Celsius conversion
            } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Kelvin")) {
                result = inputValue + 273.15; // Celsius to Kelvin conversion
            } else if (sourceUnit.equals("Kelvin") && destinationUnit.equals("Celsius")) {
                result = inputValue - 273.15; // Kelvin to Celsius conversion
            }

            // Update the resultTextView with the converted value
            resultTextView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input value", Toast.LENGTH_SHORT).show();
        }
    }
}