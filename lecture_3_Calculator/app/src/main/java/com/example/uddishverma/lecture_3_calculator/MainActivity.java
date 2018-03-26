package com.example.uddishverma.lecture_3_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private EditText etVar1;
    private TextView tvResult;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnAdd, btnSub, btnMul, btnDiv, btnEquals;
    int value1, value2, value3;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etVar1 = (EditText) findViewById(R.id.et_var1);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btn0 = (Button) findViewById(R.id.btn_0);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSub = (Button) findViewById(R.id.btn_sub);
        btnMul = (Button) findViewById(R.id.btn_mul);
        btnDiv = (Button) findViewById(R.id.btn_div);
        btnEquals = (Button) findViewById(R.id.btn_equals);
        btnClear = (Button) findViewById(R.id.btn_clear);
        tvResult = (TextView) findViewById(R.id.tv_result);

        try{
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "1");
                    etVar1.setText("1");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "2");
                    etVar1.setText("2");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "3");
                    etVar1.setText("3");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "4");
                    etVar1.setText("4");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "5");
                    etVar1.setText("5");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "6");
                    etVar1.setText("6");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "7");
                    etVar1.setText("7");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "8");
                    etVar1.setText("8");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "9");
                    etVar1.setText("9");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btn0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvResult.setText(tvResult.getText() + "0");
                    etVar1.setText("0");
                    value3 = (value3 * 10) + Integer.parseInt(etVar1.getText().toString());
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etVar1.getText() == null) {
                        etVar1.setText("");
                    } else if (flag == 2) {               //if + is used before
                        value1 = value1 - value3;
                        flag = 1;
                        tvResult.setText(tvResult.getText() + "+");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 3) {               //if + is used before
                        value1 = value1 * value3;
                        flag = 1;
                        tvResult.setText(tvResult.getText() + "+");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 4) {               //if + is used before
                        value1 = value1 / value3;
                        flag = 1;
                        tvResult.setText(tvResult.getText() + "+");
                        etVar1.setText(null);
                        value3 = 0;
                    } else {
                        value1 = Integer.parseInt(tvResult.getText().toString());
                        flag = 1;
                        tvResult.setText(tvResult.getText() + "+");
                        etVar1.setText(null);
                        value3 = 0;
                    }
                }
            });

            btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etVar1.getText() == null) {
                        etVar1.setText("");
                    } else if (flag == 1) {               //if + is used before
                        value1 = value1 + value3;
                        flag = 2;
                        tvResult.setText(tvResult.getText() + "-");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 3) {               //if + is used before
                        value1 = value1 * value3;
                        flag = 2;
                        tvResult.setText(tvResult.getText() + "-");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 4) {               //if + is used before
                        value1 = value1 / value3;
                        flag = 2;
                        tvResult.setText(tvResult.getText() + "-");
                        etVar1.setText(null);
                        value3 = 0;
                    } else {
                        value1 = Integer.parseInt(tvResult.getText().toString());
                        flag = 2;
                        tvResult.setText(tvResult.getText() + "-");
                        etVar1.setText(null);
                        value3 = 0;
                    }
                }
            });

            btnMul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etVar1.getText() == null) {
                        etVar1.setText("");
                    } else if (flag == 1) {               //if + is used before
                        value1 = value1 + value3;
                        flag = 3;
                        tvResult.setText(tvResult.getText() + "x");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 2) {               //if + is used before
                        value1 = value1 - value3;
                        flag = 3;
                        tvResult.setText(tvResult.getText() + "x");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 4) {               //if + is used before
                        value1 = value1 / value3;
                        flag = 3;
                        tvResult.setText(tvResult.getText() + "x");
                        etVar1.setText(null);
                        value3 = 0;
                    } else {
                        value1 = Integer.parseInt(tvResult.getText().toString());
                        flag = 3;
                        tvResult.setText(tvResult.getText() + "x");
                        etVar1.setText(null);
                        value3 = 0;
                    }
                }
            });

            btnDiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etVar1.getText() == null) {
                        etVar1.setText("");
                    } else if (flag == 1) {               //if + is used before
                        value1 = value1 + value3;
                        flag = 4;
                        tvResult.setText(tvResult.getText() + "÷");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 3) {               //if + is used before
                        value1 = value1 * value3;
                        flag = 4;
                        tvResult.setText(tvResult.getText() + "÷");
                        etVar1.setText(null);
                        value3 = 0;
                    } else if (flag == 2) {               //if + is used before
                        value1 = value1 - value3;
                        flag = 4;
                        tvResult.setText(tvResult.getText() + "÷");
                        etVar1.setText(null);
                        value3 = 0;
                    } else {
                        value1 = Integer.parseInt(tvResult.getText().toString());
                        flag = 4;
                        tvResult.setText(tvResult.getText() + "÷");
                        etVar1.setText(null);
                        value3 = 0;
                    }
                }
            });

            btnEquals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value2 = value3;

                    if (flag == 1) {
                        etVar1.setText(String.valueOf(value1 + value2));
                        flag = 0;
                    } else if (flag == 2) {
                        etVar1.setText(String.valueOf(value1 - value2));
                        flag = 0;
                    } else if (flag == 3) {
                        etVar1.setText(String.valueOf(value1 * value2));
                        flag = 0;
                    } else if (flag == 4) {
                        etVar1.setText(String.valueOf(value1 / value2));
                        flag = 0;
                    }
                }
            });

        }catch (ArithmeticException e) {
            Log.e(TAG, "onClick: zero error", e);
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e) {
            Log.e(TAG, "onClick : no. is not int", e);
            Toast.makeText(MainActivity.this, "input must be int", Toast.LENGTH_SHORT).show();
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etVar1.setText(null);
                    tvResult.setText(null);
                }
            });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "oneStart : Start error has been called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume : resume error has been called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause : pause error has been called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop : stop error has been called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : destroy error has been called");
    }
}