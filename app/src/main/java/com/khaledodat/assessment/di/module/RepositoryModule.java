package com.khaledodat.assessment.di.module;

import com.khaledodat.assessment.data.local.dao.QuotesDao;
import com.khaledodat.assessment.data.local.files.AssetFiles;
import com.khaledodat.assessment.data.remote.ApiService;
import com.khaledodat.assessment.data.remote.repository.QuotesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * The application module which provides app wide instances of various components
 */
@Module(includes = ViewModelModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    QuotesRepository provideNotificationsRepository(ApiService apiService, QuotesDao quotesDao) {
        return new QuotesRepository(apiService, quotesDao);
    }

}
