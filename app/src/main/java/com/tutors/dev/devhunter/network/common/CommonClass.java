package com.tutors.dev.devhunter.network.common;

import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tutors.dev.devhunter.network.json.DateDeserializer;
import com.tutors.dev.devhunter.network.json.DateSerializer;
import com.tutors.dev.devhunter.network.json.UriDeserializer;
import com.tutors.dev.devhunter.network.json.UriSerializer;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shs on 2015-04-10.
 */
public class CommonClass {

    public String toJson()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(this);
    }

    public static String toJson(Object T)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(T);
    }

    public static String toArrayJson(ArrayList<?> T)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(T);
    }

    public static Object fromArrayJson(Type type, String json)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();

        return gson.fromJson(json, type);
    }

    public static Object fromJSon(Class T, String json)
    {
        try {
            Gson gson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(Uri.class, new UriDeserializer())
                    .registerTypeAdapter(Date.class, new DateDeserializer())
                    .create();
            return gson.fromJson(json, T);
        } catch (Exception e) {
            return null;
        }
    }

    public static Bundle toBundle(String json)
    {
        Bundle savedBundle = new Bundle();
        savedBundle.putString("toBundle",json);
        return savedBundle;
    }

}
