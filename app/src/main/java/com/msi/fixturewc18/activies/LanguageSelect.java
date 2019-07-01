package com.hussain.fixturewc18.activies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hussain.fixturewc18.R;

public class LanguageSelect extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);
    }

    public void LanBangla(View view) {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Language", "Bangla");
        editor.commit();
        Intent intent = new Intent(LanguageSelect.this,FavoriteTeam.class);
        finish();
        startActivity(intent);
    }

    public void LanEnglish(View view) {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Language", "English");
        editor.commit();
        Intent intent = new Intent(LanguageSelect.this,FavoriteTeam.class);
        finish();
        startActivity(intent);
    }
}
