package com.example.coronareport.data.models.historicalData;

import com.google.gson.annotations.SerializedName;

public class Timeline{

	@SerializedName("recovered")
	private Recovered recovered;

	@SerializedName("cases")
	private Cases cases;

	@SerializedName("deaths")
	private Deaths deaths;

	public Recovered getRecovered(){
		return recovered;
	}

	public Cases getCases(){
		return cases;
	}

	public Deaths getDeaths(){
		return deaths;
	}

	@Override
	public String toString() {
		return "Timeline{" +
				"recovered=" + recovered +
				", cases=" + cases +
				", deaths=" + deaths +
				'}';
	}
}