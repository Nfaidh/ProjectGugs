package com.dragus.ellouzeandpartners.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dragus.ellouzeandpartners.dragus.R;
import com.dragus.ellouzeandpartners.util.hashage.Sha1;
import com.dragus.ellouzeandpartners.webservices.AsyncTaskCollabs;
import com.dragus.ellouzeandpartners.webservices.AsyncTaskLogin;


import java.io.IOException;


public class LoginActivity extends ActionBarActivity implements OnClickListener {


    // Log tag
    private static final String TAG = ActionBarActivity.class.getSimpleName();
    public static int NO_OPTIONS = 0;
    public static ProgressDialog pDialog;
    // login json url
    private static String url = "http://10.0.2.2:80/test/v1/login?";
    Toast toast;
    Intent intent;
    boolean isDataValid = false;
    private String content_login;
    private String content_pwd;
    private String hash_content_pwd;
    private boolean isSessionActive = false;
    private EditText edit_login;
    private EditText edit_pwd;
    private CheckBox check_session;
    private Button btn_connect;
    private Boolean status = false;
    private String msg = "";
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // get views from layout_login
        edit_login = (EditText) findViewById(R.id.id_login);
        edit_pwd = (EditText) findViewById(R.id.id_pwd);
        check_session = (CheckBox) findViewById(R.id.id_active_session);
        btn_connect = (Button) findViewById(R.id.id_btn_cn);


        // make the click on button connect possible
        btn_connect.setOnClickListener(this);

        // init pDialog
        pDialog = new ProgressDialog(this);

        // Get the app's shared preferences
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        // Get the value for the run counter
        counter = app_preferences.getInt("counter", 0);

        Log.e("This app", counter + " times.");

        // when the app is first created we initialize list of collabs
        if (counter == 1) {
            // call ws returning data to insert to the table of users

            Log.e(TAG, "Called WS GETTING Collab");
            init_table_collabs();
        }



    }

    @Override
    public void onClick(View v) {

        // action on click button connect
        // -- redirect to the activity list of plans



        // gets the data of login and pass from layout login
        content_login = edit_login.getText().toString();
        content_pwd = edit_pwd.getText().toString();


        // if one (or both) of login,password is (are) empty
        boolean isDataEmpty = verify_empty(content_login, content_pwd);

        if (!isDataEmpty) {
            Log.d(TAG, "Login/Pass not empty");
            try {
                hash_content_pwd = Sha1.computeSHAHash(content_pwd);
                // we will using AsyncTask during parsing
                // calling web service that validates login and pass for users
                // new AsyncTaskParseJson().execute();

                // when the app is first created we initialize list of users
                // if (counter == 1) {
                // call ws returning data to insert to the table of users
                // Todo Change Calling WS Login
                Log.e(TAG, "Called WS Login");
                init_table_users();
                //}

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.e(TAG, "Login/Pass empty");
            toast = Toast.makeText(getApplicationContext(), R.string.text_empty_fields, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }


    }


    // method that call the WS and validates the login and pass of users
    private boolean validate_data() throws IOException {

        if (status)
            return true;
        else
            return false;

    }


    //method that verifies if login or/and pass is/are empty
    private boolean verify_empty(String login, String pwd) {

        boolean result_empty = false;
        if (login.trim().equals("") || pwd.trim().equals(""))
            result_empty = true;

        return result_empty;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public void init_table_users() {

        new AsyncTaskLogin(content_login, content_pwd, getApplicationContext()).execute();

    }

    public void init_table_collabs() {

        new AsyncTaskCollabs(getApplicationContext()).execute();

    }


}
