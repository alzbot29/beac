package com.example.alzbot;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class AlzManager {

    private static final String PREF_NAME = "com.example.alzbot";

    private static AlzManager sInstance;
    private final SharedPreferences mPref;

    private AlzManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AlzManager(context);
        }
    }

    public static synchronized AlzManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(AlzManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setValue(String key,String value) {
        mPref.edit()
                .putString(key, value)
                .commit();
    }

    public void setValue(String key, Set<String> value) {
        mPref.edit()
                .putStringSet(key, value)
                .commit();
    }
    public void deleteValue(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public String getValue(String key) {
        return mPref.getString(key, "");
    }
    public Set<String> getValue(String key,Set<String> def) {
        return mPref.getStringSet(key, def);
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}