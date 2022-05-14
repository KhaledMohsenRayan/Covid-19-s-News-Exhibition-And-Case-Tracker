package com.example.coronareport.data.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.room.TypeConverter;

import com.example.coronareport.data.models.countriesDataModel.CountriesInfo;
import com.example.coronareport.data.models.countryDataModel.CountryInfo;
import com.example.coronareport.data.models.newsData.Source;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import okhttp3.ResponseBody;

public class Converters {

    @TypeConverter
    public String fromSource(Source source) {
        return new Gson().toJson(source);
    }

    @TypeConverter
    public Source toSource(String name) {
        return new Gson().fromJson(name, Source.class);
    }

    @TypeConverter
    public String fromCountryInfo(CountryInfo countryInfo) {
        return new Gson().toJson(countryInfo);
    }

    @TypeConverter
    public CountryInfo toCountryInfo(String name) {
        return new Gson().fromJson(name, CountryInfo.class);
    }

    @TypeConverter
    public String fromCountriesInfo(CountriesInfo countriesInfo) {
        return new Gson().toJson(countriesInfo);
    }

    @TypeConverter
    public CountriesInfo toCountriesInfo(String name) {
        return new Gson().fromJson(name, CountriesInfo.class);
    }

//    @TypeConverter
//    public byte[] bitMapToByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        return b;
//
////        String temp = Base64.encodeToString(b, Base64.DEFAULT);
////        if (temp == null) {
////            return null;
////        } else
////            return temp;
//    }
//
//    @TypeConverter
//    public Bitmap byteArrayToBitMap(byte[] bytes) {
//        try {
////            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            if (bitmap == null) {
//                return null;
//            } else {
//                return bitmap;
//            }
//
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//
//    }

//    @TypeConverter
//    public String fromJSONObject(JSONObject jsonObject) {
//        return new Gson().toJson(jsonObject);
//    }
//
//    @TypeConverter
//    public JSONObject toJSONObject(String name) {
//        return new Gson().fromJson(name, JSONObject.class);
//    }


}
