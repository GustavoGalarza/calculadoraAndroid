package com.example.calculadoramark2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2;
    String num1, num2, operator;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        num1 = "";
        num2 = "";
        operator = "";
        result = 0.0;
        setNumberButtonListeners();
        setOperatorButtonListeners();
        setEqualButtonListener();

        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
            }
        });
    }

    public void clearScreen() {
        num1 = "";
        num2 = "";
        operator = "";
        result = 0.0;
        textView1.setText("");
        textView2.setText("");
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = ((Button) v).getText().toString();
                    if (operator.isEmpty()) {
                        num1 += buttonText;
                        textView1.setText(num1);
                    } else {
                        num2 += buttonText;
                        textView1.append(buttonText);  // Mostrar en textView1
                    }
                }
            });
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = new int[]{R.id.buttonSuma, R.id.buttonResta, R.id.buttonMultiplicacion, R.id.buttonDivision};

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = ((Button) v).getText().toString();
                    textView1.append(" " + operator + " ");  // Mostrar en textView1 con espacio
                }
            });
        }
    }

    private void setEqualButtonListener() {
        Button equalButton = findViewById(R.id.buttonEqual);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1.isEmpty() && !num2.isEmpty()) {
                    double number1 = Double.parseDouble(num1);
                    double number2 = Double.parseDouble(num2);

                    switch (operator) {
                        case "+":
                            result = number1 + number2;
                            break;
                        case "-":
                            result = number1 - number2;
                            break;
                        case "*":
                            result = number1 * number2;
                            break;
                        case "/":
                            if (number2 != 0) {
                                result = number1 / number2;
                            } else {
                                result = 0.0;
                            }
                            break;
                    }
                    textView2.setText(String.valueOf(result));  
                    num1 = String.valueOf(result);
                    num2 = "";
                    operator = "";
                }
            }
        });
    }
}

