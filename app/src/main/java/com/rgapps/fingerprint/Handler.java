package com.rgapps.fingerprint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.jar.Manifest;

/**
 * Created by rajat on 9/5/17.
 */

public class Handler extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal mCancellationSignal;
    private Context mAppContext;

    public Handler(Context context){
        mAppContext = context;

    }
    public void beginAuthentication(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject){

        mCancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(mAppContext, android.Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED)
            return;
        manager.authenticate(cryptoObject, mCancellationSignal, 0, this, null);
    }
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(mAppContext, errString, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(mAppContext, helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(mAppContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        Toast.makeText(mAppContext, "Authentication succeeded.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(mAppContext,SucceedActivity.class);
        mAppContext.startActivity(i);
        ((Activity)mAppContext).finish();       //Finish MainActivity
    }

}
