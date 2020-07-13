package com.khaledodat.assessment.data.remote.repository;

import com.khaledodat.assessment.data.entities.Quote;
import com.khaledodat.assessment.data.remote.ApiService;
import com.khaledodat.assessment.view.callbacks.ResponseListener;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The article repository which has access to local and remote data fetching services
 */
public class AppRepository {

    @Inject
    ApiService apiService;

    @Inject
    QuotesRepository quotesRepository;

    @Inject
    public AppRepository() {
    }

    public QuotesRepository getQuotesRepository() {
        return quotesRepository;
    }


}
