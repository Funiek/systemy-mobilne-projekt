package com.example.trainingapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

import lombok.SneakyThrows;

public class RetriveIPTask extends AsyncTask<Void, Void, String > {
    @SneakyThrows
    @Override
    protected String doInBackground(Void... voids) {
        String ip = getContextFromUrl("http://checkip.amazonaws.com/");

        return ip;
    }

    @Override
    protected void onPostExecute(String s) {
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
