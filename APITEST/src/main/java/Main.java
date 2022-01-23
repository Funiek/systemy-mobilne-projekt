import lombok.SneakyThrows;

import java.net.*;
import java.io.*;
import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;


public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        final String API_KEY = "50ef1767f7736a824dae096f8a51da90";

        DecimalFormat df = new DecimalFormat("#.##");

        //get ip
        String ip = getContextFromUrl("http://checkip.amazonaws.com/");
        System.out.println(ip);

        //get latlong
        JSONObject jsonLatLong = getContextFromUrlJSON("http://ip-api.com/json/"+ip+"?fields=lat,lon,query");
        System.out.println(jsonLatLong);

        //get weather data in json
        JSONObject jsonWeatherData = getContextFromUrlJSON("http://api.openweathermap.org/data/2.5/weather?lat="+jsonLatLong.get("lat")+"&lon="+jsonLatLong.get("lon")+"&appid="+API_KEY);
        System.out.println(jsonWeatherData);

        //current
        double currentTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("temp").toString())-273.15) * 100) / 100;
        String currentTemp = Double.toString(currentTempDouble);
        System.out.println("Aktualna temperatura: "+currentTemp);

        //min
        double minTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("temp_min").toString())-273.15) * 100) / 100;
        String minTemp = Double.toString(minTempDouble);
        System.out.println("Minimalna temperatura: "+minTemp);

        //max
        double maxTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("temp_max").toString())-273.15) * 100) / 100;
        String maxTemp = Double.toString(maxTempDouble);
        System.out.println("Maksymalna temperatura: "+maxTemp);

        //ODCZUWALNA
        double feelsLikeTempDouble = (double) Math.round((Double.parseDouble(jsonWeatherData.getJSONObject("main").get("feels_like").toString())-273.15) * 100) / 100;
        String feelsLikeTemp = Double.toString(feelsLikeTempDouble);
        System.out.println("Odczuwalna temperatura: "+feelsLikeTemp);

        //weather
        JSONObject temp = new JSONObject(jsonWeatherData.getJSONArray("weather").get(0).toString());
        String weather = temp.get("main").toString();
        System.out.println("Pogoda: " + weather);
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
