package com.khaledodat.assessment.data.remote.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.khaledodat.assessment.data.entities.Quote;
import com.khaledodat.assessment.data.local.dao.QuotesDao;
import com.khaledodat.assessment.data.remote.ApiService;
import com.khaledodat.assessment.view.callbacks.ResponseListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesRepository {

///////////////////////////////////////////////////// Important Notice ///////////////////////////////////////////////////////////////////////////////////
// To Add DAO class, First declare variable for specified DAO, Then Add it in constructor here, Then pass it from dependency injection RepositoryModule //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ApiService apiService;
    QuotesDao quotesDao;

    public QuotesRepository(ApiService apiService, QuotesDao quotesDao) {
        this.apiService = apiService;
        this.quotesDao = quotesDao;
    }
    public void loadRandomQuotes(ResponseListener<Quote> responseListener) {
        String requestStartTime = System.currentTimeMillis() + "";

        apiService.loadRandomQuotes().enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {

                if (response.isSuccessful()) {
                    responseListener.onSuccess(response.body());
                } else {
                    responseListener.onFailure(new Throwable(response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                responseListener.onFailure(t);
            }
        });
    }

    public void saveQuoteInDB(Quote quote) {
        Disposable a = quotesDao
                .saveQuote(quote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    public LiveData<Quote> loadSavedQuote() {
        return quotesDao.getLastSaveQuote();
    }

}
