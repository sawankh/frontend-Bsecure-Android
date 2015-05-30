package com.tfg.sawan.bsecure.credentials;

import android.app.Activity;
import android.util.Log;

import com.tfg.sawan.bsecure.utils.Preferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sawan on 30/05/2015.
 */
public class Token {
    private static String token;
    private static String expiry_date;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Token.token = token;
    }

    public static String getExpiry_date() {
        return expiry_date;
    }

    public static void setExpiry_date(String expiry_date) {
        Token.expiry_date = expiry_date;
    }

    public static boolean isExpired() {
        SimpleDateFormat date_format = new SimpleDateFormat("MM-dd-yyyy");
        Date expiry_date_token = new Date();
        Date actual_date =  new Date();



        try {
            expiry_date_token = date_format.parse(Token.expiry_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // If actual date is before expiry date it means it has not expired
        if (actual_date.before(expiry_date_token)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean tokenExists() {
        if ((Token.token != null) && (Token.token.compareTo(Preferences.NOT_FOUND_MESSAGE) != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void loadToken(Activity activity) {
        Token.setToken(Preferences.readPreferences(activity, "token", Preferences.NOT_FOUND_MESSAGE));
        Token.setExpiry_date(Preferences.readPreferences(activity, "expiry", Preferences.NOT_FOUND_MESSAGE));
    }
}