package com.example.thijs.ledapp.Business;

import com.example.thijs.ledapp.Domain.PacketData;

/**
 * Created by thijs on 6/21/17.
 */

public class PacketGenerator {
    public PacketData data;

    public PacketGenerator() {

    }

    public PacketData off(int address){
        data = new PacketData(0,0,0,0,0,address);
        return data;
    }

    public PacketData on(int address) {
        data = new PacketData(127,50,5,0,0,address);
        return data;
    }
}
