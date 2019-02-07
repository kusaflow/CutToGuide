package com.kunal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.kunal.MainGame;

public class AndroidLauncher extends AndroidApplication {
    private static final String TAG = "AndroidLauncher";
    private AdView adView;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        View gameView = initializeForView(new MainGame(), config);

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

		adView.setAdSize(AdSize.FULL_BANNER);

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

	}
}
