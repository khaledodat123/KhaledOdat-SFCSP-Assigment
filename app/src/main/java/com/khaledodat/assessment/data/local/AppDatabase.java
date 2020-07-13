package com.khaledodat.assessment.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.khaledodat.assessment.data.entities.Quote;
import com.khaledodat.assessment.data.local.dao.QuotesDao;

@Database(entities = {Quote.class}, version = 7, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract QuotesDao QUotesnDao();
}