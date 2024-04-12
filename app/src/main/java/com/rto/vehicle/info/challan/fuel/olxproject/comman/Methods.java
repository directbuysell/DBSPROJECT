package com.rto.vehicle.info.challan.fuel.olxproject.comman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rto.vehicle.info.challan.fuel.olxproject.R;

public class Methods {

    public static int RC_GOOGLE = 1;
    public static Dialog progressDialog;
    public BottomSheetDialogListener bottomSheetDialogListener;

    public static String carnoPattern = "^[A-Z]{2}[ -]?[0-9]{2}[ -]?[A-Z]{1,2}[ -]?[0-9]{4}$";

    public static void bottomSheetDialog(Activity activity, int i, BottomSheetDialogListener bottomSheetDialogListener) {
        new Methods().bottomSheetDialogListener = bottomSheetDialogListener;


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity, R.style.SheetDialog);
        bottomSheetDialog.requestWindowFeature(1);

        bottomSheetDialog.setContentView(i);

        bottomSheetDialog.show();
        bottomSheetDialogListener.onCreated(bottomSheetDialog);
    }

    public interface BottomSheetDialogListener {
        void onCreated(BottomSheetDialog bottomSheetDialog);
    }


    public static void progressDialogShow(Activity activity) {
        progressDialogDismiss();
        Dialog dialog = new Dialog(activity, R.style.ProgressDialog);
        progressDialog = dialog;
        dialog.setContentView(R.layout.layout_progress_dialog);
        progressDialog.getWindow().setLayout(-1, -1);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public static void progressDialogDismiss() {
        Dialog dialog = progressDialog;
        if (dialog != null && dialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public static void hideKeyboard(Activity activity) {
        @SuppressLint("WrongConstant") InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

}
