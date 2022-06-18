package br.com.modal.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Utils {
    public static final String WEB_SERVICE_URI = "https://challenge-mobile-api.modl.pro/";
    private static final String LOGIN_TOKEN_IDENTIFIER = "LOGIN_TOKEN";

    public static void setLoginToken(String loginToken, Context context) {

        SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefsEditor.putString(LOGIN_TOKEN_IDENTIFIER, loginToken);
        prefsEditor.commit();
    }

    public static String getLoginToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(LOGIN_TOKEN_IDENTIFIER, "");
    }
}
