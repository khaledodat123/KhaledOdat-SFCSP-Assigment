package com.khaledodat.assessment.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khaledodat.assessment.data.entities.Quote;
import com.khaledodat.assessment.data.remote.repository.AppRepository;
import com.khaledodat.assessment.utils.SingleLiveEvent;
import com.khaledodat.assessment.view.callbacks.ResponseListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Landpage ViewModel
 */
public class LandpageViewModel extends ViewModel {
    private static final String TAG = "LandpageViewModel";

    private AppRepository appRepository;

    private final MutableLiveData<Quote> quoteMutableLiveData = new MutableLiveData<>();

    public SingleLiveEvent<String> getShowErrorMessage() {
        return showErrorMessage;
    }

    private final SingleLiveEvent<String> showErrorMessage = new SingleLiveEvent<>();

    public MutableLiveData<Quote> getQuoteMutableLiveData() {
        return quoteMutableLiveData;
    }

    @Inject
    public LandpageViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;

    }

    public void loadRandomQuote() {

        if (null != appRepository) {
            appRepository.getQuotesRepository().loadRandomQuotes(new ResponseListener<Quote>() {
                @Override
                public void onSuccess(Quote data) {
                    appRepository.getQuotesRepository().saveQuoteInDB(data);
                    quoteMutableLiveData.postValue(data);
                }

                @Override
                public void onFailure(Throwable t) {
                    showErrorMessage.call();
                }

                @Override
                public void onAction(String message) {

                }
            });
        }
    }

    public LiveData<Quote> loadSavedQuote(){
        return appRepository.getQuotesRepository().loadSavedQuote();
    }

}
