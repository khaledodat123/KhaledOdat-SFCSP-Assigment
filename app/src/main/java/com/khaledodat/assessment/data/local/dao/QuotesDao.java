package com.khaledodat.assessment.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.khaledodat.assessment.data.entities.Quote;

import io.reactivex.Maybe;


@Dao
public interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> saveQuote(Quote quote);

    @Query("DELETE FROM quote WHERE mId = :itemId")
    int deleteByItemId(String itemId);

    @Query("SELECT * FROM quote LIMIT 1")
    LiveData<Quote> getLastSaveQuote();
}
