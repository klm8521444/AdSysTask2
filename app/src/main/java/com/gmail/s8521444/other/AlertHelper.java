package com.gmail.s8521444.other;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlertHelper {
    public static void showAlertDialog(final Context context,
                                       final int titleId,
                                       final String message) {
        new AlertDialog.Builder(context)
                .setTitle(titleId)
                .setMessage(message)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
