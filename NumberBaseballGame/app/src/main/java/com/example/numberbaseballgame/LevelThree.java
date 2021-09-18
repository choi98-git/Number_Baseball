package com.example.numberbaseballgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LevelThree extends AppCompatActivity {

    EditText firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer;
    TextView resultText;
    Button answerButton, historyButton;
    AlertDialog.Builder dlg;
    int[] number;// 실제 정답
    int[] answer;// 사용자가 입력한 정답
    int strike = 0; //strike 개수
    int ball = 0; // ball 개수
    int level = 0; // 진행할 게임의 자리수
    String correctAnswer = ""; //정답
    String userAnswer = ""; // 유저가 입력한 답
    String history = "";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int level_number = getIntent().getIntExtra("level", 0);

        if (level_number == 3) {
            level = 3;
            setContentView(R.layout.activity_level_three);

        }

        if (level_number == 4) {
            level = 4;
            setContentView(R.layout.activity_level_four);
            fourthAnswer = (EditText) findViewById(R.id.fourthAnswer);
        }

        if (level_number == 5) {
            level = 5;
            setContentView(R.layout.activity_level_five);
            fourthAnswer = (EditText) findViewById(R.id.fourthAnswer);
            fifthAnswer = (EditText) findViewById(R.id.fifthAnswer);
        }

        connect();
        makeNumber();
        answerResult();
        history();
    }

    private void connect(){
        firstAnswer = (EditText) findViewById(R.id.firstAnswer);
        secondAnswer = (EditText) findViewById(R.id.secondAnswer);
        thirdAnswer = (EditText) findViewById(R.id.thirdAnswer);
        resultText = (TextView) findViewById(R.id.resultText);
        historyButton = (Button) findViewById(R.id.historyButton);
        answerButton = (Button) findViewById(R.id.answerButton);
    }

    // 랜덤한 level 자리수 생성
    private void makeNumber(){
        number = new int[level];

        for(int i=0; i<level; i++){
            number[i] = (int)(Math.random()*9)+1;
            for(int j=0; j<i; j++){    //중복숫자가 나오지 않게 하기위함
                if(number[i] == number[j]){
                    i--;
                    break;
                }
            }
        }
    }

    private void getAnswer(){
        answer[0] = Integer.parseInt(firstAnswer.getText().toString());
        answer[1] = Integer.parseInt(secondAnswer.getText().toString());
        answer[2] = Integer.parseInt(thirdAnswer.getText().toString());

    }

    // 사용자가 입력한 정답을 answer 배열에 저장
    private void answer(){
        answer = new int[level];
        if (level == 3) {
            try {
                getAnswer();
            }catch (Exception e){
                Error();
            }
        }
        if (level == 4){
            try {
                getAnswer();
                answer[3] = Integer.parseInt(fourthAnswer.getText().toString());
            }catch (Exception e){
                Error();
            }
        }
        if (level == 5){
            try {
                getAnswer();
                answer[3] = Integer.parseInt(fourthAnswer.getText().toString());
                answer[4] = Integer.parseInt(fifthAnswer.getText().toString());
            }catch (Exception e){
                Error();
            }
        }
    }

    // number[] 와 answer[] 비교후 결과
    private void answerResult(){

        answerButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
            @Override
            public void onClick(View v) {
                answer();
                count ++;
                for(int i=0; i<level; i++){
                    for(int j=0; j<level; j++){
                        if(number[i] == answer[j]){
                            if(i == j){
                                strike++;
                            }else{
                                ball++;
                            }
                        }
                    }
                }

                userAnswer = "";
                for(int i=0; i<level; i++) {
                    userAnswer += answer[i];
                }

                // 입력한 level 자리수가 모두 정답일 경우
                if (strike == level) {
                    for(int i=0; i<level; i++) {
                        correctAnswer += number[i];
                    }
                    dlg = new AlertDialog.Builder(LevelThree.this);
                    dlg.setIcon(getDrawable(R.drawable.baseball_icon));
                    dlg.setTitle("정답입니다");
                    dlg.setMessage("정답: " + correctAnswer + "\n" + count + "번만에 맞추셨습니다!!");
                    dlg.show();
                    history = "";
                } else {// strike = 0 이고, ball = 0 이면 OUT
                    if (strike == 0 && ball == 0){
                        resultText.setText("\n" + userAnswer + " : " + "OUT");
                    }else {
                        resultText.setText("\n" + userAnswer + " : " + strike + "S " + ball + "B");
                    }
                    history = history + resultText.getText().toString();
                    strike = 0;
                    ball = 0;
                }
                levelAnswerTextClear();
            }
        });
    }
    private void history(){
        historyButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(LevelThree.this);
                dlg.setIcon(getDrawable(R.drawable.baseball_icon));
                dlg.setTitle("기록보기");
                dlg.setMessage(history);
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });
    }
    private void levelAnswerTextClear(){
        if (level == 3){
            AnswerTextClear();
        }
        else if (level == 4){
            AnswerTextClear();
            fourthAnswer.setText("");
        }
        else if (level == 5){
            AnswerTextClear();
            fourthAnswer.setText("");
            fifthAnswer.setText("");
        }
    }

    private void AnswerTextClear(){
        firstAnswer.setText("");
        secondAnswer.setText("");
        thirdAnswer.setText("");
    }
    // 답을 입력하지 않은 경우 에러 메시지
    private void Error() {
        Toast.makeText(getApplicationContext(),"자리수에 맞는 값을 모두 입력해 주세요", Toast.LENGTH_SHORT).show();
    }
}
