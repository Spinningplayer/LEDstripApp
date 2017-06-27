package com.example.thijs.ledapp.Business;

import com.example.thijs.ledapp.Domain.LedStrip;
import com.example.thijs.ledapp.Domain.PacketData;

import java.util.ArrayList;

/**
 * Created by thijs on 6/23/17.
 */

public class StripGenerator {
    private ArrayList<LedStrip> strips = new ArrayList<>();
    private PacketGenerator generator = new PacketGenerator();

    public ArrayList<LedStrip> generateStrips() {
        for (int i = 7; i < 10; i++) {
            LedStrip strip = new LedStrip("strip " + (i - 6), i, generator.off(i));
            strip.data = strip.on();
            strips.add(strip);
        }
        return strips;
    }
}
