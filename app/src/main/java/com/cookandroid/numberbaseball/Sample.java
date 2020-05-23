package com.cookandroid.numberbaseball;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Sample extends AppCompatActivity {//게임 성공시
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        image.startAnimation(animation);

        Button againButton = (Button)findViewById(R.id.againButton);
        Button endButton = (Button)findViewById(R.id.endButton);

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(playIntent);
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//새 액티비티가 띄워져 있을 때, 새 액티비티 뿐 아니라 루트 액티비티까지 같이 종료
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        });
    }

}
