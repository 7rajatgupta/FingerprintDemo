package com.rgapps.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

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

    }
}
