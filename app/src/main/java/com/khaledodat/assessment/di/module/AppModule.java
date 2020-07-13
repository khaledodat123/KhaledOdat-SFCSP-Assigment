package com.khaledodat.assessment.di.module;

import android.app.Application;

import androidx.room.Room;

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khaledodat.assessment.data.local.AppDatabase;
import com.khaledodat.assessment.data.local.dao.QuotesDao;
import com.khaledodat.assessment.data.local.files.AssetFiles;
import com.khaledodat.assessment.data.remote.ApiService;
import com.khaledodat.assessment.data.remote.RequestInterceptor;
import com.khaledodat.assessment.utils.Constants;
import com.khaledodat.assessment.utils.PermissionChecker;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The application module which provides app wide instances of various components
 */
@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application application) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(Constants.Api.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(Constants.Api.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(Constants.Api.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor(application));
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    PermissionChecker providePermissionChecker(Application application) {
        return new PermissionChecker(application);
    }
    @Provides
    @Singleton
    AppDatabase provideQuotesDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "quotesDB.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    QuotesDao provideQuotesDao(AppDatabase appDatabase) {
        return appDatabase.QUotesnDao();
    }

    @Provides
    @Singleton
    AssetFiles provideAssetFiles(Application application) {
        return new AssetFiles(application, new Gson());
    }

}
