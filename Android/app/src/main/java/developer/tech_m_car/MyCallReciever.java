package developer.tech_m_car;

/**
 * Created by Jaspreet on 10/7/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.content.Context;

import java.util.Locale;

public class MyCallReciever extends BroadcastReceiver {
    Context context;



    @Override

    public void onReceive(Context myContext, Intent intent) {

        context=myContext;

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            // This code will execute when the phone has an incoming call

            // get the phone number
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            //Toast.makeText(context, "Call from:" +incomingNumber, Toast.LENGTH_LONG).show();
            String sender = getContactName(incomingNumber);
            Toast.makeText(context, "Call from:" +sender, Toast.LENGTH_LONG).show();
            /*Intent intent2open = new Intent(context, MainActivity.class);
            intent2open.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent2open.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            String name = "KEY";
            intent2open.putExtra(name, sender);
            context.startActivity(intent2open);*/


        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)
                || intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            // This code will execute when the call is disconnected
            //Toast.makeText(context, "Detected call hangup event", Toast.LENGTH_LONG).show();

        }

    }
    private String getContactName(String phone){
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        String projection[] = new String[]{ContactsContract.Data.DISPLAY_NAME};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if(cursor.moveToFirst()){
            return cursor.getString(0);
        }else {
            return "unknown number";
        }
    }



}
