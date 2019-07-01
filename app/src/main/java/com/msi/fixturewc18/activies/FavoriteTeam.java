package com.hussain.fixturewc18.activies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hussain.fixturewc18.R;
import com.hussain.fixturewc18.adapter.SelectTeamAdapter;

import static com.hussain.fixturewc18.constant.constant.FavTeamBangla;
import static com.hussain.fixturewc18.constant.constant.FavTeamEnglish;
import static com.hussain.fixturewc18.constant.constant.LanBangTeamlist;
import static com.hussain.fixturewc18.constant.constant.LanEngTeamlist;


public class FavoriteTeam extends AppCompatActivity {

    SelectTeamAdapter mAdapter;
    SharedPreferences sharedPreferences;
    String FavTeam;
    String[] Teamlist;
    TextView fav_Team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        LanTeamList();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.SelectTeam);
        mAdapter = new SelectTeamAdapter(Teamlist);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void LanTeamList() {
        fav_Team = findViewById(R.id.Select_Fav_Team);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Language = sharedPreferences.getString("Language",null);
        //Log.e("lan",value);
        if (Language=="Bangla"){
            Teamlist = LanBangTeamlist;
            fav_Team.setText(FavTeamBangla);
        }else {
            Teamlist = LanEngTeamlist;
            fav_Team.setText(FavTeamEnglish);
        }
    }

    /*public void onItemClickListener(int position, View view) {
        FavTeam = mAdapter.getselectedItem(view);}*/

    public void MoveToFixture(View view) {
        FavTeam = mAdapter.getselectedItem(view);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FavTeamName", FavTeam);
        editor.commit();
        Intent intent = new Intent(FavoriteTeam.this, MainActivityMatchFixture.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
