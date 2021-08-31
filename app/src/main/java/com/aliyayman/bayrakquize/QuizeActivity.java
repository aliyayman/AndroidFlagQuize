package com.aliyayman.bayrakquize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizeActivity extends AppCompatActivity {
    private TextView textViewdogru,textViewyanlis,textViewsoru;
    private ImageView imageViewbayrak;
    private Button buttonA,buttonB,buttonC,buttonD;
    private ArrayList<Bayraklar> sorularList;
    private ArrayList<Bayraklar> yanlisListe;
    private Bayraklar dogruSoru;
    private  Veritabani vt;
    private int soruSayac=0;
    private int yanlisSayac=0;
    private int dogruSayac=0;
    private HashSet<Bayraklar> seceneklerKaristirmaList= new HashSet<>();
    private ArrayList<Bayraklar> seceneklerList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize);
        textViewdogru=findViewById(R.id.textViewdogru);
        textViewsoru=findViewById(R.id.textViewsoru);
        textViewyanlis=findViewById(R.id.textViewyanlis);
        imageViewbayrak=findViewById(R.id.imageViewbayrak);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.buttonB);
        buttonC=findViewById(R.id.buttonC);
        buttonD=findViewById(R.id.buttonD);

        vt=new Veritabani(this);
        sorularList= new Bayraklardao().rastgele5Getir(vt);

        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dogruKontrol(buttonA);
              sayacKontrol();

            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                sayacKontrol();

            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonC);
                sayacKontrol();

            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonD);
                sayacKontrol();

            }
        });



    }
    public void soruYukle(){
        textViewsoru.setText((soruSayac+1)+". SORU");
        textViewdogru.setText("Doğru:"+dogruSayac);
        textViewyanlis.setText("Yanlış:"+yanlisSayac);


        dogruSoru=sorularList.get(soruSayac);
        yanlisListe=new Bayraklardao().rastgele3YanlisGetir(vt,dogruSoru.getBayrak_id());

        imageViewbayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));
        seceneklerKaristirmaList.clear();
        seceneklerKaristirmaList.add(dogruSoru);
        seceneklerKaristirmaList.add(yanlisListe.get(0));
        seceneklerKaristirmaList.add(yanlisListe.get(1));
        seceneklerKaristirmaList.add(yanlisListe.get(2));

        seceneklerList.clear();
        for(Bayraklar b: seceneklerKaristirmaList){
            seceneklerList.add(b);
        }
        buttonA.setText(seceneklerList.get(0).getBayrak_ad());
        buttonB.setText(seceneklerList.get(1).getBayrak_ad());
        buttonC.setText(seceneklerList.get(2).getBayrak_ad());
        buttonD.setText(seceneklerList.get(3).getBayrak_ad());





    }
    public void dogruKontrol(Button button){
        String butonYazi=button.getText().toString();
        String dogruCevap=dogruSoru.getBayrak_ad();
        if(butonYazi.equals(dogruCevap)){
            dogruSayac++;
        }
        else{

            Toast.makeText(getApplicationContext(),"CEVAP:"+dogruCevap,Toast.LENGTH_SHORT).show();
            yanlisSayac++;
        }



    }
    public void sayacKontrol(){
        soruSayac++;
        if(soruSayac!=5){
            soruYukle();

        }
        else{

        Intent intent= new Intent(QuizeActivity.this,ResultActivity.class);
        intent.putExtra("dogruSayac",dogruSayac);
        startActivity(intent);
        finish();



        }
    }
}