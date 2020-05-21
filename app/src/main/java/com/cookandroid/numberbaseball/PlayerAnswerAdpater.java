package com.cookandroid.numberbaseball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerAnswerAdpater extends BaseAdapter {

    private Context context;
    private ArrayList<PlayerAnswerList> answerList;
    private LayoutInflater inflater;//서브화면 보여줄꺼임.
    private int layout;

    public PlayerAnswerAdpater(Context context, int anslist, ArrayList<PlayerAnswerList> answerList){
        this.context=context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.answerList=answerList;
        this.layout=layout;
    }

    @Override
    public int getCount() {
        return answerList.size();
    }

    @Override
    public Object getItem(int i) {
        return answerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //인플레이션
    @Override
    public View getView(int i, View v, ViewGroup parent) {
        if(v==null)
        {
            v=inflater.inflate(R.layout.player_answer,null);
        }

        TextView PlayerNumber=(TextView)v.findViewById(R.id.playerNumber);
        TextView PlayerResult=(TextView)v.findViewById(R.id.playerResult);
        PlayerNumber.setText(answerList.get(i).getPlayerNumber());
        PlayerResult.setText(answerList.get(i).getPlayerResult());

        return v;
    }
}
