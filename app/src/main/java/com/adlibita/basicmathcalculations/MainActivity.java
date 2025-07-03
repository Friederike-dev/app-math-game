package com.adlibita.basicmathcalculations;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button butAddition, butSubstraction, butDivision, butMultiplication, butBack;
    TextView textView;
    ConstraintLayout layout;
    ImageView imageView, imageViewD;
    Intent intent;
    String designSaved;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        int id = item.getItemId();
        if (id == R.id.lightDesign) {
            lightDesign(layout = findViewById(R.id.layoutBackgr));
            Toast.makeText(MainActivity.this, "Light Design", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("design", "light").apply();
            return true;
        } else if (id == R.id.darkDesign) {
            darkDesign(layout = findViewById(R.id.layoutBackgr));
            Toast.makeText(MainActivity.this, "Dark Design", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("design", "dark").apply();
            return true;
        } else if (id == R.id.infoDeveloper) {
            butSubstraction.setVisibility(View.INVISIBLE);
            butAddition.setVisibility(View.INVISIBLE);
            butMultiplication.setVisibility(View.INVISIBLE);
            butDivision.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            butBack.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    public void hideInfo (View view) {
        butSubstraction.setVisibility(View.VISIBLE);
        butAddition.setVisibility(View.VISIBLE);
        butMultiplication.setVisibility(View.VISIBLE);
        butDivision.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        butBack.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);

    }

    public void lightDesign (View view) {
        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        butBack.setBackgroundResource(R.drawable.but_back_white);
        butBack.setTextColor(Color.parseColor("#000000"));
        butDivision.setBackgroundResource(R.drawable.but_blue_grid);
        butDivision.setTextColor(Color.parseColor("#000000"));
        butMultiplication.setBackgroundResource(R.drawable.but_green);
        butMultiplication.setTextColor(Color.parseColor("#000000"));
        butAddition.setBackgroundResource(R.drawable.but_yellow);
        butAddition.setTextColor(Color.parseColor("#000000"));
        butSubstraction.setBackgroundResource(R.drawable.but_pink);
        butSubstraction.setTextColor(Color.parseColor("#000000"));
        textView.setBackgroundResource(R.drawable.but_back_white);
        textView.setTextColor(Color.parseColor("#000000"));
        imageView.setVisibility(View.VISIBLE);
        imageViewD.setVisibility(View.INVISIBLE);

    }

    public void darkDesign (View view) {
        layout.setBackgroundResource(R.drawable.background_dark);
        butBack.setBackgroundResource(R.drawable.but_dark);
        butBack.setTextColor(Color.parseColor("#1C93B6"));
        butDivision.setBackgroundResource(R.drawable.but_dark);
        butDivision.setTextColor(Color.parseColor("#1C93B6"));
        butMultiplication.setBackgroundResource(R.drawable.but_dark);
        butMultiplication.setTextColor(Color.parseColor("#1C93B6"));
        butAddition.setBackgroundResource(R.drawable.but_dark);
        butAddition.setTextColor(Color.parseColor("#1C93B6"));
        butSubstraction.setBackgroundResource(R.drawable.but_dark);
        butSubstraction.setTextColor(Color.parseColor("#1C93B6"));
        textView.setBackgroundResource(R.drawable.but_dark);
        textView.setTextColor(Color.parseColor("#1C93B6"));
        imageView.setVisibility(View.INVISIBLE);
        imageViewD.setVisibility(View.VISIBLE);
    }


    public void setAdditionMode (View view) {
        intent = new Intent (getApplicationContext(), AdditionActivity.class) ;
        startActivity(intent);
    }

    public void setSubstractionMode (View view) {
        intent = new Intent (getApplicationContext(), SubstractionActivity.class) ;
        startActivity(intent);
    }

    public void setMultiplicationMode (View view) {
        intent = new Intent (getApplicationContext(), MultiplicationActivity.class) ;
        startActivity(intent);
    }

    public void setDivisionMode (View view) {
        intent = new Intent (getApplicationContext(), DivisionActivity.class) ;
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butAddition = findViewById(R.id.buttonAddi);
        butDivision = findViewById(R.id.buttonDiv);
        butMultiplication = findViewById(R.id.buttonMulti);
        butSubstraction = findViewById(R.id.buttonSubs);
        butBack = findViewById(R.id.buttonBack);
        textView = findViewById(R.id.textView);
        layout = findViewById(R.id.layoutBackgr);
        imageView = findViewById(R.id.imageView);
        imageViewD = findViewById(R.id.imageViewD);


        butDivision.setY(-1000);
        butDivision.animate().translationYBy(1000).setDuration(2500);

        butAddition.setY(+1000);
        butAddition.animate().translationYBy(-1000).setDuration(2500);

        butMultiplication.setX(+1000);
        butMultiplication.animate().translationXBy(-1000).setDuration(2500);

        butSubstraction.setX(-1000);
        butSubstraction.animate().translationXBy(1000).setDuration(2500);



       // sharedPreferences = this.getSharedPreferences("com.adlibita.basicmathcalculations", Context.MODE_PRIVATE);  // anstelle von :
        sharedPreferences = getApplicationContext().getSharedPreferences("com.adlibita.basicmathcalculations", Context.MODE_PRIVATE);

        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);  // anstelle von:
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //sharedPreferences.edit().putInt("design", 0).apply();  // diese Zeile vielleicht nicht nötig
        designSaved = sharedPreferences.getString("design", "ERROR" );

      //  SharedPreferences.Editor editor = sharedPreferences.edit();
      //  editor.putString("design", "light");
      //  editor.apply();
       // SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putInt("design",0);
        //editor.apply();


        if (designSaved.equals("ERROR")) {
            lightDesign(layout= findViewById(R.id.layoutBackgr));
        }
        else if (designSaved.equals("light")){
            lightDesign(layout= findViewById(R.id.layoutBackgr));
        }
        else {
            darkDesign(layout= findViewById(R.id.layoutBackgr));
        }




    }
}
