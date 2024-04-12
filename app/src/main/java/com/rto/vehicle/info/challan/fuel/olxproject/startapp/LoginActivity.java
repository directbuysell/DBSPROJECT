package com.rto.vehicle.info.challan.fuel.olxproject.startapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.activity.HomeActivity;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;

public class LoginActivity extends AppCompatActivity {
    Activity activity;
    LinearLayout ll_mobilelogin, ll_googlelogin;

    EditText et_mobile;
    TextView btn_sendotp, btn_otp;
    ProgressBar pb_main, pb2_main;
    PinView otpView;
    String otpss;
    Intent intent;
    LinearLayout ll_newaccount;
    TextView tv_forget;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;

        ll_mobilelogin = (LinearLayout) findViewById(R.id.ll_mobilelogin);
        ll_googlelogin = (LinearLayout) findViewById(R.id.ll_googlelogin);
        ll_newaccount = (LinearLayout) findViewById(R.id.ll_newaccount);
        tv_forget = (TextView)findViewById(R.id.tv_forget);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, HomeActivity.class));
            }
        });

        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, ResetpasswordActivity.class));
            }
        });


        ll_newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ll_mobilelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods.bottomSheetDialog(activity, R.layout.bottom_firstnumber, new Methods.BottomSheetDialogListener() {
                    @Override
                    public void onCreated(BottomSheetDialog bottomSheetDialog) {
                        bottomSheetDialog.setCancelable(false);
                        et_mobile = (EditText) bottomSheetDialog.findViewById(R.id.et_mobile);
                        btn_sendotp = (TextView) bottomSheetDialog.findViewById(R.id.btn_sendotp);
                        pb_main = (ProgressBar) bottomSheetDialog.findViewById(R.id.pb_main);

                        btn_sendotp.setEnabled(false);
                        btn_sendotp.getBackground().setAlpha(50);

                        et_mobile.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (et_mobile.getText().toString().length() == 10) {
                                    btn_sendotp.setEnabled(true);
                                    btn_sendotp.getBackground().setAlpha(255);
                                } else {
                                    btn_sendotp.setEnabled(false);
                                    btn_sendotp.getBackground().setAlpha(50);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                        btn_sendotp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String mobilenumber = et_mobile.getText().toString().trim();

                                if (mobilenumber.isEmpty()) {
                                    et_mobile.setError("Enter valid mobile number");
                                    et_mobile.requestFocus();
                                } else {
                                    Methods.hideKeyboard(activity);
                                    SharePrefs.editor("number", mobilenumber);

                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            BottomSendOtp(activity);

                                        }
                                    }, 2500);


                                    btn_sendotp.setVisibility(View.GONE);
                                    pb_main.setVisibility(View.VISIBLE);
                                }


                            }
                        });

                    }
                });
            }
        });


        ll_googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoogleLogin();

            }
        });

    }


    private void BottomSendOtp(Activity activity) {

        Methods.bottomSheetDialog(activity, R.layout.bottom_firstotp, new Methods.BottomSheetDialogListener() {
            @Override
            public void onCreated(BottomSheetDialog bottomSheetDialog) {
                bottomSheetDialog.setCancelable(false);
                btn_otp = (TextView) bottomSheetDialog.findViewById(R.id.btn_otp);
                otpView = (PinView) bottomSheetDialog.findViewById(R.id.otp_view);
                pb2_main = (ProgressBar) bottomSheetDialog.findViewById(R.id.pb2_main);

                btn_otp.setEnabled(false);
                btn_otp.getBackground().setAlpha(50);


                otpView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (otpView.getText().toString().length() == 4) {
                            btn_otp.setEnabled(true);
                            btn_otp.getBackground().setAlpha(255);
                        } else {
                            btn_otp.setEnabled(false);
                            btn_otp.getBackground().setAlpha(50);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                btn_otp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otpss = otpView.getText().toString().trim();


                        if (otpss.isEmpty()) {
                            Toast.makeText(activity, "Please Enter OTP Code", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("===otp", "" + otpss);

                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    startActivity(new Intent(activity, HomeActivity.class));

                                }
                            }, 2500);


                            btn_sendotp.setVisibility(View.GONE);
                            pb2_main.setVisibility(View.VISIBLE);


                        }
                    }
                });

            }
        });

    }

    private void GoogleLogin() {
        Methods.progressDialogShow(activity);
        GoogleSignInClient client = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build());
        this.googleSignInClient = client;
        Intent signInIntent = client.getSignInIntent();
        intent = signInIntent;
        startActivityForResult(signInIntent, Methods.RC_GOOGLE);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == Methods.RC_GOOGLE && i2 == -1) {
            handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
            return;
        } else {
            Methods.progressDialogDismiss();
            try {
                SharePrefs.editor("login", true);
                intent = new Intent(activity, HomeActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount result = task.getResult(ApiException.class);
            Log.e("========!", "display name: " + result.getDisplayName());
            result.getId();
            String displayName = result.getDisplayName();
            try {
                SharePrefs.editor("photo", result.getPhotoUrl().toString());
            } catch (Exception unused) {
            }
            String email = result.getEmail();
            Log.e("========!", "handleSignInResult:name::" + displayName);
            SharePrefs.editor("name", displayName);
            SharePrefs.editor("email", email);
            SharePrefs.editor("login", true);
            Methods.progressDialogDismiss();

            intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
            finish();
        } catch (ApiException unused2) {
            Methods.progressDialogDismiss();
        }
    }

}