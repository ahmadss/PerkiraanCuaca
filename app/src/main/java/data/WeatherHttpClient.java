package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by ahmad on 20/12/2016.
 */
public class WeatherHttpClient {

    public String getWeatherData(String place){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        URL url = null;
        try {
            url = new URL(Utils.BASE_URL + place +"&appid=e22b9393248c947b0345467eec1feca1");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //membaca dari response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
