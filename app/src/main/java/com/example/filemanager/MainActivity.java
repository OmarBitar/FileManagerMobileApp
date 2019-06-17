package com.example.filemanager;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        final Button b1 = findViewById(R.id.b1);
        final Button b2 = findViewById(R.id.b2);
        final Button b3 = findViewById(R.id.b3);
        final Button b4 = findViewById(R.id.b4);
        final Button b5 = findViewById(R.id.b5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setText("pressed");
            }
        });

        final ListView listView = findViewById(R.id.listView);
        // Array of strings... By default, ArrayAdapter creates a view for each array item by calling toString()
        // on each item and placing the contents in a TextView.
        String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
                "WebOS", "Ubuntu", "Windows7", "Max OS X"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.item, mobileArray);

        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void getPermissions() {
        String TAG = "Permissions Status";
        // loop permissions to check if permissions are granted.
        for (int requestCode = 0; requestCode < PERMISSIONS.length; requestCode++) {
            Toast.makeText(this, "index #:" + requestCode + "  PackageManager: " + PackageManager.PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(this, PERMISSIONS[requestCode]) != PackageManager.PERMISSION_GRANTED) {
                // returns true if the user has previously denied the request,
                // and returns false if a user has denied a permission and selected the Don't ask again.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSIONS[requestCode])) {

                    if (requestCode + 1 == PERMISSIONS.length) {
                        //call alert dialog
                        new permissionDialogList(this, PERMISSIONS, null);
                        //new permissionDialogList(this, PERMISSIONS, this.getPermissions());
                    } else {
                        Log.d(TAG, PERMISSIONS[requestCode] + " ==> Permission is not granted");
                        Toast.makeText(this, "Permission #:" + requestCode + "  is not granted, will ask again later", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this, new String[]{PERMISSIONS[requestCode]}, requestCode);
                    Log.d(TAG, PERMISSIONS[requestCode] + " ==> Permission granted!");
                    Toast.makeText(this, "Permission #:" + requestCode + "  is granted!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permission has already been granted
                Log.d(TAG, PERMISSIONS[requestCode] + " ==> Permission granted!");
                Toast.makeText(this, "index #:" + requestCode + "  PackageManager: " + PackageManager.PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

