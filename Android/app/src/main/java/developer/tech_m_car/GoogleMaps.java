package developer.tech_m_car;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class GoogleMaps extends ActionBarActivity {

    EditText s,d;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        s=(EditText)findViewById(R.id.s);
        d=(EditText)findViewById(R.id.d);
        b=(Button)findViewById(R.id.b);
    }
    public void send(View v)
    {
        String s1=s.getText().toString();
        String s2=d.getText().toString();
        String url="https://www.google.co.in/maps/dir/"+s1+",+New+Delhi,+Delhi/"+ s2+ ",+New+Delhi,+Delhi/@28.6127912,77.2616739,14z/data=!4m8!4m7!1m2!1m1!1s0x390cfb08c81f50cf:0x455019574c65ba2f!1m2!1m1!1s0x390ce2127da1cf23";
        Uri gmmIntentUri=Uri.parse(url);
        Intent mapIntent= new Intent(Intent.ACTION_VIEW,gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}
