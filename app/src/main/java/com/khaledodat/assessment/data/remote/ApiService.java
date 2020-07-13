package com.khaledodat.assessment.data.remote;

import com.khaledodat.assessment.data.entities.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The APIService interface which will contain the semantics of all the REST calls.
 */
public interface ApiService {

    @GET("random")
    Call<Quote> loadRandomQuotes();

}
