package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView zeroP,oneP,twoP,threeP,fourP,fiveP,sixP,sevenP,eightP;
    TextView status,rstBtn;
    boolean intiImg = true;
    static String[] playBoard = {"1","1","1","1","1","1","1","1","1"};
    ArrayList<String> position = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        status = findViewById(R.id.status);
        rstBtn = findViewById(R.id.rstBtn);

        zeroP = findViewById(R.id.zeroP);
        oneP = findViewById(R.id.oneP);
        twoP = findViewById(R.id.twoP);
        threeP = findViewById(R.id.threeP);
        fourP = findViewById(R.id.fourP);
        fiveP = findViewById(R.id.fiveP);
        sixP = findViewById(R.id.sixP);
        sevenP = findViewById(R.id.sevenP);
        eightP = findViewById(R.id.eightP);
        status.setText("X Turn");

        rstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
                status.setText("X Turn");
            }
        });

        zeroP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(zeroP, 0, "0");
            }
        });

        oneP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(oneP, 1, "1");
            }
        });

        twoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(twoP, 2, "2");
            }
        });

        threeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(threeP, 3, "3");
            }
        });

        fourP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(fourP, 4, "4");
            }
        });

        fiveP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(fiveP, 5, "5");
            }
        });

        sixP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(sixP, 6, "6");
            }
        });

        sevenP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(sevenP, 7, "7");
            }
        });

        eightP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCrossNCircle(eightP, 8, "8");
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public String winingCon(){
        for (int a = 0; a < 8; a++) {
            String condition = null;
            switch (a) {
                case 0:
                    condition = playBoard[0] + playBoard[1] + playBoard[2];
                    break;
                case 1:
                    condition = playBoard[3] + playBoard[4] + playBoard[5];
                    break;
                case 2:
                    condition = playBoard[6] + playBoard[7] + playBoard[8];
                    break;
                case 3:
                    condition = playBoard[0] + playBoard[3] + playBoard[6];
                    break;
                case 4:
                    condition = playBoard[1] + playBoard[4] + playBoard[7];
                    break;
                case 5:
                    condition = playBoard[2] + playBoard[5] + playBoard[8];
                    break;
                case 6:
                    condition = playBoard[0] + playBoard[4] + playBoard[8];
                    break;
                case 7:
                    condition = playBoard[2] + playBoard[4] + playBoard[6];
                    break;
            }
            //For X winner
            if (condition.equals("XXX")) {
                return "X";
            }// For O winner
            else if (condition.equals("OOO")) {
                return "O";
            }
        }
        if(position.size()==9){
            return  "draw";
        }
        return null;
    }
    public void status(){
        if(winingCon() == "X"){
            alert("'X' Won the Game");
        }else if(winingCon() == "O"){
            alert("'O' Won the Game");
        }else if(winingCon() == "draw"){
            alert("Draw the Game");
        }
    }
    public  void restart(){
        zeroP.setImageResource(0);
        oneP.setImageResource(0);
        twoP.setImageResource(0);
        threeP.setImageResource(0);
        fourP.setImageResource(0);
        fiveP.setImageResource(0);
        sixP.setImageResource(0);
        sevenP.setImageResource(0);
        eightP.setImageResource(0);
        position.clear();
        playBoard = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1"};
        intiImg = true;
    }
    public void alert(String Win){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog);

        View titleView = getLayoutInflater().inflate(R.layout.alert_title, null);
        TextView title = titleView.findViewById(R.id.titleDialogBox);
        alertDialog.setCustomTitle(titleView);
        View holder = View.inflate(MainActivity.this, R.layout.alert_body, null);
        TextView buttonAdd = holder.findViewById(R.id.btn_yes);
        TextView bodyAlert = holder.findViewById(R.id.bodyDialogBox);
        bodyAlert.setText(Html.fromHtml("<font color='#00FF00'>"+Win+"</font>"));
        alertDialog.setView(holder);

        AlertDialog dialog = alertDialog.create();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
                dialog.dismiss();
                status.setText("X Turn");
            }
        });
        dialog.show();
    }
    public void setCrossNCircle(ImageView imageView,int boardPosition, String positionString){
        if(position.contains(positionString)){
            status.setText(Html.fromHtml("<font color='#FF0000'>Already Selected</font>"));
        }else{
            position.add(positionString);
            if(intiImg){
                imageView.setImageResource(R.drawable.cross);
                intiImg = false;
                playBoard[boardPosition] = "X";
                status.setText("O Turn");
                status();
            }else{
                imageView.setImageResource(R.drawable.circle);
                intiImg = true;
                playBoard[boardPosition] = "O";
                status.setText("X Turn");
                status();
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
