package com.example.thijs.ledapp.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PacketData implements Parcelable {
    public int R, G, B, A, X, address;

    public PacketData(int R, int G, int B, int A, int X, int address) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.A = A;
        this.X = X;
        this.address = address;
    }

    public void setRGB(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public void setBrightness(int A) {
        this.A = A;
    }

    public void setMode(int X) {
        this.X = X;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public JSONObject getJson() {
        Map<String, Integer> params = new HashMap<>();
        params.put("R", this.R);
        params.put("G", this.G);
        params.put("B", this.B);
        params.put("A", this.A);
        params.put("X", this.X);
        params.put("address", this.address);
        JSONObject postVars = new JSONObject(params);
        return postVars;
    }

    public String toString() {
        String returnString =
                "R: " + this.R +
                ", G: " + this.G +
                ", B: " + this.B +
                ", A: " + this.A +
                ", mode: " + this.X +
                ", address: " + this.address;
        return returnString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.R);
        dest.writeInt(this.G);
        dest.writeInt(this.B);
        dest.writeInt(this.A);
        dest.writeInt(this.X);
        dest.writeInt(this.address);
    }

    protected PacketData(Parcel in) {
        this.R = in.readInt();
        this.G = in.readInt();
        this.B = in.readInt();
        this.A = in.readInt();
        this.X = in.readInt();
        this.address = in.readInt();
    }

    public static final Parcelable.Creator<PacketData> CREATOR = new Parcelable.Creator<PacketData>() {
        @Override
        public PacketData createFromParcel(Parcel source) {
            return new PacketData(source);
        }

        @Override
        public PacketData[] newArray(int size) {
            return new PacketData[size];
        }
    };
}
