package com.example.thijs.ledapp.Business;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.thijs.ledapp.Config;
import com.example.thijs.ledapp.Domain.PacketData;
import com.example.thijs.ledapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EspRequest {

    final int SUCCESS_STATE = 0;
    final int FAILED_STATE = 1;
    final String OBJECT_TAG = "msg";
    final String OBJECT_VALUE = "post success";

    RequestListener listener;
    Context context;

    public EspRequest(Context context, RequestListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void send(PacketData data) {
        try {
            JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Config.ADDRESS,
                data.getJson(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            if (response.getString(OBJECT_TAG) == OBJECT_VALUE) {
//                                listener.onRequestResult(SUCCESS_STATE, response.getString(OBJECT_TAG));
//                            } else {
//                                listener.onRequestResult(FAILED_STATE, context.getString(R.string.response_failed));
//                            }
                            listener.onRequestResult(SUCCESS_STATE, response.getString(OBJECT_TAG));
                        } catch(JSONException e) {
                            System.err.println(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error);
                        listener.onRequestResult(FAILED_STATE, context.getString(R.string.request_unavailable));
                    }
                }
            );
            VolleyRequestQueue.getInstance(context).addToRequestQueue(request);
        } catch(Exception e) {
            listener.onRequestResult(FAILED_STATE, context.getString(R.string.request_failed));
            System.err.println(e);
        }
    }

    public interface RequestListener{
        public void onRequestResult(int state, String message);
    }
}
