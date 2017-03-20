package com.example.desktop_mobile.bluetoothmessenger;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Set;

public class CreateNewActivity extends AppCompatActivity {

    private TableLayout tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        Intent intent = getIntent();

        tbl = (TableLayout) this.findViewById(R.id.tblConnections);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
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

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    // dummy for testing some stuff
    public void other() {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener(){
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
}
