package com.androidlokomedia.perkiraancuaca;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

@TargetApi(Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humadity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;

    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        String timestamp = "1454544000" ; //Example -> in ms
//        Date d = new Date(Long.parseLong(timestamp));
//        Log.d("Datee", "onCreate: "+d);
//
//            DecimalFormat decimalFormat = new DecimalFormat("#.#");
//            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

        // Format the current time.
//        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
//        Date currentTime_1 = new Date(timestamp);
//        String dateString = formatter.format(currentTime_1);


        cityName = (TextView) findViewById(R.id.kotaText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humadity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.presureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);

        renderWeaterData("Jakarta,ID");

    }

    public void renderWeaterData(String kota){
        WeaterTask weaterTask = new WeaterTask();
        weaterTask.execute(new String[]{kota + "&units=metric"});
    }

//    public class ImageDownloadLoader extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            try {
//                URL url = new URL(Utils.ICON_URL+urls[0]+".png");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//                InputStream inputStream = connection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                return bitmap;
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }

//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            try {
//                iconView.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////        http://192.168.56.1/lokomet/foto_berita/small_15spiderman.jpg
//
//        }
//    }

    private class WeaterTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            weather = JSONWeatherParser.getWeather(data);

            weather.iconData = weather.currentCondition.getIcon();
            Log.v("Data: ", weather.currentCondition.getDescription());

//            new ImageDownloadLoader().execute(weather.iconData);

            return weather;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

//            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd");
////            Date currentTime_1 = new Date();
////            String dateString = formatter.format(currentTime_1);
////            DateFormat dateFormat = DateFormat.getDateInstance();
////            myString = DateFormat.getDateInstance().format(myDate);
//            String sunriseDate = formatter.format(new Date(weather.place.getSunrise()));
//            String sunsetDate = formatter.format(new Date(weather.place.getSunset()));
//            String updateDate = formatter.format(new Date(weather.place.getLastUpdate()));

//            DecimalFormat decimalFormat = new DecimalFormat("#.#");
//            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

//            Long timestamp = "1454544000 ; //Example -> in ms
//            getDate(timestamp);
            Date sunsetDate = new Date(Long.parseLong(String.valueOf(weather.place.getSunset())));
            Date sunriseDate = new Date(Long.parseLong(String.valueOf(weather.place.getSunrise())));
            Date lastUpdate = new Date(Long.parseLong(String.valueOf(weather.place.getLastUpdate())));

            cityName.setText(weather.place.getCity()+","+weather.place.getCountry());
            temp.setText(""+weather.currentCondition.getTemperature()+"C");
            humadity.setText("Humidity: "+weather.currentCondition.getHumadity()+"%");
            pressure.setText("Pressure: "+weather.currentCondition.getPressure()+"hPa");
            wind.setText("Wind: "+weather.wind.getSpeed()+"mps");
            sunrise.setText("Sunrise: "+sunriseDate);
            sunset.setText("sunset: "+sunsetDate);
            updated.setText("Last Updated: "+lastUpdate);
            description.setText("Kondisi: "+weather.currentCondition.getCondition()+"("+
                    weather.currentCondition.getDescription()+")");
        }

        private String getDate(long milliSeconds) {
            // Create a DateFormatter object for displaying date in specified
            // format.
            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
            // Create a calendar object that will convert the date and time value in
            // milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis((int) milliSeconds);
            return formatter.format(calendar.getTime());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ubahKotaId) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
