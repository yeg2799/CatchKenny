package com.example.catchkennys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {
    TextView ScoreText;
    TextView TimeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imagearray;
    Handler handler;
    Runnable runnable;
    int skor=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScoreText=findViewById(R.id.Skor);
        TimeText=findViewById(R.id.Zaman);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);


        imagearray =new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6, imageView7, imageView8,imageView9};
        HideImages();
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            TimeText.setText("Zaman: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
            TimeText.setText("Zaman Doldu!!");
            handler.removeCallbacks(runnable);
                for (ImageView image:imagearray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Yeniden Başla");
                alert.setMessage("Yeniden Başlamak İster misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Oyun Bitti",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

    }

    public  void SkoruArttir(View view)
    {
        skor++;
        ScoreText.setText("Skor:"+skor);



    }
    public void HideImages()
    {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imagearray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imagearray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }

}