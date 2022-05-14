package com.example.coronareport.data.models.countryDataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "CountryDataTable")
public class CountryDataModel{

	@SerializedName("continent")
	private String continent;

	@SerializedName("country")
	private String country;

	@SerializedName("recoveredPerOneMillion")
	private double recoveredPerOneMillion;

	@PrimaryKey
	@SerializedName("cases")
	private int cases;

	@SerializedName("critical")
	private int critical;

	@SerializedName("oneCasePerPeople")
	private int oneCasePerPeople;

	@SerializedName("active")
	private int active;

	@SerializedName("testsPerOneMillion")
	private int testsPerOneMillion;

	@SerializedName("population")
	private int population;

	@SerializedName("oneDeathPerPeople")
	private int oneDeathPerPeople;

	@SerializedName("recovered")
	private int recovered;

	@SerializedName("oneTestPerPeople")
	private int oneTestPerPeople;

	@SerializedName("tests")
	private int tests;

	@SerializedName("criticalPerOneMillion")
	private double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	private int deathsPerOneMillion;

	@SerializedName("todayRecovered")
	private int todayRecovered;

	@SerializedName("casesPerOneMillion")
	private int casesPerOneMillion;

	@SerializedName("countryInfo")
	private CountryInfo countryInfo;

	@SerializedName("updated")
	private long updated;

	@SerializedName("deaths")
	private int deaths;

	@SerializedName("activePerOneMillion")
	private double activePerOneMillion;

	@SerializedName("todayCases")
	private int todayCases;

	@SerializedName("todayDeaths")
	private int todayDeaths;

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getRecoveredPerOneMillion() {
		return recoveredPerOneMillion;
	}

	public void setRecoveredPerOneMillion(double recoveredPerOneMillion) {
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}

	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public int getOneCasePerPeople() {
		return oneCasePerPeople;
	}

	public void setOneCasePerPeople(int oneCasePerPeople) {
		this.oneCasePerPeople = oneCasePerPeople;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getTestsPerOneMillion() {
		return testsPerOneMillion;
	}

	public void setTestsPerOneMillion(int testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getOneDeathPerPeople() {
		return oneDeathPerPeople;
	}

	public void setOneDeathPerPeople(int oneDeathPerPeople) {
		this.oneDeathPerPeople = oneDeathPerPeople;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getOneTestPerPeople() {
		return oneTestPerPeople;
	}

	public void setOneTestPerPeople(int oneTestPerPeople) {
		this.oneTestPerPeople = oneTestPerPeople;
	}

	public int getTests() {
		return tests;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	public double getCriticalPerOneMillion() {
		return criticalPerOneMillion;
	}

	public void setCriticalPerOneMillion(double criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	public int getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}

	public void setDeathsPerOneMillion(int deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	public int getTodayRecovered() {
		return todayRecovered;
	}

	public void setTodayRecovered(int todayRecovered) {
		this.todayRecovered = todayRecovered;
	}

	public int getCasesPerOneMillion() {
		return casesPerOneMillion;
	}

	public void setCasesPerOneMillion(int casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public double getActivePerOneMillion() {
		return activePerOneMillion;
	}

	public void setActivePerOneMillion(double activePerOneMillion) {
		this.activePerOneMillion = activePerOneMillion;
	}

	public int getTodayCases() {
		return todayCases;
	}

	public void setTodayCases(int todayCases) {
		this.todayCases = todayCases;
	}

	public int getTodayDeaths() {
		return todayDeaths;
	}

	public void setTodayDeaths(int todayDeaths) {
		this.todayDeaths = todayDeaths;
	}

	@Override
 	public String toString(){
		return 
			"CountryDataModel{" + 
			"continent = '" + continent + '\'' + 
			",country = '" + country + '\'' + 
			",recoveredPerOneMillion = '" + recoveredPerOneMillion + '\'' + 
			",cases = '" + cases + '\'' + 
			",critical = '" + critical + '\'' + 
			",oneCasePerPeople = '" + oneCasePerPeople + '\'' + 
			",active = '" + active + '\'' + 
			",testsPerOneMillion = '" + testsPerOneMillion + '\'' + 
			",population = '" + population + '\'' + 
			",oneDeathPerPeople = '" + oneDeathPerPeople + '\'' + 
			",recovered = '" + recovered + '\'' + 
			",oneTestPerPeople = '" + oneTestPerPeople + '\'' + 
			",tests = '" + tests + '\'' + 
			",criticalPerOneMillion = '" + criticalPerOneMillion + '\'' + 
			",deathsPerOneMillion = '" + deathsPerOneMillion + '\'' + 
			",todayRecovered = '" + todayRecovered + '\'' + 
			",casesPerOneMillion = '" + casesPerOneMillion + '\'' + 
			",countryInfo = '" + countryInfo + '\'' + 
			",updated = '" + updated + '\'' + 
			",deaths = '" + deaths + '\'' + 
			",activePerOneMillion = '" + activePerOneMillion + '\'' + 
			",todayCases = '" + todayCases + '\'' + 
			",todayDeaths = '" + todayDeaths + '\'' + 
			"}";
		}
}