package com.example.firstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    char ch;
    double firstNumber;
    double secondNumber;
    double calculation;
    boolean isCalculationDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.textViewResult);
        result.setText("");
        isCalculationDone = false;
    }

    public void showNumber(View view) {
        if (isCalculationDone) {
            result.setText("");
            isCalculationDone = false;
        }

        Button button = (Button) view;
        result.append(button.getText().toString());
    }

    public void Operation(View view) {
        ch = ((Button) view).getText().toString().charAt(0);
        firstNumber = Double.parseDouble((result.getText().toString()));
        result.setText("");
    }

    public void equalOperation(View view) {
        secondNumber = Double.parseDouble(result.getText().toString());
        calculation = 0;

        switch (ch) {
            case '+': {
                calculation = firstNumber + secondNumber;
                break;
            }
            case '-': {
                calculation = firstNumber - secondNumber;
                break;
            }
            case 'x': {
                calculation = firstNumber * secondNumber;
                break;
            }
            case '/': {
                if (secondNumber != 0) {
                    calculation = firstNumber / secondNumber;
                }
                else
                {
                    result.setText("ERROR");
                    return;
                }
                break;
            }
        }

        if (calculation == Math.floor(calculation)) {
            result.setText(String.valueOf((int)calculation));
        }
        else {
            result.setText(String.format("%.2f", calculation));
        }

        isCalculationDone = true;
        calculation = 0;
        firstNumber = 0;
        secondNumber = 0;
    }
}