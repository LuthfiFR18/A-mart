package com.example.a_martmobprog;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "YourAppNamePref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // Save user details when "Remember Me" is checked
    public void saveRememberMeDetails(String email, String password, boolean rememberMe) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_REMEMBER_ME, rememberMe);
        editor.apply();
    }

    // Retrieve saved user details
    public User getRememberMeDetails() {
        String email = pref.getString(KEY_EMAIL, null);
        String password = pref.getString(KEY_PASSWORD, null);
        boolean rememberMe = pref.getBoolean(KEY_REMEMBER_ME, false);

        return new User(email, password, rememberMe);
    }
}

