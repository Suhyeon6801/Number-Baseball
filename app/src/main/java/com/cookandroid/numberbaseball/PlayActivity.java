package com.cookandroid.numberbaseball;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

   // private CustomDialog dialog;

    private int radomNum=0;
    private int cnt=0;
    private PlayerAnswerAdpater playerAnswerAdpater;
    private ArrayList<PlayerAnswerList> playerAnswerList = new ArrayList<PlayerAnswerList>();
    public static boolean isAnswer=false;
    public static Context context;

    EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        context=this;

        radomNum=makeRandomNum(context);
        answerEditText=(EditText)findViewById(R.id.YourAnswer);
        ListView playerList = (ListView)findViewById(R.id.playerListView);
        playerAnswerAdpater=new PlayerAnswerAdpater(this, R.layout.player_answer, playerAnswerList);
        playerList.setAdapter(playerAnswerAdpater);

        ((Button) findViewById(R.id.button0)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button1)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button2)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button3)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button4)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button5)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button6)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button7)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button8)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button9)).setOnClickListener(on_Click);


        Button gameButton=(Button)findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.gameButton:
                        cnt++;
                        playerAnswerList=check_number();
                        answerEditText.setText("");
                        playerAnswerAdpater.notifyDataSetChanged();

                        if(isAnswer){
                            //Dialog();
                            //mSuccessDialog = new SuccessDialog(this);
                            //Toast.makeText(context,"정답!! 입력 횟수는 "+cnt ,Toast.LENGTH_SHORT).show();
                            isAnswer=true;
                        }
                        break;
                }

                if(isAnswer) {
                    //startActivity(new Intent(PlayActivity.this, Sample.class));
                    Intent retintent = new Intent(PlayActivity.this, SuccessActivity.class);
                    String temp = String.valueOf(cnt);
                    retintent.putExtra("COUNT",temp);
                    startActivity(retintent);
                    isAnswer=false;
                }
            }
        });

        Button deleteButton = (Button)findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=answerEditText.getText().toString();
                str=str.substring(0,str.length()-1);
                answerEditText.setText(str);
            }
        });
    }

    private View.OnClickListener on_Click = new View.OnClickListener() {
        public void onClick(View v) {
            String id = null;
            String str = "";

            switch (v.getId()) {
                case R.id.button0:
                    id = "0";
                    break;
                case R.id.button1:
                    id = "1";
                    break;
                case R.id.button2:
                    id = "2";
                    break;
                case R.id.button3:
                    id = "3";
                    break;
                case R.id.button4:
                    id = "4";
                    break;
                case R.id.button5:
                    id = "5";
                    break;
                case R.id.button6:
                    id = "6";
                    break;
                case R.id.button7:
                    id = "7";
                    break;
                case R.id.button8:
                    id = "8";
                    break;
                case R.id.button9:
                    id = "9";
                    break;
            }

            str=answerEditText.getText()+id;
            answerEditText.setText(str);
        }
    };


    private int makeRandomNum(Context context)
    {
        Play p=new Play(context);
        int getNum=p.getRandomNumber();
        return getNum;
    }

    private ArrayList<PlayerAnswerList> check_number(){
        Play p=new Play(getApplicationContext());
        String p_answer = (String) answerEditText.getText().toString();
        int ret = p.check_length(p_answer);

        if(ret==-1)//숫자가 아닌 수를 입력
        {
            AlertDialog.Builder builer1 = new AlertDialog.Builder(PlayActivity.this, R.style.MyCustomDialogStyle);
            builer1.setIcon(R.drawable.baseball);
            builer1.setMessage("숫자가 아닙니다!");
            builer1.setPositiveButton("OK",null);
            builer1.create().show();
        }
        else if(ret==-2)//입력길이가 4개가 아닐 때
        {
            AlertDialog.Builder builer2 = new AlertDialog.Builder(PlayActivity.this, R.style.MyCustomDialogStyle);
            builer2.setIcon(R.drawable.baseball);
            builer2.setMessage("4자리 수를 입력하지 않았습니다!");
            builer2.setPositiveButton("OK",null);
            builer2.create().show();
        }
        else if(ret==-3)//서로다른 4 숫자를 입력 아니한 경우
        {
            AlertDialog.Builder builer3 = new AlertDialog.Builder(PlayActivity.this,R.style.MyCustomDialogStyle);
            builer3.setIcon(R.drawable.baseball);
            builer3.setMessage("서로 다른 4자리 수를 입력하지 않았습니다!");
            builer3.setPositiveButton("OK",null);
            builer3.create().show();
        }
        else
        {
            String result=p.process(ret,radomNum);
            System.out.println(result);
            PlayerAnswerList ansList = new PlayerAnswerList();
            ansList.setPlayerNumber(p_answer);

            if(!result.contains("4S")){
                ansList.setPlayerResult(result);
                Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
            }
            else{
                ansList.setPlayerResult("정답");
                isAnswer=true;
            }
            playerAnswerList.add(0,ansList);//입력한게 맨 위로 가자!
        }

        return playerAnswerList;
    }


}
