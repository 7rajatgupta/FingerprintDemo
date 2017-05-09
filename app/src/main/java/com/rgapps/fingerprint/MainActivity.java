package com.rgapps.fingerprint;

import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_KEY_NAME = "example_key";   // Used to generate key
    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;
    private KeyStore mKeyStore;
    private KeyGenerator mKeyGenerator;
    private Cipher mCipher;
    private FingerprintManager.CryptoObject mCryptoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //hide the action bar

        //Getting the services up!
        mKeyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        mFingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        //Check whether the fingerprint lock is enabled on the lockscreen or not.

        if (!mKeyguardManager.isKeyguardSecure())
            Toast.makeText(this, "Lockscreen is not set", Toast.LENGTH_LONG).show();
        //Check the permissions
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED) //Check Permission
            Toast.makeText(this,"Improper Permissions",Toast.LENGTH_SHORT).show();
        if(!mFingerprintManager.hasEnrolledFingerprints())                   //Check if any fingerprint is enrolled
            Toast.makeText(this,"No fingerprints enrolled",Toast.LENGTH_SHORT).show();




    }

    public void keyGenerator(){
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            mKeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        }catch( NoSuchAlgorithmException | NoSuchProviderException e){
            //e.printStackTrace();
            throw new RuntimeException("Cant get key generator instance", e);
        }
        try {
            mKeyStore.load(null);
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(TAG_KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC).setUserAuthenticationRequired(true).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
        }catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertificateException | IOException e){
            //e.printStackTrace();
            throw new RuntimeException(e);

        }

    }
}
