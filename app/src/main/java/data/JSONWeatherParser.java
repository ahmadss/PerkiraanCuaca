package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by ahmad on 20/12/2016.
 */
public class JSONWeatherParser {
    public static Weather getWeather(String data){
        Weather weather = new Weather();
        //create JsonObject from data

        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();

            JSONObject coorObj = Utils.getJsonObject("coord",jsonObject);
            place.setLat(Utils.getFloat("lat",coorObj));
            place.setLon(Utils.getFloat("lon",coorObj));

            JSONObject sysObj = Utils.getJsonObject("sys",jsonObject);
            place.setCountry(Utils.getString("country",sysObj));
            place.setLastUpdate(Utils.getInt("dt",jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;

            //Get the weather info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeaterId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description",jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon",jsonWeather));

            JSONObject windObj = Utils.getJsonObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDeg(Utils.getFloat("deg",windObj));

            JSONObject mainObj = Utils.getJsonObject("main", jsonObject);
            weather.currentCondition.setTemperature(Utils.getDouble("temp",mainObj));
            weather.currentCondition.setPressure(Utils.getFloat("pressure",mainObj));
            weather.currentCondition.setHumadity(Utils.getFloat("humidity",mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max",mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_min",mainObj));

            JSONObject cloudObj = Utils.getJsonObject("clouds", jsonObject);
            weather.clouds.setPerecipitation(Utils.getInt("all",cloudObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
//{
//        "coord":{
//        "lon":-0.13,
//        "lat":51.51
//        },
//        "weather":[
//        {
//        "id":721,
//        "main":"Haze",
//        "description":"haze",
//        "icon":"50n"
//        },
//        {
//        "id":701,
//        "main":"Mist",
//        "description":"mist",
//        "icon":"50n"
//        },
//        {
//        "id":300,
//        "main":"Drizzle",
//        "description":"light intensity drizzle",
//        "icon":"09n"
//        }
//        ],
//        "base":"stations",
//        "main":{
//        "temp":5.99,
//        "pressure":1025,
//        "humidity":87,
//        "temp_min":5,
//        "temp_max":8
//        },
//        "visibility":5000,
//        "wind":{
//        "speed":2.1,
//        "deg":10
//        },
//        "clouds":{
//        "all":90
//        },
//        "dt":1482164400,
//        "sys":{
//        "type":1,
//        "id":5091,
//        "message":0.0117,
//        "country":"GB",
//        "sunrise":1482134597,
//        "sunset":1482162783
//        },
//        "id":2643743,
//        "name":"London",
//        "cod":200
//        }
