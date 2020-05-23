package com.cookandroid.numberbaseball;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Play {//숫자야구 로직, 맞출 랜덤수 생성하기.

    public boolean isAnswer=false;
    private Context context;
    Map<Integer,Integer> playerMap=new LinkedHashMap<Integer, Integer>();

    public Play(){

    }

    public Play(Context context){
        this.context=context;
    }

    public String process(int number, int ans){//입력받는 숫자랑 정답.
        int strike=0, ball=0;
        Map<Integer,Integer> answerMap=new LinkedHashMap<Integer, Integer>();
        String result="";
        ans=1234;

        //정답
        answerMap.put(0,(ans/1000));
        answerMap.put(1,(ans%1000)/100);
        answerMap.put(2,(ans%100)/10);
        answerMap.put(3,(ans%10));

        for(int i=0; i<4; i++)
        {
            if(playerMap.get(i)==answerMap.get(i))
                strike++;
        }

        if(strike==4) {//여기 나중에 애니메이션 같은거 넣을 거임.
            isAnswer = true;
            Toast.makeText(context, "정답입니다.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(int i=0; i<4; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(i!=j)
                    {
                        if(playerMap.get(i)==answerMap.get(j))
                            ball++;
                    }
                }
            }
        }

        if(strike==0 && ball==0)
            result="OUT";
        else
            result=strike+"S " + ball+"B";

        return result;
    }

    //숫자인지, 숫자 길이가 4인지 확인하는 함수. -> 맞으면 그 숫자 반환
    public int check_length(String str){

        int num;
        try{
            num=Integer.parseInt(str);
        }catch (NumberFormatException e){
            Toast.makeText(context,"숫자가 아닙니다!!",Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(num<1000||num>9999)
        {
            Toast.makeText(context,"서로 다른 4자리 수를 입력하세요.",Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(!isAllDifferent(num)){
            return -1;
        }

        return num;
    }

    //서로 다른 네수를 확인하는 함수.
    public boolean isAllDifferent(int num){

        playerMap.put(0,num/1000);
        playerMap.put(1,(num%1000)/100);
        playerMap.put(2,(num%100)/10);
        playerMap.put(3,num%10);

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++)
            {
                if(i!=j)
                {
                    if(playerMap.get(i)==playerMap.get(j))
                    {
                        Toast.makeText(context,"서로 다른 네 수를 입력하세요.", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getRandomNumber(){//랜덤수 생성
        int getNum=0;
        while(getNum<1000)//1000이상이 아니면 계속 랜덤 수 생성
        {
            getNum=new Random().nextInt(10000);

            if(!isAllDifferent(getNum))//입력한 숫자가 서로 다른 4자리수가 아니면 다시!
                getNum=-1;
        }

        System.out.println("Random Number is "+getNum);
        return getNum;
    }
}
