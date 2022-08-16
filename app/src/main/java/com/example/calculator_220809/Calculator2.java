package com.example.calculator_220809;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator_220809.CalculatorAll.AddClass;
import com.example.calculator_220809.CalculatorAll.DivClass;
import com.example.calculator_220809.CalculatorAll.MulClass;
import com.example.calculator_220809.CalculatorAll.SubClass;

public class Calculator2 extends AppCompatActivity {

    //boolean isFirstInput = true; // 입력 값이 처음 입력되는지 체크
    double num1, num2; // 처음 수와 다음 수 저장
    TextView resultText; // 계산 값 출력
    char operator = '+'; // 연산자 저장
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6,
            button_7, button_8, button_9, button_add, button_sub, button_mul,
            button_div, button_equal, button_clear;
    View.OnClickListener clickListener;

    @Override
    protected void onPause() {
        super.onPause();

        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        restoreState();
    }

    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Calculator2.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", resultText.getText().toString());
        editor.commit();
    }

    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Calculator2.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("name"))) {
            String name = pref.getString("name", "");
            resultText.setText(name);
        }
    }
    /*
    protected void clearMyPrefs() {
        SharedPreferences pref = getSharedPreferences("pref", Calculator2.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);
        setTitle("계산기2");

        resultText = findViewById(R.id.textView);

        AddClass addClass = new AddClass();
        SubClass subClass = new SubClass();
        MulClass mulClass = new MulClass();
        DivClass divClass = new DivClass();

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_add = findViewById(R.id.button_add);
        button_sub = findViewById(R.id.button_sub);
        button_mul = findViewById(R.id.button_mul);
        button_div = findViewById(R.id.button_div);
        button_equal = findViewById(R.id.button_equals);
        button_clear = findViewById(R.id.button_right_empty);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = resultText.getText().toString();

                switch (v.getId()) {
                    case R.id.button_right_empty:
                        resultText.setText("");
                        break;
                    case R.id.button_add: case R.id.button_sub: case R.id.button_mul: case R.id.button_div: // 연산자 버튼 눌렀을 때 operator 지정
                        if(num.equals("")) {
                            Toast.makeText(getApplicationContext(), "숫자를 먼저 입력하세요", Toast.LENGTH_SHORT).show();
                        } else {
                            num1 = Double.parseDouble(num);
                            num = "";
                            resultText.setText(num);
                            if(v.getId() == R.id.button_add)
                                operator = '+';
                            else if(v.getId() == R.id.button_sub)
                                operator = '-';
                            else if(v.getId() == R.id.button_mul)
                                operator = '*';
                            else if(v.getId() == R.id.button_div)
                                operator = '/';
                        }
                        break;
                    case R.id.button_equals:
                        if(num.equals("")) {
                            Toast.makeText(getApplicationContext(), "숫자를 먼저 입력하세요", Toast.LENGTH_SHORT).show();
                        } else {
                            num2 = Double.parseDouble(num);
                            //if를 사용하여 operator 연산 수행
                            if (operator == '+') {
                                resultText.setText(addClass.add(num1, num2));
                            } else if (operator == '-') {
                                resultText.setText(subClass.sub(num1, num2));
                            } else if (operator == '/') {
                                if (num2 == 0) {
                                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다 다시 입력하세요", Toast.LENGTH_SHORT).show();
                                    resultText.setText("");
                                } else {
                                    resultText.setText(divClass.div(num1, num2));
                                }
                            } else if (operator == '*') {
                                resultText.setText(mulClass.mul(num1, num2));
                            }
                        }
                        break;
                    case R.id.button_0: case R.id.button_1: case R.id.button_2: case R.id.button_3:
                    case R.id.button_4: case R.id.button_5: case R.id.button_6: case R.id.button_7:
                    case R.id.button_8: case R.id.button_9:
                        if(v.getId() == R.id.button_0) {
                            num += "0";
                        } else if (v.getId() == R.id.button_1) {
                            num += "1";
                        } else if (v.getId() == R.id.button_2) {
                            num += "2";
                        } else if (v.getId() == R.id.button_3) {
                            num += "3";
                        } else if (v.getId() == R.id.button_4) {
                            num += "4";
                        } else if (v.getId() == R.id.button_5) {
                            num += "5";
                        } else if (v.getId() == R.id.button_6) {
                            num += "6";
                        } else if (v.getId() == R.id.button_7) {
                            num += "7";
                        } else if (v.getId() == R.id.button_8) {
                            num += "8";
                        } else if (v.getId() == R.id.button_9) {
                            num += "9";
                        }
                        resultText.setText(num);
                        break;
                }
            }
        };

        button_0.setOnClickListener(clickListener);
        button_1.setOnClickListener(clickListener);
        button_2.setOnClickListener(clickListener);
        button_3.setOnClickListener(clickListener);
        button_4.setOnClickListener(clickListener);
        button_5.setOnClickListener(clickListener);
        button_6.setOnClickListener(clickListener);
        button_7.setOnClickListener(clickListener);
        button_8.setOnClickListener(clickListener);
        button_9.setOnClickListener(clickListener);
        button_add.setOnClickListener(clickListener);
        button_sub.setOnClickListener(clickListener);
        button_mul.setOnClickListener(clickListener);
        button_div.setOnClickListener(clickListener);
        button_equal.setOnClickListener(clickListener);
        button_clear.setOnClickListener(clickListener);

    }

}