package com.example.numberbaseballgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LevelThree extends AppCompatActivity {

    EditText firstAnswer, secondAnswer, thirdAnswer;
    TextView resultText;
    Button answerButton;
    AlertDialog.Builder dlg;
    int[] number = new int[3]; // 실제 정답
    int[] answer = new int[3]; // 사용자가 입력한 정답
    int strike = 0; //strike 개수
    int ball = 0; // ball 개수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);

        firstAnswer = (EditText) findViewById(R.id.firstAnswer);
        secondAnswer = (EditText) findViewById(R.id.secondAnswer);
        thirdAnswer = (EditText) findViewById(R.id.thirdAnswer);
        resultText = (TextView) findViewById(R.id.resultText);
        answerButton = (Button) findViewById(R.id.answerButton);

        makeNumber();
        answerResult();

    }

    // 랜덤한 3자리수 생성
    private void makeNumber(){
        for(int i=0; i<3; i++){
            number[i] = (int)(Math.random()*9)+1;
            for(int j=0; j<i; j++){    //중복숫자가 나오지 않게 하기위함
                if(number[i] == number[j]){
                    i--;
                    break;
                }
            }
        }
    }


    // 사용자가 입력한 정답을 answer 배열에 저장
    private void answer(){
        answer[0] = Integer.parseInt(firstAnswer.getText().toString());
        answer[1] = Integer.parseInt(secondAnswer.getText().toString());
        answer[2] = Integer.parseInt(thirdAnswer.getText().toString());
    }

    // number[] 와 answer[] 비교후 결과
    private void answerResult(){
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer();
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(number[i] == answer[j]){
                            if(i == j){
                                strike++;
                            }else{
                                ball++;
                            }
                        }
                    }
                }
                if (strike == 3){
                   dlg = new AlertDialog.Builder(LevelThree.this);
                   dlg.setTitle("정답입니다");
                   dlg.setMessage("정답: " + number[0] + number[1] + number[2] );
                   dlg.show();
                }else {
                    resultText.setText(strike + "S" + ball + "B");
                    strike = 0;
                    ball = 0;
                }
            }
        });
    }
}
