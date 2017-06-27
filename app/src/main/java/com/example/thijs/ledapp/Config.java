package com.example.thijs.ledapp;

import com.example.thijs.ledapp.Domain.PacketData;

/**
 * Created by thijs on 6/20/17.
 */

public class Config {
    public static String ADDRESS = "http://192.168.43.57:8080";
    public static PacketData STRIP_ONE_DATA = new PacketData(0,0,0,0,0,7);
    public static boolean STRIP_ONE_TOGGLE = false;
}
