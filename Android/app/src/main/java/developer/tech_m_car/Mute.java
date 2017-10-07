package developer.tech_m_car;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class Mute extends ActionBarActivity {

    ToggleButton sidemirror;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    String convert;
    int flag=0;
    UUID uuid;
    BluetoothSocket mmSocket;
    OutputStream mmOutputStream;
    InputStream mmInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mute);
        // sidemirror=(ToggleButton)findViewById(R.id.sidemirror);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); //create a BluetoothAdapter object
        if (mBluetoothAdapter == null) {
            Log.d("JASPREET", "Device does not support bluetooth.");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent settingsIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(settingsIntent);
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices(); //get all the paired devices in the set of type bluetooth.the set has unique element
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName().equals("HC-05")) {
                    mDevice = device;
                   // Toast.makeText(getBaseContext(), "device:" + mDevice, Toast.LENGTH_SHORT).show();
                    flag=1;

                    uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID

                    try {
                        mmSocket = mDevice.createRfcommSocketToServiceRecord(uuid);
                        mmSocket.connect();
                        if(mmSocket.isConnected())
                        {
                            flag=2;
                            Toast.makeText(getBaseContext(), "You are connected to Bluetooth", Toast.LENGTH_SHORT).show();
                        }
                        mmOutputStream = mmSocket.getOutputStream();
                        mmInputStream = mmSocket.getInputStream();
                       // Toast.makeText(getBaseContext(), "sending value", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
        }


        /*sidemirror.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (sidemirror.getText().equals("ON")) {

                    Toast.makeText(getBaseContext(), "ON", Toast.LENGTH_LONG).show();
                    if(flag==2)
                    {
                        try {
                            Toast.makeText(getBaseContext(), "smf sent", Toast.LENGTH_SHORT).show();
                            String msg="smf";

                            mmOutputStream.write(msg.getBytes());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if(flag==0)
                    {
                        Toast.makeText(getBaseContext(), "Pair with HC-05", Toast.LENGTH_SHORT).show();
                    }

                } else if (sidemirror.getText().equals("OFF")) {

                    Toast.makeText(getBaseContext(), "OFF", Toast.LENGTH_LONG).show();
                    if(flag==2)
                    {
                        try {
                            Toast.makeText(getBaseContext(), "smu sent", Toast.LENGTH_SHORT).show();
                            String msg="smu";

                            mmOutputStream.write(msg.getBytes());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if(flag==0)
                    {
                        Toast.makeText(getBaseContext(), "Pair with HC-05", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });*/
    }
    public void mute(View v)
    {
        if(flag==2)
        {
            try {
                //Toast.makeText(getBaseContext(), "msof sent", Toast.LENGTH_SHORT).show();
                String msg="msof";

                mmOutputStream.write(msg.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(flag==0)
        {
            Toast.makeText(getBaseContext(), "Pair with HC-05", Toast.LENGTH_SHORT).show();
        }
    }

    public void de_mute(View v)
    {
        if(flag==2)
        {
            try {
                //Toast.makeText(getBaseContext(), "mson sent", Toast.LENGTH_SHORT).show();
                String msg="mson";

                mmOutputStream.write(msg.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(flag==0)
        {
            Toast.makeText(getBaseContext(), "Pair with HC-05", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            try {
                if(flag==1) {
                    finish();
                }
                else if(flag==2)
                {

                    mmOutputStream.close();
                    mmInputStream.close();
                    mmSocket.close();
                    finish();
                }
                else
                {
                    finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
