package com.dragus.ellouzeandpartners.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.dragus.ellouzeandpartners.activities.ListPlansActivity;
import com.dragus.ellouzeandpartners.activities.LoginActivity;
import com.dragus.ellouzeandpartners.util.parser.JsonParser;
import com.dragus.ellouzeandpartners.util.hashage.Sha1;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by T-3500 on 06/03/2015.
 */
public class AsyncTaskLogin extends AsyncTask<String, String, String> {


    public static final String TAG = AsyncTaskLogin.class.getSimpleName();
    String yourJsonStringUrl;
    private String content_login;
    private String hash_content_pwd;
    private ProgressDialog pDialog;
    private Boolean status;
    private String msg;
    boolean isDataValid = false;
    private Context context;

    public AsyncTaskLogin(String login, String pass, Context context) {

        this.content_login = login;
        this.context = context;

        try {
            this.hash_content_pwd = Sha1.computeSHAHash(pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // set your json string url here
        yourJsonStringUrl = "http://10.0.2.2:80/test/v1/login?" + "login=" + content_login + "&password=" + hash_content_pwd;

        pDialog = LoginActivity.pDialog;
    }

    @Override
    protected void onPreExecute() {

        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... arg0) {


        Log.d(TAG, "Login is " + content_login + " and password hashed is " + hash_content_pwd);
        // instantiate our json parser
        JsonParser jParser = new JsonParser();

        // get json string from url
        JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

        if (json != null) {

            try {

                // Storing each json item in variable
                status = json.getBoolean("status");
                msg = json.getString("message");

                // show the values in our logcat
                Log.d(TAG, "status: " + status
                        + ", msg : " + msg);

               // TODO Store data in database


            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } else
            Log.e(TAG, "The result JSON is NULL");

        return null;

    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {
        try {
            isDataValid = validate_data();

            if (isDataValid) {
                Log.d(TAG, "LOGIN Success");
                Intent intent = new Intent(context, ListPlansActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                (context).startActivity(intent);

                //((Activity)context).finish();

            } else {
                Log.e(TAG, "LOGIN Failed");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // method that call the WS and validates the login and pass of users
    private boolean validate_data() throws IOException {

        if (status)
            return true;
        else
            return false;

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
