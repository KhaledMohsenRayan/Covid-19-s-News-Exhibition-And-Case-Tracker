package com.example.coronareport.data.database;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.coronareport.data.models.countriesDataModel.CountriesDataModelItem;
import com.example.coronareport.data.models.countryDataModel.CountryDataModel;
import com.example.coronareport.data.models.globalDataModel.GlobalDataModel;
import com.example.coronareport.data.models.newsData.ArticlesItem;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;

@Dao
public interface CoronaDao {

    // News ------------------------------

    @Query("select * from articlestable")
    List<ArticlesItem> getAllArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<ArticlesItem> articlesItemList);

    @Query("delete from articlestable")
    void deleteAllArticles();

    @Transaction
    default void deleteAndInsertArticlesInTransaction(List<ArticlesItem> articlesItemList) {
        // Anything inside this method runs in a single transaction.
        deleteAllArticles();
        insertArticles(articlesItemList);

    }

    // Global Data -------------

    @Query("select * from GlobalDataTable")
    GlobalDataModel getGlobalData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGlobalData(GlobalDataModel globalDataModel);

    @Query("delete from GlobalDataTable")
    void deleteGlobalData();

    @Transaction
    default void deleteAndInsertGlobalDataInTransaction(GlobalDataModel globalDataModel) {
        // Anything inside this method runs in a single transaction.
        deleteGlobalData();
        insertGlobalData(globalDataModel);
    }

    // Country Data -------------

    @Query("select * from CountryDataTable")
    CountryDataModel getCountryData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountryData(CountryDataModel countryDataModel);

    @Query("delete from CountryDataTable")
    void deleteCountryData();

    @Transaction
    default void deleteAndInsertCountryDataInTransaction(CountryDataModel countryDataModel) {
        // Anything inside this method runs in a single transaction.
        deleteCountryData();
        insertCountryData(countryDataModel);
    }

    // Countries Data -------------

    @Query("select * from CountriesDataTable")
    List<CountriesDataModelItem> getCountriesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountriesData(List<CountriesDataModelItem> countriesDataModelItemList);

    @Query("delete from CountriesDataTable")
    void deleteCountriesData();

    @Transaction
    default void deleteAndInsertCountriesDataInTransaction(List<CountriesDataModelItem> countriesDataModelItemList) {
        // Anything inside this method runs in a single transaction.
        deleteCountriesData();
        insertCountriesData(countriesDataModelItemList);
    }

    @Query("SELECT * FROM CountriesDataTable WHERE country LIKE :countryName")
    CountryDataModel findCountryWithName(String countryName);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertImages(Bitmap bitmap);

//    @Query("SELECT * FROM historicaltable")
//    JSONObject getAllHistorical();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAllHistorical(JSONObject jsonObject);
//
//    @Query("delete from HistoricalTable")
//    void deleteAllHistorical();
//
//    @Transaction
//    default void deleteAndInsertAllHistoricalInTransaction(JSONObject jsonObject) {
//        // Anything inside this method runs in a single transaction.
//        deleteAllHistorical();
//        insertAllHistorical(jsonObject);
//    }



}
