package com.example.calculator_220809;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    boolean isFirstInput = true; // 입력 값이 처음 입력되는지 체크
    double resultNumber = 0; // 계산 값 저장
    char operator = '+'; // 연산자 저장

    final String CLEAR_INPUT_TEXT = "0";
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("계산기1");

        resultText = findViewById(R.id.textView);
    }

    // 사칙연산 값 반환
    public double doubleCal(double result, double lastNum, char operator) {
        if(operator == '+') {
            result += lastNum;
        }
        else if(operator == '-') {
            result -= lastNum;
        }
        else if(operator == '/') {
            result /= lastNum;
        }
        else if(operator == 'x') {
            result *= lastNum;
        }
        return result;
    }

    // +, -, x, / 버튼 클릭
    public void operatorClick(View view) {
        Button getButton = findViewById(view.getId());

        if(view.getId() == R.id.button_equals) {
            if(isFirstInput) {
                resultNumber = 0;
                setClearText(CLEAR_INPUT_TEXT);
                // =를 두 번 이상 누를 때 처리
            }
            else {
                resultNumber = doubleCal(resultNumber, Double.parseDouble(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }
        }
        else {
            if(isFirstInput) {
                operator = getButton.getText().toString().charAt(0);
            }
            else {
                double lastNum = Double.parseDouble(resultText.getText().toString());
                resultNumber = doubleCal(resultNumber, lastNum, operator);
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }
        }
    }

    // 0 ~ 9 버튼 클릭
    public void numButtonClick(View view) {
        Button getButton = findViewById(view.getId());

        if(isFirstInput) {
            resultText.setText(getButton.getText().toString());
            isFirstInput = false;
        }
        else {
            if(resultText.getText().toString().equals("0")) {
                Toast.makeText(getApplicationContext(), "초기 입력값이 0입니다.", Toast.LENGTH_LONG).show();
                setClearText(CLEAR_INPUT_TEXT);
                return;
            }
            resultText.append(getButton.getText().toString());
        }
    }

    // AC, CE 버튼 클릭
    public void buttonClick(View view) {

        switch(view.getId()) {
            case R.id.button_left_empty:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.button_right_empty:
                if(resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0,getResultText.length()-1);
                    resultText.setText(subString);
                }
                else {
                    resultText.setText(CLEAR_INPUT_TEXT);
                    isFirstInput = true;
                }
                break;
        }

    }

    // 입력된 숫자 초기화
    public void setClearText(String clearText) {
        isFirstInput = true;
        resultText.setText(clearText);
    }

}