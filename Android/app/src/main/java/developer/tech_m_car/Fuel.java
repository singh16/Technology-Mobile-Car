package developer.tech_m_car;

import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;


public class Fuel extends ActionBarActivity {

    LocationManager locmngr;
    LocationListener loclis;
    double lat1,long1,lat2,long2,set,diff;
    EditText f;


    float fuel=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);
        f=(EditText)findViewById(R.id.fuel);
        Intent ointent=getIntent();
        lat1= ointent.getDoubleExtra("LATI",0.0);
        long1=ointent.getDoubleExtra("LONG",0.0);
        /*Toast.makeText(getBaseContext(),
                "Latitude is:" + lat1 + "  Longitude is:" + long1,
                Toast.LENGTH_LONG).show();*/


    }

  public void stop(View view)
    {
        locmngr = (LocationManager) getSystemService(LOCATION_SERVICE);
        loclis = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
                //Toast.makeText(getBaseContext(), provider + "Enabled",
                  //      Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), "Switch on your Location",
                        Toast.LENGTH_LONG).show();
            }


            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub

                lat2 =location.getLatitude();
                long2 =location.getLongitude();
                //tvlati.setText("Lattitude:" + lati);
                //tvlongi.setText("Longitude:" + longi);

                /*Toast.makeText(getBaseContext(),
                        "Latitude1 is:" + lat1 + "  Longitude1 is:" + long1,
                        Toast.LENGTH_LONG).show();*/

            }
        };
       /* HashMap<String, String> user1 = session.getUserDetails();
        float lat_value = Float.parseFloat(user1.get(SessionManager.KEY_LAT));
        float long_value = Float.parseFloat(user1.get(SessionManager.KEY_LONG));*/
        double distance1=distance(lat1, long1, lat2, long2);

        fuel=Float.parseFloat(f.getText().toString());
        double fuel_eff= ((distance1/fuel))/1000;
        Toast.makeText(getBaseContext(),
                "Fuel Effiecency:"+fuel_eff,
                Toast.LENGTH_LONG).show();
        //session.createLoginSession(total_distance, fuel);




    }

    public double distance(double lat1, double lng1, double lat2,
                           double lng2) {

        double earthRadius = (float) 3958.75; // in miles, change to 6371 for
        // kilometer output

        double dLat = (float) Math.toRadians(lat2 - lat1);
        double dLng = (float) Math.toRadians(lng2 - lng1);

        double sindLat = (float) Math.sin(dLat / 2);
        double sindLng = (float) Math.sin(dLng / 2);

        double a = (float) (Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                        * Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2)));

        double c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }

}
