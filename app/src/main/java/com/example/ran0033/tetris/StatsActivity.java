package com.example.ran0033.tetris;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setContentView(R.layout.activity_stats);
        // Displays the HTML string in the UI via a WebView
        ListView lv = (ListView)findViewById(R.id.statsList);
        ArrayList<Entry> res = MenuActivity.dbStats.getStats();

        ListAdapter adapter = new ListAdapter(getBaseContext(),
                R.layout.stats_layout, res);

        lv.setAdapter(adapter);
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entry entry = (Entry) adapterView.getItemAtPosition(i);
                startExchangeActivity(entry);
            }
        });*/
    }

}
