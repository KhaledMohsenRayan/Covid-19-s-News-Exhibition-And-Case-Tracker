package com.example.coronareport.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.coronareport.data.models.countriesDataModel.CountriesDataModelItem;
import com.example.coronareport.data.models.countryDataModel.CountryDataModel;
import com.example.coronareport.data.models.globalDataModel.GlobalDataModel;
import com.example.coronareport.data.models.newsData.ArticlesItem;

@Database(entities = {ArticlesItem.class , GlobalDataModel.class , CountryDataModel.class , CountriesDataModelItem.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class RoomDatabaseInstance extends RoomDatabase {

    public abstract CoronaDao coronaDao();

    private static volatile RoomDatabaseInstance INSTANCE;

    public static RoomDatabaseInstance getInstance(Context context) {
        if (INSTANCE == null && context != null) {
            synchronized (RoomDatabaseInstance.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabaseInstance.class,
                            "CoronaDatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
