package com.example.thijs.ledapp.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.thijs.ledapp.Business.PacketGenerator;

import java.io.Serializable;

/**
 * Created by thijs on 6/21/17.
 */

public class LedStrip implements Parcelable {
    public String title;
    public int address;
    public PacketData data, on, off;
    private PacketGenerator generator = new PacketGenerator();

    public LedStrip(String title, int address, PacketData data) {
        this.title = title;
        this.address = address;
        this.data = data;

        on = generator.on(address);
        off = generator.off(address);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public PacketData getData() {
        return data;
    }

    public void setData(PacketData data) {
        this.data = data;
    }

    public PacketData off() {
        if(off == null) {
            off = generator.off(address);
        }
        return off;
    }

    public PacketData on() {
        if(on == null) {
            on = generator.on(address);
        }
        return on;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.address);
        dest.writeParcelable(this.data, flags);
    }

    protected LedStrip(Parcel in) {
        this.title = in.readString();
        this.address = in.readInt();
        this.data = in.readParcelable(PacketData.class.getClassLoader());
        this.on = in.readParcelable(PacketData.class.getClassLoader());
        this.off = in.readParcelable(PacketData.class.getClassLoader());
    }

    public static final Parcelable.Creator<LedStrip> CREATOR = new Parcelable.Creator<LedStrip>() {
        @Override
        public LedStrip createFromParcel(Parcel source) {
            return new LedStrip(source);
        }

        @Override
        public LedStrip[] newArray(int size) {
            return new LedStrip[size];
        }
    };
}
