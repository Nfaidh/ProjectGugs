package com.dragus.ellouzeandpartners.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dragus.ellouzeandpartners.activities.LoginActivity;
import com.dragus.ellouzeandpartners.helper.DataBaseHelper;
import com.dragus.ellouzeandpartners.model.Collab;
import com.dragus.ellouzeandpartners.model.FirstLastName;
import com.dragus.ellouzeandpartners.model.User;
import com.dragus.ellouzeandpartners.util.parser.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 06/03/2015.
 */
public class AsyncTaskCollabs extends AsyncTask<String, String, String> {


    public static final String TAG = AsyncTaskLogin.class.getSimpleName();
    String yourJsonStringUrl;
    boolean isDataValid = false;
    private String content_login;
    private String hash_content_pwd;
    private ProgressDialog pDialog;
    private Boolean status;
    private String msg;
    private Context context;
    private List<User> listUsers = new ArrayList<User>();

    // database
    DataBaseHelper db;

    //listview collab
    private ArrayList<FirstLastName> list_collabs_names = new ArrayList<FirstLastName>();


    public AsyncTaskCollabs(Context context) {

        // set your json string url here
        yourJsonStringUrl = "http://10.0.2.2:80/test/v1/collabs";

        pDialog = LoginActivity.pDialog;
        //database sqlite
        db = new DataBaseHelper(context);
    }

    @Override
    protected void onPreExecute() {

        // Showing progress dialog before making http request
        pDialog.setMessage("Loading Collabs ...");
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... arg0) {


        // instantiate our json parser
        JsonParser jParser = new JsonParser();

        // get json string from url
        JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);


        if (json != null) {


            //get array collabs
            JSONArray collabs = null;
            try {
                collabs = json.getJSONArray("collabs");

                for (int i = 0; i < collabs.length(); i++) {


                    JSONObject obj = collabs.getJSONObject(i);

                    Collab collab = new Collab();
                    collab.setId_collab(obj.getInt("id_collab"));
                    collab.setLast_name(obj.getString("last_name"));
                    Log.e(TAG, "ID " + obj.getInt("id_collab"));
                    collab.setFirst_name(obj.getString("first_name"));
                    collab.setDb_perid(obj.getString("db_perid"));
                    collab.setDb_enterprise(obj.getString("db_enterprise"));
                    collab.setDb_contract_type(obj.getString("db_contract_type"));
                    collab.setDb_contract_site(obj.getString("db_contract_site"));
                    collab.setDb_effective_site(obj.getString("db_effective_site"));
                    collab.setDb_floor(obj.getString("db_floor"));
                    collab.setDb_place(obj.getString("db_place"));
                    collab.setDb_org1(obj.getString("db_org1"));
                    collab.setDb_org2(obj.getString("db_org2"));
                    collab.setDb_org3(obj.getString("db_org3"));

                    list_collabs_names.add(new FirstLastName(obj.getString("first_name") + " " + obj.getString("last_name")));

                    // adding collab to collabs table
                    db.addCollab(collab);

                    Log.e(TAG, "" + db.getAllCollabs().size());


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else
            Log.e(TAG, "The result JSON is NULL");


        return null;

    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {

        Log.e(TAG, "Finish GETTING Collab");
        hidePDialog();


    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
