package com.adlibita.basicmathcalculations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdditionActivity extends AppCompatActivity {

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



    public void playAgain (View view){

        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        spielStand.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        butPlayAgain.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        richtigFalsch.setText("");
        newQuestionAddi();

        new CountDownTimer(30800, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
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
            richtigFalsch.setText("wrong answer!");
        }
        numberOfQuestions++;
        spielStand.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestionAddi();
    }

    public void goBack (View view) {

        finish();

    }

    public void newQuestionAddi() {
        Random randomName = new Random();
        int a = randomName.nextInt(501);
        if (a==0) {a = randomName.nextInt(501);}

        int b = randomName.nextInt(501);
        if (b==0) {a = randomName.nextInt(501);}

        aufgabe.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = randomName.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = randomName.nextInt(1001);

                while (wrongAnswer == a + b) {
                    wrongAnswer = randomName.nextInt(1001);
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

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        intent = getIntent();

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        designHier = (sharedPreferences.getString("design", "ERROR"));

        if (designHier.equals("dark")) {
            setContentView(R.layout.activity_addition);
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

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        playAgain(timer = findViewById(R.id.textViewTimer));

    }
}
