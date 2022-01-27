package com.example.trainingapp;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import lombok.SneakyThrows;

public class RetriveJSONTask extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... strings) {
        final String API_KEY = "50ef1767f7736a824dae096f8a51da90";

        JSONObject jsonLatLong = getContextFromUrlJSON(strings[0]);

        return jsonLatLong;
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
    }

    @SneakyThrows
    private static String getContextFromUrl(String urlString) {
        URL url = new URL(urlString);
        URLConnection request = url.openConnection();
        request.setRequestProperty("Content-Type","text/html");


        BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
        return input.readLine();
    }

    @SneakyThrows
    private static JSONObject getContextFromUrlJSON(String urlString) {
        URL url = new URL(urlString);
        URLConnection request = url.openConnection();
        request.setRequestProperty("Content-Type", "application/json; utf-8");

        BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

        return new JSONObject(input.readLine());
    }
}
