package com.hussain.fixturewc18.activies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hussain.fixturewc18.R;

public class TeamGroup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        if (isBangla())
            ChangeToBangla();
    }

    public void ChangeToBangla(){
        TextView groupA = findViewById(R.id.groupA);
        TextView groupB = findViewById(R.id.groupB);
        TextView groupC = findViewById(R.id.groupC);
        TextView groupD = findViewById(R.id.groupD);
        TextView groupE = findViewById(R.id.groupE);
        TextView groupF = findViewById(R.id.groupF);
        TextView groupG = findViewById(R.id.groupG);
        TextView groupH = findViewById(R.id.groupH);
        TextView argentina = findViewById(R.id.argentina);
        TextView australia = findViewById(R.id.australia);
        TextView belgium = findViewById(R.id.belgium);
        TextView brazil = findViewById(R.id.brazil);
        TextView colombia = findViewById(R.id.colombia);
        TextView croatia = findViewById(R.id.croatia);
        TextView costaRica = findViewById(R.id.costaRica);
        TextView denmark = findViewById(R.id.denmark);
        TextView egypt = findViewById(R.id.egypt);
        TextView england = findViewById(R.id.england);
        TextView france = findViewById(R.id.france);
        TextView germany = findViewById(R.id.germany);
        TextView iceland = findViewById(R.id.iceland);
        TextView iran = findViewById(R.id.iran);
        TextView japan = findViewById(R.id.japan);
        TextView koreaRepublic = findViewById(R.id.koreaRepublic);
        TextView mexico = findViewById(R.id.mexico);
        TextView morocco = findViewById(R.id.morocco);
        TextView nigeria = findViewById(R.id.nigeria);
        TextView panama = findViewById(R.id.panama);
        TextView portugal = findViewById(R.id.portugal);
        TextView poland = findViewById(R.id.poland);
        TextView peru = findViewById(R.id.peru);
        TextView russia = findViewById(R.id.russia);
        TextView senegal = findViewById(R.id.senegal);
        TextView soudiArabia = findViewById(R.id.soudiArabia);
        TextView serbia = findViewById(R.id.serbia);
        TextView switzerland = findViewById(R.id.switzerland);
        TextView sweden = findViewById(R.id.sweden);
        TextView spain = findViewById(R.id.spain);
        TextView tunisia = findViewById(R.id.tunisia);
        TextView uruguay = findViewById(R.id.uruguay);

        groupA.setText("গ্রুপ এ");
        groupB.setText("গ্রুপ বি");
        groupC.setText("গ্রুপ সি");
        groupD.setText("গ্রুপ ডি");
        groupE.setText("গ্রুপ ই");
        groupF.setText("গ্রুপ এফ");
        groupG.setText("গ্রুপ জি");
        groupH.setText("গ্রুপ এইচ");
        argentina.setText("আর্জেন্টিনা");
        australia.setText("অস্ট্রেলিয়া");
        belgium.setText("বেলজিয়াম");
        brazil.setText("ব্রাজিল");
        colombia.setText("কলোম্বিয়া");
        croatia.setText("ক্রোয়েশিয়া");
        costaRica.setText("কোস্টারিকা");
        denmark.setText("ডেনমার্ক");
        egypt.setText("ইজিপ্ট");
        england.setText("ইংল্যান্ড");
        france.setText("ফ্রান্স");
        germany.setText("জার্মানি");
        iceland.setText("আইল্যান্ড");
        iran.setText("ইরান");
        japan.setText("জাপান");
        koreaRepublic.setText("কোরিয়া রিপাবলিক");
        mexico.setText("মেক্সিকো");
        morocco.setText("মরক্কো");
        nigeria.setText("নাইজেরিয়া");
        panama.setText("পানামা");
        poland.setText("পোল্যান্ড");
        portugal.setText("পর্তুগাল");
        peru.setText("পেরু");
        russia.setText("রাশিয়া");
        senegal.setText("সেনেগাল");
        serbia.setText("সার্বিয়া");
        soudiArabia.setText("সৌদি আরাবিয়া");
        spain.setText("স্পেন");
        sweden.setText("সুইডেন");
        switzerland.setText("সুইজারল্যান্ড");
        tunisia.setText("তুনিসিয়া");
        uruguay.setText("উরুগুয়ে");
    }

    public boolean isBangla(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Language = sharedPreferences.getString("Language",null);
        if (Language == "Bangla")
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
