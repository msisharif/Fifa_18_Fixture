package com.hussain.fixturewc18.activies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hussain.fixturewc18.database.MatchFixtureDatabaseHelper;
import com.hussain.fixturewc18.R;
import com.hussain.fixturewc18.adapter.DataAdapter;
import com.hussain.fixturewc18.internet_and_update.NetworkAvailableService;
import com.hussain.fixturewc18.model.Api;
import com.hussain.fixturewc18.model.Record;
import com.tapadoo.alerter.Alerter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hussain.fixturewc18.constant.constant.NoInternet;
import static com.hussain.fixturewc18.constant.constant.NoInternetAlerter;
import static com.hussain.fixturewc18.constant.constant.NoInternetAlerterTitle;
import static com.hussain.fixturewc18.internet_and_update.NetworkConnectivity.isConnected;

public class MainActivityMatchFixture extends AppCompatActivity {

    private RecyclerView recyclerView;
    MatchFixtureDatabaseHelper MatchFixtureDB;
    private DataAdapter adapter;
    private List<Record> data;
    Button favTeam, group, poinTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MatchFixtureDB = new MatchFixtureDatabaseHelper(this);
        favTeam = (Button) findViewById(R.id.favTeam);
        group = (Button) findViewById(R.id.group);
        poinTable = (Button) findViewById(R.id.poinTable);

        if (isBangla()) {
            favTeam.setText("পছন্দের দল");
            group.setText("গ্রুপ");
            poinTable.setText("টিম পয়েন্ট");
            NoInternet = "ইন্টারনেট নেই";
            NoInternetAlerter = "ইন্টারনেট";
            NoInternetAlerterTitle = "ইন্টারনেট চালু করুন";
        }

        if (MatchFixtureDB.isEmpty()) {
            if (!isConnected(getApplicationContext())){
                Alerter.create(MainActivityMatchFixture.this)
                        .setTitle(NoInternetAlerterTitle)
                        .setText(NoInternetAlerter)
                        .setBackgroundColorRes(R.color.colorPrimaryDark)
                        .show();
                /*setContentView(R.layout.no_internet);
                TextView nointernet = findViewById(R.id.noInternet);
                nointernet.setText(NoInternet);*/
            }
            loadJSON();
            initViews();
        } else {
            startService(new Intent(this, NetworkAvailableService.class));
            initViews();
        }
    }

    public void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.Match_Fixture);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        data = MatchFixtureDB.getData();
        adapter = new DataAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(48);
    }

    private void loadJSON() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Language = sharedPreferences.getString("Language",null);
        Api.ApiInterface apiInterface = Api.getAllMatchFixtureData().create(Api.ApiInterface.class);
        Call<com.hussain.fixturewc18.model.MatchFixture> call = apiInterface.all(Language);

        call.enqueue(new Callback<com.hussain.fixturewc18.model.MatchFixture>() {
            @Override

            public void onResponse(Call<com.hussain.fixturewc18.model.MatchFixture> call, Response<com.hussain.fixturewc18.model.MatchFixture> response) {
                final com.hussain.fixturewc18.model.MatchFixture matchFixture = response.body();

                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<matchFixture.getRecords().size();i++){
                            boolean rss = MatchFixtureDB.insertData(matchFixture.getRecords().get(i).getTeamA(),matchFixture.getRecords().get(i).getTeamB(),
                                    matchFixture.getRecords().get(i).getMatchPlay(),matchFixture.getRecords().get(i).getGroup(),matchFixture.getRecords().get(i).getVanue(),
                                    matchFixture.getRecords().get(i).getResult(),matchFixture.getRecords().get(i).getWinner());
                        }
                    data = MatchFixtureDB.getData();
                    adapter = new DataAdapter(data);
                    recyclerView.setAdapter(adapter);
                    //recyclerView.scrollToPosition(48);
                    }
                };handler.postDelayed(runnable,100);
            }

            @Override
            public void onFailure(Call<com.hussain.fixturewc18.model.MatchFixture> call, Throwable t) {

            }
        });
    }

    public String DateTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
        return formattedDate;
    }

    public void PointTable(View view) {
    }

    public void Group(View view) {
        Intent intent = new Intent(getApplicationContext(),TeamGroup.class);
        startActivity(intent);
    }

    public void FavTeam(View view) {
        Intent intent = new Intent(getApplicationContext(),FavTeamMatchFixture.class);
        startActivity(intent);
    }

    public final void start() {
        Intent intent = new Intent(getApplicationContext(),MainActivityMatchFixture.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean isBangla(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Language = sharedPreferences.getString("Language",null);
        if (Language.equalsIgnoreCase("Bangla"))
            return true;
        else
            return false;
    }
}
