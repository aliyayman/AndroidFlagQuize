package com.aliyayman.bayrakquize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewsonuc,textViewyuzde;
    private Button buttonRes;
    private int dogruSayac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewsonuc=findViewById(R.id.textViewsonuc);
        textViewyuzde=findViewById(R.id.textViewyuzde);
        buttonRes=findViewById(R.id.buttonRes);
        
        dogruSayac=getIntent().getIntExtra("dogruSayac",0);
        textViewsonuc.setText(dogruSayac+" DOĞRU "+(5-dogruSayac)+" YANLIŞ");
        textViewyuzde.setText("%"+(dogruSayac*20)+" BAŞARI");

        buttonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,QuizeActivity.class));
                finish();

            }
        });
    }
}