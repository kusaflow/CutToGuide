package com.kunal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.kunal.MainGame;

public class AndroidLauncher extends AndroidApplication implements AdVideoInterface, RewardedVideoAdListener, openOtherApps {
    private static final String TAG = "AndroidLauncher";
    //private AdView adView;

    private RewardedVideoAd adRewardedVideoView;
    private static final String REWARDED_VIDEO_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private VideoEventListener vel;

    Boolean is_video_ad_loaded = false;


    boolean doubleBackToExitPressedOnce = false;


    @Override
	protected void onCreate (Bundle savedInstanceState) {
	    //wasan updated the code
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        //initialize(new MainGame(this), config);

        //ad banner ---------------------------------------------------------------------------------
        RelativeLayout layout = new RelativeLayout(this);

        View gameView = initializeForView(new MainGame(this,this), config);


        layout.addView(gameView);


        /*
		adView = new AdView(this);

		adView.setAdListener(
		        new AdListener(){
                    @Override
                    public void onAdLoaded() {
                        Log.i(TAG, "Ad loaded");
                    }
                }
        );

		//adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest.Builder builder = new AdRequest.Builder();
        builder.addTestDevice("9888583A305DA8A163262D36B17A29EE");

        RelativeLayout.LayoutParams adpream = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        adpream.addRule(RelativeLayout.CENTER_HORIZONTAL);
        adpream.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        layout.addView(adView, adpream);

        //adView.loadAd(builder.build());
        */

        setContentView(layout);


        //banner ad finish here--------------------------------------------------------------------------------------


        //rewared ad video-----------------------------------------------------------------------------------------
        setupRewarded();

        //---------------------------------need to check------------------------------------------------
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
        //----------------------------------------------------------------------------------------------


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    //rewared ad video
    public void loadRewardedVideoAd(){
        adRewardedVideoView.loadAd(REWARDED_VIDEO_AD_UNIT_ID, new AdRequest.Builder().build());
    }


    public void setupRewarded() {
        adRewardedVideoView = MobileAds.getRewardedVideoAdInstance(this);
        adRewardedVideoView.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    @Override
    public boolean hasVideoLoaded(){
        if(is_video_ad_loaded) {
            return true;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                if (!adRewardedVideoView.isLoaded()) {
                    loadRewardedVideoAd();
                }
            }
        });
        return false;
    }

    // to show video--
    public void showRewardedVideoAd(){
        runOnUiThread(new Runnable() {
            public void run() {
                if (adRewardedVideoView.isLoaded()) {
                    adRewardedVideoView.show();
                } else {
                    loadRewardedVideoAd();
                }
            }
        });
    }

    public void setVideoEventListener (VideoEventListener listener) {
        this.vel = listener;
    }

    //listeners for rewarded ads

    //end of listeners

    @Override
    public void onRewardedVideoAdLoaded() {
        if(vel != null) {
            vel.onRewardedVideoAdLoadedEvent();
        }
        is_video_ad_loaded = true;
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        is_video_ad_loaded = false;
        loadRewardedVideoAd();
        if(vel != null) {
            vel.onRewardedVideoAdClosedEvent();
        }
    }

    @Override
    public void onRewarded(RewardItem reward) {
        if(vel != null) {
            // The type and the amount can be set in your AdMob console
            vel.onRewardedEvent(reward.getType(), reward.getAmount());
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    //end of rewared video-------------------------------------------------------------


    ///open other apps and make toast
    @Override
    public void OpenApp(String link) {

        Intent intent = new Intent(this,AndroidLauncher.class);
        try {;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        } catch (Exception e) {

        }
        startActivity(intent);
    }

    @Override
    public void MakeToast(String text) {
        //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /////////////////////

    @Override
    protected void onPause() {
        adRewardedVideoView.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        adRewardedVideoView.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
	    adRewardedVideoView.destroy(this);
        super.onDestroy();
    }


}
