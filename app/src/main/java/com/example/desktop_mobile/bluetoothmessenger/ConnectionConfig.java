package com.example.desktop_mobile.bluetoothmessenger;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Set;

public class ConnectionConfig extends AppCompatActivity {

    private TableLayout tbl;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_config);
        Intent intent = getIntent();

        tbl = (TableLayout) this.findViewById(R.id.tblConnections);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceMAC = device.getAddress();

                TableRow connectionRow = new TableRow(context);
                connectionRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                TextView tvDeviceName = new TextView(context);
                tvDeviceName.setText(deviceName);
                tvDeviceName.setTextColor(Color.BLUE);
                tvDeviceName.setTextSize(10);
                connectionRow.addView(tvDeviceName);

                TextView tvMAC = new TextView(context);
                tvMAC.setText(deviceMAC);
                tvMAC.setTextColor(Color.BLUE);
                tvMAC.setTextSize(10);
                connectionRow.addView(tvMAC);

                tbl.addView(connectionRow);

            } else {

            }
        }
    };

    public void newTest(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    // dummy for testing some stuff
    // just ignore this
    public void other() {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() {
            public void onServiceConnected(int profile, BluetoothProfile proxy) {

            }

            public void onServiceDisconnected(int profile) {

            }
        };

        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceMAC = device.getAddress();

                TableRow connectionRow = new TableRow(this);
                connectionRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                TextView tvDeviceName = new TextView(this);
                tvDeviceName.setText(deviceName);
                tvDeviceName.setTextColor(Color.BLUE);
                tvDeviceName.setTextSize(10);
                connectionRow.addView(tvDeviceName);

                TextView tvMAC = new TextView(this);
                tvMAC.setText(deviceMAC);
                tvMAC.setTextColor(Color.BLUE);
                tvMAC.setTextSize(10);
                connectionRow.addView(tvMAC);

                tbl.addView(connectionRow);
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ConnectionConfig Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
