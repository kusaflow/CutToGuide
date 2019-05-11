package com.kunal;

public interface VideoEventListener {
    void onRewardedEvent(String type, int amount);
    void onRewardedVideoAdLoadedEvent();
    void onRewardedVideoAdClosedEvent();
}
