package com.example.filemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

public class permissionDialogList {

    private boolean okStatus = false;
    private MainActivity.Lambda permissionCallbackFunction;


    permissionDialogList(Context context, String[] itemsList, MainActivity.Lambda recursionFunction) {

        final ArrayList selectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Set the dialog title
        builder.setTitle("need following permissions to function")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(itemsList, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int itemIndex, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(itemIndex);
                        } else if (selectedItems.contains(itemIndex)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(itemIndex));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK
                        recursionFunction.doFunction();
                        //Toast.makeText(context, "User clicked OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("ask me later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

}
