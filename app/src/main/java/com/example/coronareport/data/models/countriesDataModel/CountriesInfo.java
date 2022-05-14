package com.example.coronareport.data.models.countriesDataModel;

import com.google.gson.annotations.SerializedName;

public class CountriesInfo {

	@SerializedName("flag")
	private String flag;

	@SerializedName("_id")
	private int id;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private Double lat;

	@SerializedName("long")
	private Double jsonMemberLong;

	@SerializedName("iso3")
	private String iso3;

	public String getFlag(){
		return flag;
	}

	public int getId(){
		return id;
	}

	public String getIso2(){
		return iso2;
	}

	public Double getLat() {
		return lat;
	}

	public Double getJsonMemberLong() {
		return jsonMemberLong;
	}

	public String getIso3(){
		return iso3;
	}

	@Override
 	public String toString(){
		return 
			"CountryInfo{" + 
			"flag = '" + flag + '\'' + 
			",_id = '" + id + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",lat = '" + lat + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			",iso3 = '" + iso3 + '\'' + 
			"}";
		}
}