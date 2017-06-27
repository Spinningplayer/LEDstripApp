package com.example.thijs.ledapp.Presentation;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.ParcelFormatException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thijs.ledapp.Business.EspRequest;
import com.example.thijs.ledapp.Config;
import com.example.thijs.ledapp.Domain.LedStrip;
import com.example.thijs.ledapp.Domain.PacketData;
import com.example.thijs.ledapp.R;
import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

public class StripActivity  extends AppCompatActivity implements EspRequest.RequestListener{

    LedStrip strip;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = extras.getInt("position");
        strip = MainActivity.getStrips(position);

        final HSLColorPicker colorPicker = (HSLColorPicker) findViewById(R.id.colorPicker);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        final EspRequest req = new EspRequest(getApplicationContext(), StripActivity.this);
        final Button setButton = (Button) findViewById(R.id.strip1_button);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strip.data != null) {
                    Log.i("PACKET", strip.data.toString());
                    req.send(strip.data);
                } else {
                    Toast.makeText(StripActivity.this, getResources().getString(R.string.empty_packet), Toast.LENGTH_SHORT).show();
                }
            }
        });

        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                Log.i("COLOR", String.valueOf(color));

                imageView.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                int A = (color >> 24) & 0xff;
                int R = (color >> 16) & 0xff;
                int G = (color >>  8) & 0xff;
                int B = (color      ) & 0xff;

                strip.data = new PacketData(R, G, B, A, 0, 7);
            }
        });
    }

    @Override
    public void onRequestResult(int state, String message) {
        if(state == 1) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        state = 0;
    }
}
