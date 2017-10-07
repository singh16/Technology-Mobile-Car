package developer.tech_m_car;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class Water extends ActionBarActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    String convert;
    int flag=0;
    UUID uuid;
    BluetoothSocket mmSocket;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    boolean stopWorker;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        tv=(TextView)findViewById(R.id.three);
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
                    //Toast.makeText(getBaseContext(), "device:" + mDevice, Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(getBaseContext(), "sending value", Toast.LENGTH_SHORT).show();
                        if(flag==2)
                        {
                            try {
                                //Toast.makeText(getBaseContext(), "tion", Toast.LENGTH_SHORT).show();
                                String msg="tion";

                                mmOutputStream.write(msg.getBytes());
                                beginListenForData();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if(flag==0)
                        {
                            Toast.makeText(getBaseContext(), "Pair with HC-05", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
        }

    }






    void beginListenForData() {
        final Handler handler = new Handler();
        final byte delimiter = 10;
        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                    try {
                        int bytesAvailable = mmInputStream.available();
                        if (bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for (int i = 0; i < bytesAvailable; i++) {
                                byte b = packetBytes[i];
                                if (b == delimiter) {
                                    final byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        public void run() {
                                            tv.setText(data);
                                        }
                                    });



                                } else {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } catch (IOException ex) {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
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
