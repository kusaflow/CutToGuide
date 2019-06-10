package com.kunal;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
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

public class AndroidLauncher extends AndroidApplication implements AdVideoInterface, RewardedVideoAdListener {
    private static final String TAG = "AndroidLauncher";
    private AdView adView;

    private RewardedVideoAd adRewardedVideoView;
    private static final String REWARDED_VIDEO_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private VideoEventListener vel;

    Boolean is_video_ad_loaded = false;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        //initialize(new MainGame(this), config);

        //ad banner ---------------------------------------------------------------------------------
        RelativeLayout layout = new RelativeLayout(this);

        View gameView = initializeForView(new MainGame(this), config);

        layout.addView(gameView);

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

        adView.loadAd(builder.build());
        setContentView(layout);
        //banner ad finish here--------------------------------------------------------------------------------------


        //rewared ad video-----------------------------------------------------------------------------------------
        setupRewarded();

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
