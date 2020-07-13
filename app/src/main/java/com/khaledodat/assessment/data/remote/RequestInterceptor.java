package com.khaledodat.assessment.data.remote;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.core.os.ConfigurationCompat;

import com.khaledodat.assessment.utils.Constants;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This okhttp interceptor is responsible for adding the common query parameters and headers
 */
public class RequestInterceptor implements Interceptor {

    Application app;

    public RequestInterceptor(Application app) {
        this.app = app;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String accessToken = app.getSharedPreferences(Constants.SharedPreferencesKeys.PREFS_KEY_SHARED_PREFS_NAME, Context.MODE_PRIVATE).getString(Constants.SharedPreferencesKeys.PREFS_KEY_TOKEN, "");
        String language = app.getSharedPreferences(Constants.SharedPreferencesKeys.PREFS_KEY_SHARED_PREFS_NAME, Context.MODE_PRIVATE).getString(Constants.SharedPreferencesKeys.PREFS_KEY_LANGUAGE, "en");

        Request request = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("language", language)
                .build();

        if (accessToken != null && !accessToken.isEmpty()){
            request = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
//                    .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjNzRHJSQjREYUswQVRHQWtXeEZza1MwdnN4UXk0SzZnYWpRVjZ3TmliUmsifQ.eyJleHAiOjE1ODQ2MTAxNzIsIm5iZiI6MTU4NDUyMzc3MiwidmVyIjoiMS4wIiwiaXNzIjoiaHR0cHM6Ly9yb25vY2tkZXZwLmIyY2xvZ2luLmNvbS81NjFlZDhmNC1iN2EyLTQ3YTYtOTRjNC1hMTBkMjhlYTQxYTgvdjIuMC8iLCJzdWIiOiI4ZDU0NjA2OS03ZTJlLTQwYjMtOGIwOC04NDRkNmJlZjMyMjciLCJhdWQiOiJhMjcxNTg4Mi01M2VhLTRjNDktYTFiNy1mN2VmZjA3NjI1YWMiLCJub25jZSI6ImRlZmF1bHROb25jZSIsImlhdCI6MTU4NDUyMzc3MiwiYXV0aF90aW1lIjoxNTg0NTIzNzcyLCJzaWduSW5OYW1lcy5waG9uZU51bWJlciI6Iis5NjI3ODY5OTE3MjUiLCJuYW1lIjoiTW9oYW1tZWQgQWwtS2lzd2FuaSIsImV4dGVuc2lvbl9HZW5kZXIiOiJNIiwidGlkIjoiNTYxZWQ4ZjQtYjdhMi00N2E2LTk0YzQtYTEwZDI4ZWE0MWE4In0.GSac5XgOe4KKW_lj0-5pKPs4ksOIdT0z2vi6iBsRpYHPK-jObusSMOhyyJr7qY1WNWT1ST324QxBC_JZRk8GkFElutF9diN6uz3Ovkm8QmQXrQUHoYqTQYN6V-k61PpyDZ0dLhKX0hKtgQKMyLK9_LxX7RY6qM69Tz8aBjQo2xctMMy1du2Nx_YUNwdfJJodIY6gRE7crp9JScwcXpSiNaDyQ-_ChBmK3rzszcKbNzbYJVxR8GZc1miIB5OgQeicgq698Eg7eV8X4z6knFNynE2e3wkdf5CkpjqGbFT19xiQufN9RDXrPWeWzFMQYqSv88cw_OKYdpnPxqNA45B3sw")
                    .addHeader("Authorization", "Bearer " + Objects.requireNonNull(accessToken))
                    .addHeader("language", ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0).getLanguage())
                    .build();
        }

        return chain.proceed(request);
    }
}