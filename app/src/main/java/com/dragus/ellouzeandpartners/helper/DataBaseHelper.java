package com.dragus.ellouzeandpartners.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dragus.ellouzeandpartners.model.Collab;
import com.dragus.ellouzeandpartners.model.FirstLastName;
import com.dragus.ellouzeandpartners.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 05/03/2015.
 * Class that handle all database CRUD (Create, Read, Update, Delele) and database connection
 */


public class DataBaseHelper extends SQLiteOpenHelper {

    // Log tag
    private static final String TAG = DataBaseHelper.class.getSimpleName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dragus_db";

    // Table Names
    private static final String TABLE_COLLAB = "collabs";
    private static final String TABLE_USER = "users";

    // COLLABS Table - column names
    private static final String KEY_COLLAB_ID = "collab_login";
    private static final String KEY_COLLAB_LASTNAME = "collab_lastname";
    private static final String KEY_COLLAB_FIRSTNAME = "collab_firstname";
    private static final String KEY_COLLAB_DB_DATE = "collab_db_date";
    private static final String KEY_COLLAB_DB_PERID = "collab_db_perid";
    private static final String KEY_COLLAB_DB_ENTERPRISE = "collab_db_enterprise";
    private static final String KEY_COLLAB_DB_CONTRACT_TYPE = "collab_db_contract_type";
    private static final String KEY_COLLAB_DB_CONTRACT_SITE = "collab_db_contract_site";
    private static final String KEY_COLLAB_DB_EFFECTIVE_SITE = "collab_db_effective_site";
    private static final String KEY_COLLAB_DB_FLOOR = "collab_db_floor";
    private static final String KEY_COLLAB_DB_PLACE = "collab_db_place";
    private static final String KEY_COLLAB_DB_ORG1 = "collab_db_org1";
    private static final String KEY_COLLAB_DB_ORG2 = "collab_db_org2";


    // USERS Table - column names
    private static final String KEY_USER_LOGIN = "user_login";
    private static final String KEY_USER_PASS = "user_pass";
    // USER table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_USER_LOGIN + " TEXT PRIMARY KEY," + KEY_USER_PASS
            + " TEXT" + ")";


    // Table Create Statements
    private static final String KEY_COLLAB_DB_ORG3 = "collab_db_org3";
    // COLLAB table create statement
    private static final String CREATE_TABLE_COLLAB = "CREATE TABLE "
            + TABLE_COLLAB + "(" + KEY_COLLAB_ID + " TEXT PRIMARY KEY," + KEY_COLLAB_LASTNAME
            + " TEXT, " + KEY_COLLAB_FIRSTNAME
            + " TEXT, " + KEY_COLLAB_DB_DATE
            + " TEXT, " + KEY_COLLAB_DB_PERID
            + " TEXT, " + KEY_COLLAB_DB_ENTERPRISE
            + " TEXT, " + KEY_COLLAB_DB_CONTRACT_TYPE
            + " TEXT, " + KEY_COLLAB_DB_CONTRACT_SITE
            + " TEXT, " + KEY_COLLAB_DB_EFFECTIVE_SITE
            + " TEXT, " + KEY_COLLAB_DB_FLOOR
            + " TEXT, " + KEY_COLLAB_DB_PLACE
            + " TEXT, " + KEY_COLLAB_DB_ORG1
            + " TEXT, " + KEY_COLLAB_DB_ORG2
            + " TEXT, " + KEY_COLLAB_DB_ORG3
            + " TEXT" + ")";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // This method called when database is created, in which we write the table statements
    @Override
    public void onCreate(SQLiteDatabase db) {


        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_COLLAB);

        //insert row in database
        Log.e(TAG, "Called");

    }


    // This method is called in cases of modifying the table structure, adding constraints to database etc.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_COLLAB);

        // create new tables
        onCreate(db);
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


    // ------------------------ "users" table methods ----------------//

    /**
     * Creating a user
     */

    public void addUser(User user) {

        Log.e(TAG, "Enter Add User");
        boolean isExistUser = hasObject(user.getLogin());

        if (!isExistUser) {

            Log.e(TAG, "User do not exist in database");
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_USER_LOGIN, user.getLogin()); // User Login
            values.put(KEY_USER_PASS, user.getPassword()); // User Password

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close(); // Closing database connection

        } else
            Log.e(TAG, "User already in database");

    }

    /**
     * Getting single user
     */
    public User getUser(String login) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_USER_LOGIN,
                        KEY_USER_PASS}, KEY_USER_LOGIN + "=?",
                new String[]{String.valueOf(login)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(cursor.getString(0), cursor.getString(1));
        // return user
        return user;

    }

    /**
     * Getting all users
     */
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setLogin(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    /**
     * Verify if user exist
     */
    public boolean hasObject(String login) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_USER
                + " WHERE " + KEY_USER_LOGIN + " =? ";
        Cursor cursor = db.rawQuery(selectString, new String[]{login}); //add the String your searching by here

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }
        cursor.close();          // Don't forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }


    // ------------------------ "collabs" table methods ----------------//


    /**
     * Creating a user
     */

    public void addCollab(Collab collab) {

        Log.e(TAG, "Enter Add Collab");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COLLAB_ID, collab.getId_collab());
        values.put(KEY_COLLAB_LASTNAME, collab.getLast_name());
        values.put(KEY_COLLAB_FIRSTNAME, collab.getFirst_name());
        values.put(KEY_COLLAB_DB_DATE, collab.getDb_date());
        values.put(KEY_COLLAB_DB_PERID, collab.getDb_perid());
        values.put(KEY_COLLAB_DB_ENTERPRISE, collab.getDb_enterprise());
        values.put(KEY_COLLAB_DB_CONTRACT_TYPE, collab.getDb_contract_type());
        values.put(KEY_COLLAB_DB_CONTRACT_SITE, collab.getDb_contract_site());
        values.put(KEY_COLLAB_DB_EFFECTIVE_SITE, collab.getDb_effective_site());
        values.put(KEY_COLLAB_DB_FLOOR, collab.getDb_floor());
        values.put(KEY_COLLAB_DB_PLACE, collab.getDb_place());
        values.put(KEY_COLLAB_DB_ORG1, collab.getDb_org1());
        values.put(KEY_COLLAB_DB_ORG2, collab.getDb_org2());
        values.put(KEY_COLLAB_DB_ORG3, collab.getDb_org3());

        // Inserting Row
        db.insert(TABLE_COLLAB, null, values);
        db.close(); // Closing database connection


    }

    /**
     * Getting all collabs
     */
    public List<Collab> getAllCollabs() {

        List<Collab> collabList = new ArrayList<Collab>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COLLAB;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Collab collab = new Collab();
                collab.setId_collab(Integer.parseInt(cursor.getString(0)));
                collab.setLast_name(cursor.getString(1));
                collab.setFirst_name(cursor.getString(2));
                collab.setDb_date(cursor.getString(3));
                collab.setDb_perid(cursor.getString(4));
                collab.setDb_enterprise(cursor.getString(5));
                collab.setDb_contract_type(cursor.getString(6));
                collab.setDb_contract_site(cursor.getString(7));
                collab.setDb_effective_site(cursor.getString(8));
                collab.setDb_floor(cursor.getString(9));
                collab.setDb_place(cursor.getString(10));
                collab.setDb_org1(cursor.getString(11));
                collab.setDb_org2(cursor.getString(12));
                collab.setDb_org3(cursor.getString(13));



                // Adding cololab to list
                collabList.add(collab);
            } while (cursor.moveToNext());
        }

        // return collabs list
        return collabList;
    }

    /**
     * Getting all first and last NAMES collabs
     */
    public List<FirstLastName> getAllNamesCollabs() {

        List<FirstLastName> collabNamesList = new ArrayList<FirstLastName>();
        // Select All Query
        String selectQuery = "SELECT  collab_lastname, collab_firstname FROM " + TABLE_COLLAB;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FirstLastName collab = new FirstLastName();
                collab.setFirst_last_name(cursor.getString(0)+" "+cursor.getString(1));

                // Adding collab to list
                collabNamesList.add(collab);
            } while (cursor.moveToNext());
        }

        // return collabs Names list
        return collabNamesList;
    }


}
