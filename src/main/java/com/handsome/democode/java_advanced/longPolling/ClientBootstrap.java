package com.handsome.democode.java_advanced.longPolling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andy on 17/7/6.
 */
public class ClientBootstrap {

    public static final String URL = "http://localhost:8080/longpolling";

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            System.out.println("第" + (++i) + "次 longpolling");
            HttpURLConnection connection = null;
            try {
                URL getUrl = new URL(URL);
                connection = (HttpURLConnection) getUrl.openConnection();
                connection.setReadTimeout(50000);//这就是等待时间，设置为50s
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept-Charset", "utf-8");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");

                if (200 == connection.getResponseCode()) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        StringBuilder result = new StringBuilder(256);
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        System.out.println("结果 " + result);

                    } finally {
                        if (reader != null) {
                            reader.close();
                        }
                    }
                }
            } catch (IOException e) {

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }

}

