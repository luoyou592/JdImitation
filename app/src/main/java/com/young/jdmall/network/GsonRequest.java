package com.young.jdmall.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by Zhipe on 2017/7/11.
 */

public class GsonRequest<T> extends JsonRequest<T> {

    private Gson mGson;
    private Class mClazz;
    private onGsonRequestListener mOnGsonRequestListener;

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        //   mGson = new Gson();
    }

    public GsonRequest(Class<T> clazz, String url, onGsonRequestListener l) {
        this(Method.GET, url, null, null, null);
        mGson = new Gson();
        mClazz = clazz;
        mOnGsonRequestListener = l;
    }

    @Override
    protected void deliverResponse(T response) {
        // super.deliverResponse(response);
        System.out.println("--------=======================");
        mOnGsonRequestListener.completeRequest(response, true);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        mOnGsonRequestListener.completeRequest(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String result = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T json = (T) mGson.fromJson(result, mClazz);
            System.out.println(json + "========");
            return Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

 /*   public void setOnGsonRequestListener(onGsonRequestListener l) {
        mOnGsonRequestListener = l;
    }*/

    public interface onGsonRequestListener<T> {
        void completeRequest(T response, boolean result);

        void completeRequest(VolleyError error);
    }
}

