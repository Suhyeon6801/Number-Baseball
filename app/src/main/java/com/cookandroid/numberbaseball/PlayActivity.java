package com.cookandroid.numberbaseball;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

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
                            Toast.makeText(context,"정답!!",Toast.LENGTH_SHORT).show();
                            isAnswer=true;
                        }
                        break;
                }
            }
        });
    }

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

        if(ret!=-1)
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
            playerAnswerList.add(ansList);
        }

        return playerAnswerList;
    }


}
