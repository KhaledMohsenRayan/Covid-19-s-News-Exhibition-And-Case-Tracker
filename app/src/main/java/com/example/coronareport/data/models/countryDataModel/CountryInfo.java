package com.example.coronareport.data.models.countryDataModel;

import com.google.gson.annotations.SerializedName;

public class CountryInfo{

	@SerializedName("flag")
	private String flag;

	@SerializedName("_id")
	private int id;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private int lat;

	@SerializedName("long")
	private int jsonMemberLong;

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

	public int getLat(){
		return lat;
	}

	public int getJsonMemberLong(){
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