package com.eseo.twic.forms;

import com.eseo.twic.beans.VilleFrance;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;
import top.jfunc.json.JsonObject;
import top.jfunc.json.impl.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionVille {

    private static final String CHARSET = "UTF-8";
    private final List<JsonObject> responses = new ArrayList<>();
    private static final String URLGET = "http://localhost:8181/villes";
    private static final String URL = "http://localhost:8181/ville";
    private static final String HEADER = "application/x-www-form-urlencoded";
    private static final String APIKEY = "0fb090a1995dc8bc3d3a52311618228b";
    private static final String URLAPI = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private static final DecimalFormat DEC = new DecimalFormat("#0.00");


    public GestionVille() throws IOException {
        URLConnection connection = new URL(URLGET).openConnection();
        connection.setRequestProperty("Accept-CHARSET", CHARSET);
        InputStream responseGet = connection.getInputStream();
        try (
                Scanner scanner = new Scanner(responseGet)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            JSONArray jsonArray = new JSONArray(responseBody);
            for(int i=0; i<jsonArray.size(); i++) {
                JsonObject jObject = jsonArray.getJsonObject(i);
                this.responses.add(jObject);

            }
        }

    }

    public static void put(String query) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(HEADER);
        RequestBody body = RequestBody.create(mediaType,query);
        Request request = new Request.Builder()
                .url(URL)
                .method("PUT", body)
                .addHeader("Content-Type", HEADER)
                .build();
        client.newCall(request).execute();
    }

    public static void delete(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(HEADER);
        RequestBody body = RequestBody.create(mediaType, "Code_commune_INSEE="+id);
        Request request = new Request.Builder()
                .url(URL)
                .method("DELETE", body)
                .addHeader("Content-Type", HEADER)
                .build();
        client.newCall(request).execute();
    }



    public List<VilleFrance> readAll() throws IOException {
        List<VilleFrance> listeVilles=new ArrayList<>();
        for (JsonObject respons : this.responses) {
            ObjectMapper mapper = new ObjectMapper();
            VilleFrance ville = mapper.readValue(respons.toString(), VilleFrance.class);
            listeVilles.add(ville);
        }
        return listeVilles;
    }

    public List<VilleFrance> readPage(int nbPage, int resultPerPage) throws IOException {
        List<VilleFrance> listeVilles = new ArrayList<>();
        int max = nbPage*resultPerPage;
        if(this.responses.size()<max){
            max = this.responses.size();
        }
        for(int i=(nbPage-1)*resultPerPage; i<max; i++){
            ObjectMapper mapper = new ObjectMapper();
            listeVilles.add(mapper.readValue( this.responses.get(i).toString(), VilleFrance.class));
        }
        return listeVilles;
    }

    public VilleFrance getVilleById(String id) throws IOException {
        List<VilleFrance> villes = readAll();
        VilleFrance villeId = new VilleFrance();
        for(VilleFrance ville : villes){
            if(id.equals(ville.getId())){
                villeId = ville;
            }
        }
        return villeId;
    }

    public VilleFrance getVilleByName(String name) throws IOException {
        List<VilleFrance> villes = readAll();
        VilleFrance villeName = new VilleFrance();
        for(VilleFrance ville : villes){
            if(name.equals(ville.getNomCommune())){
                villeName = ville;
            }
        }
        return villeName;
    }



    public static String distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371.0;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return DEC.format(earthRadius * c);
    }

    public static String getTemperature(String latitude, String longitude) throws IOException {
        String temp;
        URLConnection connection = new URL(URLAPI + latitude + "&lon=" + longitude + "&appid=" + APIKEY).openConnection();
        connection.setRequestProperty("Accept-CHARSET", CHARSET);
        InputStream responseGet = connection.getInputStream();
        try (
                Scanner scanner = new Scanner(responseGet)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            JSONObject obj = new JSONObject(responseBody).getJSONObject("main");
            System.out.println("obj : " +obj);
            double temperature = obj.getDouble("temp") - 273.15;
            temp = DEC.format(temperature) + " °C";
            return temp;
        }
    }
}