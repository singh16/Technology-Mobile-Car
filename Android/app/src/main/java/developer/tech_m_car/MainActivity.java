package developer.tech_m_car;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends ActionBarActivity {

    private String memberFieldString;
    LocationManager locmngr;
    LocationListener loclis;
    double lati, longi;
    float sp;
    String Phoneno= "9911474933";
    public TextToSpeech tts;
    SmsManager smsmgr;
    String SMS_SENT = "SMS_SENT";
    String SMS_DELIEVERED = "SMS_DELIEVERED";
    Intent iSent, iDelievered;
    PendingIntent piSent, piDelivered;
    BroadcastReceiver brSent, brDelievered;
    IntentFilter infSent, infDelivered;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                tts.setLanguage(Locale.getDefault());
                tts.setPitch(1.2F);    //1.2F
                tts.setSpeechRate(0.7F);     //1.3F
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
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
                //        Toast.LENGTH_LONG).show();
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

                lati = location.getLatitude();
                longi = location.getLongitude();
                sp = location.getSpeed();
                sp *= (18 / 5);

                /*Toast.makeText(getBaseContext(),
                        "Latitude is:" + lati + "  Longitude is:" + longi,
                        Toast.LENGTH_LONG).show();*/


            }
        };
       if (sp >= 5.00f) {

        smsmgr = SmsManager.getDefault();
        if (smsmgr == null) {
            Toast.makeText(getBaseContext(),
                    "Your Device Does not support SMS", Toast.LENGTH_LONG)
                    .show();
        }
        iSent = new Intent(SMS_SENT);
        iDelievered = new Intent(SMS_DELIEVERED);
        piSent = PendingIntent.getBroadcast(getBaseContext(), 1, iSent, 0);
        piDelivered = PendingIntent.getBroadcast(getBaseContext(), 2,
                iDelievered, 0);
        brSent = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                switch (getResultCode()) {
                    case RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent successfully",
                                Toast.LENGTH_LONG).show();
                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(),
                                "Hardware Failure SMS Cannot Be Sent",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(),
                                "No Service SMS Cannot Be Sent", Toast.LENGTH_LONG)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(),
                                "No Packates  Null PDU SMS Cannot Be Sent",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(),
                                "Radio Off SMS Cannot Be Sent", Toast.LENGTH_LONG)
                                .show();
                        break;

                    default:
                        break;
                }
            }

        };
        brDelievered = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS Devlivered",
                                Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }
            }
        };
        infSent = new IntentFilter(SMS_SENT);
        infDelivered = new IntentFilter(SMS_DELIEVERED);

        smsmgr.sendTextMessage(Phoneno, null, "Hello Sir/Madam, Your car has just crossed the speed limit of "+sp+"km/hr", piSent, piDelivered);
   }

    }
    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("YourActivity", "onNewIntent is called!");

        memberFieldString = intent.getStringExtra("KEY");
        tts.speak("You have a call from"+memberFieldString, TextToSpeech.QUEUE_ADD, null);
        super.onNewIntent(intent);
    } // End of onNewIntent(Intent intent)

    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            String c=intent.getStringExtra("c");
            tts.speak("You have a message" + title + "   " + text, TextToSpeech.QUEUE_ADD, null);


        }
    };
    public void ultrasonic(View v)
    {
        Intent i=new Intent(getBaseContext(),Parking.class);
        startActivity(i);

    }
    public void feedback(View v)
    {
        Intent i=new Intent(getBaseContext(),Feedback.class);
        startActivity(i);

    }
    public void maps(View v)
    {
        Intent i=new Intent(getBaseContext(),GoogleMaps.class);
        startActivity(i);
    }
    public void temperature(View v)
    {
        Intent i=new Intent(getBaseContext(),Temperature.class);
        startActivity(i);
    }
    public void fuel(View v)
    {
        Intent i=new Intent(getBaseContext(),Fuel.class);
        i.putExtra("LATI",lati);
        i.putExtra("LONG", longi);
        startActivity(i);
    }
    public void sidemirror(View v)
    {
        Intent i=new Intent(getBaseContext(),SideMirror.class);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
//        registerReceiver(brSent, infSent);
  //      registerReceiver(brDelievered, infDelivered);
        locmngr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                loclis);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
      //  unregisterReceiver(brSent);
        locmngr.removeUpdates(loclis);
        super.onPause();
    }
    public void location(View v)
    {
        Intent i=new Intent(getBaseContext(),AndroidFoursquare.class);
        i.putExtra("LATI",lati);
        i.putExtra("LONG", longi);
        startActivity(i);

    }
    public void powerwindow(View v)
    {
        Intent i=new Intent(getBaseContext(),PowerWindow.class);
        startActivity(i);
    }
    public void mute(View v)
    {
        Intent i=new Intent(getBaseContext(),Mute.class);
        startActivity(i);
       
    }
    public void water(View v)
    {
        Intent i=new Intent(getBaseContext(),Water.class);
        startActivity(i);

    }
}
