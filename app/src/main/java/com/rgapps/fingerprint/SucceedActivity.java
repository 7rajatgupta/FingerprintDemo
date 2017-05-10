package com.rgapps.fingerprint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class SucceedActivity extends AppCompatActivity {

    private ImageButton mGitbutton,mAboutButton;
    private String mUrl = "https://github.com/rajatpunkstaa/FingerprintDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succeed);

        getSupportActionBar().hide();

        mGitbutton = (ImageButton)findViewById(R.id.git_button);
        mAboutButton = (ImageButton)findViewById(R.id.about_button);

        mGitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mUrl));
                startActivity(i);
            }
        });
    }

}
