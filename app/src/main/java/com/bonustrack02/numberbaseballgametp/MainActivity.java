package com.bonustrack02.numberbaseballgametp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edit01, edit02, edit03;
    Button btnAnswer;
    TextView resultText, endText;
    ImageView endImage;

    int n1, n2, n3, countStrike, countBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit01 = findViewById(R.id.edit01);
        edit02 = findViewById(R.id.edit02);
        edit03 = findViewById(R.id.edit03);
        btnAnswer = findViewById(R.id.btn_answer);
        resultText = findViewById(R.id.result_text);
        endText = findViewById(R.id.end_text);
        endImage = findViewById(R.id.end_image);

        Random rnd = new Random();
        n1 = rnd.nextInt(9) + 1;
        do {
            n2 = rnd.nextInt(9) + 1;
        } while (n1 == n2);

        do {
            n3 = rnd.nextInt(9) + 1;
        } while (n1 == n3 || n2 == n3);

        edit01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 1) edit02.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edit02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 1) edit03.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            int x1, x2, x3;

            @Override
            public void onClick(View view) {
                try {
                    String s1 = edit01.getText().toString();
                    x1 = Integer.parseInt(s1);
                    String s2 = edit02.getText().toString();
                    x2 = Integer.parseInt(s2);
                    String s3 = edit03.getText().toString();
                    x3 = Integer.parseInt(s3);
                } catch (Exception e) {
                    x1 = 0;
                    x2 = 0;
                    x3 = 0;
                    Toast.makeText(MainActivity.this, "숫자를 제대로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


                if (x1 == n1)
                    countStrike++;
                else if (x1 == n2)
                    countBall++;
                else if (x1 == n3)
                    countBall++;

                if (x2 == n2)
                    countStrike++;
                else if (x2 == n1)
                    countBall++;
                else if (x2 == n3)
                    countBall++;

                if (x3 == n3)
                    countStrike++;
                else if (x3 == n2)
                    countBall++;
                else if (x3 == n1)
                    countBall++;

                resultText.append(x1 + " " + x2 + " " + x3 + "   " + countStrike + "Strike " + countBall + "Ball\n");

                if (countStrike == 3) {
                    endText.setText(x1 + " " + x2 + " " + x3 + " 정답입니다!");
                    endText.setVisibility(View.VISIBLE);
                    endImage.setVisibility(View.VISIBLE);
                }

                countBall = 0;
                countStrike = 0;

                edit01.setText("");
                edit02.setText("");
                edit03.setText("");

                edit01.requestFocus();
            }
        });
    }
}