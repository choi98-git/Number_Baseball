package com.example.numberbaseballgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;



public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dlg;
    CheckBox check3, check4, check5;
    Button startBtn, explanationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check3 = (CheckBox)findViewById(R.id.check3);
        check4 = (CheckBox)findViewById(R.id.check4);
        check5 = (CheckBox)findViewById(R.id.check5);
        startBtn = (Button)findViewById(R.id.startGame);
        explanationBtn = (Button) findViewById(R.id.explanationButton);

        checkOne();
        startGame();
        gameExplanation();
    }

    // 중복 체크 x
    private void checkOne(){
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check4.setChecked(false);
                check5.setChecked(false);
            }
        });

        check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check3.setChecked(false);
                check5.setChecked(false);
            }
        });

        check5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check3.setChecked(false);
                check4.setChecked(false);
            }
        });
    }

    // 게임 시작
    private void startGame(){
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check3.isChecked()){
                    Intent intent = new Intent(MainActivity.this, LevelThree.class);
                    intent.putExtra("level", 3);
                    startActivity(intent);
                }
                if (check4.isChecked()){
                    Intent intent = new Intent(MainActivity.this, LevelThree.class);
                    intent.putExtra("level", 4);
                    startActivity(intent);
                }
                if (check5.isChecked()){
                    Intent intent = new Intent(MainActivity.this, LevelThree.class);
                    intent.putExtra("level", 5);
                    startActivity(intent);
                }
            }
        });
    }

    private void gameExplanation(){
        explanationBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setIcon(getDrawable(R.drawable.baseball_icon));
                dlg.setTitle("게임 설명");
                dlg.setMessage(
                        "숫자야구란 사용자가 선택한 3~5개의 임의의 숫자가 무엇인지 맞추는 게임입니다.\n" +
                                "1) 사용자가 선택한 3~5자리 숫자와 위치가 모두 맞아야 성공입니다.\n" +
                                "2) 숫자는 1~9까지 구성되어 있으며, 각 숫자는 한번씩만 사용 가능합니다\n" +
                                "3) 숫자와 자리의 위치가 맞으면 스트라이크(S), 숫자만 맞으면 볼(B) 입니다.\n" +
                                "4) 숫자가 하나도 맞지 않을 경우 아웃(OUT) 으로 표시됩니다.\n" +
                                "\n" +
                                "ex) 임의의 숫자가 123 인 경우\n" +
                                "  - 1. 153 -> 2S \n" +
                                "    사용자가 '153'를 입력했을 경우 위치와 숫자 \n" +
                                "    모두 일치하는게 '1, 3' 2개이기때문에 2S\n" +
                                "  \n" +
                                "  - 2. 152 - > 1S 1B\n" +
                                "     사용자가 '152'를 입력했을 경우 위치와 숫자 \n" +
                                "     모두 일치하는게 '1', 1개  숫자만 일치하는게 \n" +
                                "     '2' 1개 이기때문에 1S 1B");
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });
    }

}