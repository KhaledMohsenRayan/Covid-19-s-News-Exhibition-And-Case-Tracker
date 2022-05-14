package com.example.coronareport.api;

import com.example.coronareport.data.models.countriesDataModel.CountriesDataModelItem;
import com.example.coronareport.data.models.countryDataModel.CountryDataModel;
import com.example.coronareport.data.models.globalDataModel.GlobalDataModel;
import com.example.coronareport.data.models.historicalData.HistoricalDataModel;
import com.example.coronareport.data.models.newsData.NewsModel;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines/category/health/{country}.json")
    @Headers({"Accept: application/json"})
    Call<NewsModel> getNews(@Path("country") String country);

    @GET("v3/covid-19/countries/{countryCode}")
    @Headers({"Accept: application/json"})
    Call<CountryDataModel> getCountryData(@Path("countryCode") String countryCode);

    @GET("v3/covid-19/countries")
    @Headers({"Accept: application/json"})
    Call<CountryDataModel> getAllCountryData();

    @GET("v3/covid-19/countries")
    @Headers({"Accept: application/json"})
    Call<List<CountriesDataModelItem>> getAllCountriesData(@Query("yesterday") String yesterday ,
                                                           @Query("twoDaysAgo") String twoDaysAgo,
                                                           @Query("sort") String sort,
                                                           @Query("allowNull") String allowNull);
    @GET("v3/covid-19/all")
    @Headers({"Accept: application/json"})
    Call<GlobalDataModel> getGlobalData();

    @GET("v3/covid-19/historical/{countryCode}?lastdays=8")
    @Headers({"Accept: application/json"})
    Call<ResponseBody> getHistoricalData(@Path("countryCode") String countryCode);

//    @GET("v3/covid-19/historical/{countryCode}?lastdays=8")
//    @Headers({"Accept: application/json"})
//    Call<HistoricalDataModel> getHistoricalDataTest(@Path("countryCode") String countryCode);
//
//    @GET("v3/covid-19/historical?lastdays=8")
//    @Headers({"Accept: application/json"})
//    Call<ResponseBody> getAllHistoricalData();






}
