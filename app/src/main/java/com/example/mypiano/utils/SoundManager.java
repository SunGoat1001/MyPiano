package com.example.mypiano.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.SparseArray;

import com.example.mypiano.R;


public class SoundManager {
    private SoundPool mSoundPool;
    private SparseArray mSoundPoolMap;
    private boolean mMute = false;
    private Context mContext;

    private static final int MAX_STREAM = 10;
    private static final int STOP_DELAY_MILIS = 10000;
    private Handler mHandler;

    private static SoundManager instance = null;

    public SoundManager() {
        mSoundPool = new SoundPool(MAX_STREAM,
                AudioManager.STREAM_MUSIC,
                0);

        mSoundPoolMap = new SparseArray();
        mHandler = new Handler();
    }

    public static SoundManager getInstance(){
        if (instance == null) {
            instance = new SoundManager();
        }

        return instance;
    }

    public void initStreamTypeMedia(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void addSound(int soundID) {
        mSoundPoolMap.put(soundID, mSoundPool.load(mContext, soundID, 1));
    }

    public void playSound(int soundID) {
        if (mMute) {
            return;
        }

        boolean hasSound = mSoundPoolMap.indexOfKey(soundID) >= 0;

        if (!hasSound) {
            return;
        }

        final int soundId = mSoundPool.play((Integer) mSoundPoolMap.get(soundID), 1, 1, 1, 0, 1f);

        scheduleSoundStop(soundId);
    }

    private void scheduleSoundStop(final int soundID) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSoundPool.stop(soundID);
            }
        }, STOP_DELAY_MILIS);
    }

    public void init(Context context) {
        this.mContext = context;
        instance.initStreamTypeMedia((Activity) mContext);
        instance.addSound(R.raw.key01);
        instance.addSound(R.raw.key02);
        instance.addSound(R.raw.key03);
        instance.addSound(R.raw.key04);
        instance.addSound(R.raw.key05);
        instance.addSound(R.raw.key06);
        instance.addSound(R.raw.key07);
        instance.addSound(R.raw.key08);
        instance.addSound(R.raw.key09);
        instance.addSound(R.raw.key10);
        instance.addSound(R.raw.key11);
        instance.addSound(R.raw.key12);
        instance.addSound(R.raw.key13);
        instance.addSound(R.raw.key14);
        instance.addSound(R.raw.key15);
        instance.addSound(R.raw.key16);
        instance.addSound(R.raw.key17);
        instance.addSound(R.raw.key18);
        instance.addSound(R.raw.key19);
        instance.addSound(R.raw.key20);
        instance.addSound(R.raw.key21);
        instance.addSound(R.raw.key22);
        instance.addSound(R.raw.key23);
        instance.addSound(R.raw.key24);
    }
}
