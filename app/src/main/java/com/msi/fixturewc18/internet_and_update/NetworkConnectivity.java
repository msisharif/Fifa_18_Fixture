package com.hussain.fixturewc18.internet_and_update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.Preference;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hussain.fixturewc18.R;
import com.hussain.fixturewc18.activies.MainActivityMatchFixture;
import com.hussain.fixturewc18.activies.TeamGroup;
import com.hussain.fixturewc18.adapter.DataAdapter;
import com.hussain.fixturewc18.database.MatchFixtureDatabaseHelper;
import com.hussain.fixturewc18.model.Api;
import com.hussain.fixturewc18.model.MatchFixture;
import com.hussain.fixturewc18.model.Record;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hussain on 23-03-2018.
 */

public class NetworkConnectivity extends BroadcastReceiver{

    private RecyclerView recyclerView;
    MatchFixtureDatabaseHelper MatchFixtureDB;
    private DataAdapter adapter;
    private List<Record> data;
    String lan;

    public NetworkConnectivity(String Language) {
        lan = Language;
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo!=null){

                MatchFixtureDB = new MatchFixtureDatabaseHelper(context);

                Api.ApiInterface apiInterface = Api.getAllMatchFixtureData().create(Api.ApiInterface.class);

                Call<MatchFixture> call = apiInterface.all(lan);

                call.enqueue(new Callback<MatchFixture>() {
                    @Override

                    public void onResponse(Call<com.hussain.fixturewc18.model.MatchFixture> call, Response<MatchFixture> response) {
                        final com.hussain.fixturewc18.model.MatchFixture matchFixture = response.body();

                        final Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                for (int i=0; i<matchFixture.getRecords().size();i++){
                                    //Log.e("status", matchFixture.getRecords().get(i).getResult());
                                    boolean rss = MatchFixtureDB.updateData(matchFixture.getRecords().get(i).getId(),matchFixture.getRecords().get(i).getTeamA(),matchFixture.getRecords().get(i).getTeamB(),
                                            matchFixture.getRecords().get(i).getMatchPlay(),matchFixture.getRecords().get(i).getGroup(),matchFixture.getRecords().get(i).getVanue(),
                                            matchFixture.getRecords().get(i).getResult(),matchFixture.getRecords().get(i).getWinner());
                                }
                            }
                        };handler.postDelayed(runnable,100);
                        try {
                            MainActivityMatchFixture mainActivityMatchFixture = new MainActivityMatchFixture();
                            mainActivityMatchFixture.initViews();
                        }catch (Exception e){ }
                    }

                    @Override
                    public void onFailure(Call<com.hussain.fixturewc18.model.MatchFixture> call, Throwable t) {

                    }
                });

            }else
                return;
        }
    }
}
