package com.example.coronareport.data.models.historicalData;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoricalDataModel{

	@SerializedName("country")
	private String country;

	@SerializedName("province")
	private List<String> province;

	@SerializedName("timeline")
	private Timeline timeline;

	public String getCountry(){
		return country;
	}

	public List<String> getProvince(){
		return province;
	}

	public Timeline getTimeline(){
		return timeline;
	}

	@Override
	public String toString() {
		return "HistoricalDataModel{" +
				"country='" + country + '\'' +
				", province=" + province +
				", timeline=" + timeline +
				'}';
	}
}