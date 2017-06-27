package com.example.thijs.ledapp.Business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.thijs.ledapp.Config;
import com.example.thijs.ledapp.Domain.LedStrip;
import com.example.thijs.ledapp.Presentation.MainActivity;
import com.example.thijs.ledapp.R;

import java.util.ArrayList;
import java.util.List;

public class StripAdapter extends ArrayAdapter<LedStrip>{

    EspRequest.RequestListener listener;

    public StripAdapter(Context context, List<LedStrip> strips, EspRequest.RequestListener listener) {
        super(context, 0, strips);
        this.listener = listener;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final LedStrip strip = getItem(position);

        final EspRequest request = new EspRequest(getContext(), listener);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem_strip, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.stripitem_title_view);
        Switch swt = (Switch) convertView.findViewById(R.id.stripitem_switch_state);

        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LedStrip mainStrip = MainActivity.getStrips(position);

                if(isChecked) {
                    request.send(mainStrip.data);
                } else {
                    request.send(mainStrip.off);
                }
            }
        });

        title.setText(strip.getTitle());

        return convertView;
    }
}
