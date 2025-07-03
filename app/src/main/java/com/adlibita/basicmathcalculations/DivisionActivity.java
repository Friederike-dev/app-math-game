package com.adlibita.basicmathcalculations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DivisionActivity extends AppCompatActivity {

    TextView aufgabe, timer, spielStand, richtigFalsch;
    Button butBack, butPlayAgain, but0, but1, but2, but3;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<>();
    int score = 0;
    int numberOfQuestions = 0;
    GridLayout gridLayout;
    Intent intent;
    String designHier;

    private static final String TAG = "MainActivity";

    private AdView mAdView;


    public void goBack (View view) {

        finish();

    }


    public void playAgain (View view){

        score = 0;
        numberOfQuestions = 0;
        spielStand.setText(score + "/" + numberOfQuestions);
        butPlayAgain.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        richtigFalsch.setText("");

        newQuestionDivision();

        new CountDownTimer(30800, 1000){

            @Override
            public void onTick(long millisUntilFinished) {  // vielleicht final oder private oder so hier
                timer.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                richtigFalsch.setText("Finished!");
                gridLayout.setVisibility(View.INVISIBLE);
                butPlayAgain.setVisibility(View.VISIBLE);
                timer.setText("0s");
            }
        }.start();
    }

    public void choseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            richtigFalsch.setText("correct!");
            score++;
        }else{
            richtigFalsch.setText("wrong answer");
        }
        numberOfQuestions++;
        spielStand.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestionDivision();
    }

    private void newQuestionDivision() {

        int b;
        int c;
        boolean randomGut = false;
        Random randomName = new Random();

        for (int k=0; k<10000 && !randomGut ; k++) {      // oder einfach k>1 && !randomGut - nee , mag er nicht!

            b = randomName.nextInt(51);     // ergibt zufällige Werte von 0-50
            c = randomName.nextInt(51);     // ergibt zufällige Werte von 0-50

            if ( 0 < c*b && c*b < 100){

                aufgabe.setText(c * b + " : " + b); // a : b = c  a=c*b ; Integer.toString scheint unneccessary

                locationOfCorrectAnswer = randomName.nextInt(4); // zufälliger Wert aus 0-3

                answers.clear();

                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(c);
                    } else {
                        int wrongAnswer = randomName.nextInt(101);

                        while (wrongAnswer == c) {
                            wrongAnswer = randomName.nextInt(101);
                        }
                    /*while (wrongAnswer == answers()){
                        wrongAnswer = randomName.nextInt(41);       // hier noch schreiben, dass sich alle 4 Antworten unterscheiden sollen - ok so??
                    }*/
                        answers.add(wrongAnswer);
                    }
                }

                but0.setText(Integer.toString(answers.get(0)));
                but1.setText(Integer.toString(answers.get(1)));
                but2.setText(Integer.toString(answers.get(2)));
                but3.setText(Integer.toString(answers.get(3)));

                randomGut = true;

            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_addition);

        getSupportActionBar().hide();

        intent = getIntent();

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        designHier = (sharedPreferences.getString("design", "ERROR"));

        if (designHier.equals("dark")) {
            setContentView(R.layout.activity_addition);
        } else if (designHier.equals("light")) {
            setContentView(R.layout.activity_game_light);
        } else {
            setContentView(R.layout.activity_game_light);
        }

        aufgabe = findViewById(R.id.textViewAufgabe);
        timer = findViewById(R.id.textViewTimer);
        spielStand = findViewById(R.id.spielStand);
        richtigFalsch = findViewById(R.id.textViewRichtigFalsch);
        butBack = findViewById(R.id.buttonBack);
        butPlayAgain = findViewById(R.id.buttonPlayAgain);
        but0 = findViewById(R.id.button0);
        but1 = findViewById(R.id.button1);
        but2 = findViewById(R.id.button2);
        but3 = findViewById(R.id.button3);
        gridLayout = findViewById(R.id.gridLayout);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        playAgain(timer = findViewById(R.id.textViewTimer));
    }
}
