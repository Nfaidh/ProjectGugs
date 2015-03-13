package com.dragus.ellouzeandpartners.app;

/**
 * Created by T-3500 on 02/03/2015.
 */

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.dragus.ellouzeandpartners.helper.DataBaseHelper;
import com.dragus.ellouzeandpartners.model.User;
import com.dragus.ellouzeandpartners.webservices.AsyncTaskLogin;

import java.util.List;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public static final String PREFS_NAME = "MyApp_Settings";
    private static AppController mInstance;
    // database
    DataBaseHelper db;
    private RequestQueue mRequestQueue;



    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // Get the app's shared preferences
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        // Get the value for the run counter
        int counter = app_preferences.getInt("counter", 0);


        Log.e("This app", counter + " times.");

        // Increment the counter
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putInt("counter", ++counter);
        editor.commit(); // Very important

        mInstance = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }




}