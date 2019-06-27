package com.example.filemanager;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
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


    interface Lambda {
        void doFunction();
    }

    public static void askPermissions(Context context, Activity activity, String[] permissions) {

        for (int index = 0; index < permissions.length; index++) {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(context, permissions[index]) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[index])) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(context, "shouldShowRequestPermissionRationale", Toast.LENGTH_LONG).show();
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(activity, permissions, index);
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                Toast.makeText(context, "Permission has already been granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getPermissions.doFunction();

        askPermissions(this, MainActivity.this, PERMISSIONS);


    }
}

