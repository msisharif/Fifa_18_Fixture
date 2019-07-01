package com.hussain.fixturewc18;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hussain.fixturewc18.activies.LanguageSelect;
import com.hussain.fixturewc18.activies.MainActivityMatchFixture;

public class MainActivity extends AppCompatActivity{
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Language = sharedPreferences.getString("Language",null);
        if (Language!=null){
            Intent intent = new Intent(MainActivity.this, MainActivityMatchFixture.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this, LanguageSelect.class);
            startActivity(intent);
        }
    }
}
