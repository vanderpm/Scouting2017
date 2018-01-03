package com.example.vande.scouting2017;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SendDataActivity extends AppCompatActivity {
    //Create Objects-------------------------------------------------------
    Button send;
    String dataPath = "/data/user/0/com.example.vande.scouting2017/files/match.csv";
    File root, curFolder;
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;
    BluetoothAdapter btAdatper = BluetoothAdapter.getDefaultAdapter();

    //---------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
//        dataPath = (EditText) findViewById(R.id.FilePath);
        send = (Button) findViewById(R.id.sendBtooth);


        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendViaBluetooth();
            }
        });
    }


    //exit to application---------------------------------------------------------------------------
    public void exit(View V) {
        btAdatper.disable();
        Toast.makeText(this, "*** Now Bluetooth is off... Thanks. ***", Toast.LENGTH_LONG).show();
        finish();
    }

    //Method for send file via bluetooth------------------------------------------------------------
    public void sendViaBluetooth() {
        if (!dataPath.equals(null)) {
            if (btAdatper == null) {
                Toast.makeText(this, "Device not support bluetooth", Toast.LENGTH_LONG).show();
            } else {
                enableBluetooth();
            }
        } else {
            Toast.makeText(this, "Please select a file.", Toast.LENGTH_LONG).show();
        }
    }

    public void enableBluetooth() {
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);
        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }

    //Override method for sending data via bluetooth availability--------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.setType("*/*");

            for (String file : fileList()) {
                File file1 = new File(file);
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file1));
            }

            PackageManager pm = getPackageManager();
            List<ResolveInfo> list = pm.queryIntentActivities(i, 0);
            if (list.size() > 0) {
                String packageName = null;
                String className = null;
                boolean found = false;

                for (ResolveInfo info : list) {
                    packageName = info.activityInfo.packageName;
                    if (packageName.equals("com.android.bluetooth")) {
                        className = info.activityInfo.name;
                        found = true;
                        break;
                    }
                }
                //CHECK BLUETOOTH available or not------------------------------------------------
                if (!found) {
                    Toast.makeText(this, "Bluetooth not been found", Toast.LENGTH_LONG).show();
                } else {
                    i.setClassName(packageName, className);
                    startActivity(i);
                }
            }
        } else {
            Toast.makeText(this, "Bluetooth is cancelled", Toast.LENGTH_LONG).show();
        }
    }


}
