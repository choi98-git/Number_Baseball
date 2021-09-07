package com.example.numberbaseballgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;



public class MainActivity extends AppCompatActivity {

    CheckBox check3, check4, check5;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check3 = (CheckBox)findViewById(R.id.check3);
        check4 = (CheckBox)findViewById(R.id.check4);
        check5 = (CheckBox)findViewById(R.id.check5);
        startBtn = (Button)findViewById(R.id.startGame);

        checkOne();
        startGame();
    }

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

    private void startGame(){
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check3.isChecked()){
                    Intent intent = new Intent(MainActivity.this, LevelThree.class);
                    startActivity(intent);
                }
                if (check4.isChecked()){
                }
                if (check5.isChecked()){
                }
            }
        });
    }


}