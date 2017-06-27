package com.example.thijs.ledapp.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thijs.ledapp.Business.EspRequest;
import com.example.thijs.ledapp.Business.StripAdapter;
import com.example.thijs.ledapp.Business.StripGenerator;
import com.example.thijs.ledapp.Domain.LedStrip;
import com.example.thijs.ledapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EspRequest.RequestListener{

    public static List<LedStrip> ledStrips;
    ListView stripView;
    SwitchCompat masterSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StripGenerator generator = new StripGenerator();

        Toolbar bar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(bar);

        //setup ledstrips en stripview
        ledStrips = generator.generateStrips();
        StripAdapter adapter = new StripAdapter(getApplicationContext(),ledStrips, this);

        stripView = (ListView) findViewById(R.id.main_stripview);
        stripView.setAdapter(adapter);

        stripView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StripActivity.class);
                intent.putExtra("strip", ledStrips.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

//        masterSwitch = (SwitchCompat) findViewById(R.id.menu_switch);
//        masterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
    }

    @Override
    public void onRequestResult(int state, String message) {
        if(state == 1) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_switch);
        item.setActionView(R.layout.bar_switch);
        MenuItem spinner = menu.findItem(R.id.menu_spinner);
        spinner.setActionView(R.layout.bar_spinner);
        return true;
    }

    public static LedStrip getStrips(int position) {
        return ledStrips.get(position);
    }


}
