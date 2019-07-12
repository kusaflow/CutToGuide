package com.kunal;

public interface AdVideoInterface {
 public boolean hasVideoLoaded();
 public void loadRewardedVideoAd();
 public void showRewardedVideoAd();
 public void setVideoEventListener(VideoEventListener listener);
}