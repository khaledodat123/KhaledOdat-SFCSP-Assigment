package com.khaledodat.assessment.utils;

public class Constants {

    private Constants() {
        // Private constructor to hide the implicit one
    }

    public static class SharedPreferencesKeys {
        public static final String PREFS_KEY_SHARED_PREFS_NAME = "global_prefs";
        public static final String PREFS_KEY_NOTIFICATION_SETTINGS = "notifications_settings";
        public static final String PREFS_KEY_LANGUAGE = "lang";
        public static final String PREFS_KEY_TOKEN = "token";
    }

    public static class Api {
        public static final String BASE_URL = "https://programming-quotes-api.herokuapp.com/quotes/";
        public static final long CONNECT_TIMEOUT = 30000;
        public static final long READ_TIMEOUT = 30000;
        public static final long WRITE_TIMEOUT = 30000;
        public static final String API_KEY = "Put your api key here";
    }
}
