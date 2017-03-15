package com.superapps.ravi.localshop;

import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
{

    public static String EXTRA_MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        showMessage("The app is running on a phone with API level " + Build.VERSION.SDK_INT);
    }

    public void openShopOwnerListing(View view) {
        Intent intent = new Intent(this, ShopOwnerListingGDrive.class);
        startActivity(intent);
        // Do something in response to button
    }

    public void openCustomerActivity(View view) {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
