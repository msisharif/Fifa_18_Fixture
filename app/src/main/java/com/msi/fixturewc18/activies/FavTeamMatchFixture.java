package com.hussain.fixturewc18.activies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hussain.fixturewc18.R;
import com.hussain.fixturewc18.adapter.DataAdapter;
import com.hussain.fixturewc18.database.MatchFixtureDatabaseHelper;
import com.hussain.fixturewc18.model.Record;

import java.util.List;

public class FavTeamMatchFixture extends AppCompatActivity {
    private RecyclerView recyclerView;
    MatchFixtureDatabaseHelper MatchFixtureDB;
    private DataAdapter adapter;
    private List<Record> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_team_match_fixture);

        MatchFixtureDB = new MatchFixtureDatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String FavTeamName = sharedPreferences.getString("FavTeamName",null);

        if (FavTeamName==null || FavTeamName.isEmpty()){
            Intent intent = new Intent(getApplicationContext(),FavoriteTeam.class);
            finish();
            startActivity(intent);
        }

        else{
            recyclerView = (RecyclerView)findViewById(R.id.Match_Fixture);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            data = MatchFixtureDB.getDataForFavTeam(FavTeamName);
            adapter = new DataAdapter(data);
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
