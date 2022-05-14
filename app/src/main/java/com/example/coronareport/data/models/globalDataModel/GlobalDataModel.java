package com.example.coronareport.data.models.globalDataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "GlobalDataTable")
public class GlobalDataModel{

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
	private double testsPerOneMillion;

	@SerializedName("population")
	private long population;

	@SerializedName("affectedCountries")
	private int affectedCountries;

	@SerializedName("oneDeathPerPeople")
	private int oneDeathPerPeople;

	@SerializedName("recovered")
	private int recovered;

	@SerializedName("oneTestPerPeople")
	private int oneTestPerPeople;

	@SerializedName("tests")
	private long tests;

	@SerializedName("criticalPerOneMillion")
	private double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	private double deathsPerOneMillion;

	@SerializedName("todayRecovered")
	private int todayRecovered;

	@SerializedName("casesPerOneMillion")
	private int casesPerOneMillion;

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

	public double getTestsPerOneMillion() {
		return testsPerOneMillion;
	}

	public void setTestsPerOneMillion(double testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public int getAffectedCountries() {
		return affectedCountries;
	}

	public void setAffectedCountries(int affectedCountries) {
		this.affectedCountries = affectedCountries;
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

	public long getTests() {
		return tests;
	}

	public void setTests(long tests) {
		this.tests = tests;
	}

	public double getCriticalPerOneMillion() {
		return criticalPerOneMillion;
	}

	public void setCriticalPerOneMillion(double criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	public double getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}

	public void setDeathsPerOneMillion(double deathsPerOneMillion) {
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
			"GlobalDataModel{" + 
			"recoveredPerOneMillion = '" + recoveredPerOneMillion + '\'' + 
			",cases = '" + cases + '\'' + 
			",critical = '" + critical + '\'' + 
			",oneCasePerPeople = '" + oneCasePerPeople + '\'' + 
			",active = '" + active + '\'' + 
			",testsPerOneMillion = '" + testsPerOneMillion + '\'' + 
			",population = '" + population + '\'' + 
			",affectedCountries = '" + affectedCountries + '\'' + 
			",oneDeathPerPeople = '" + oneDeathPerPeople + '\'' + 
			",recovered = '" + recovered + '\'' + 
			",oneTestPerPeople = '" + oneTestPerPeople + '\'' + 
			",tests = '" + tests + '\'' + 
			",criticalPerOneMillion = '" + criticalPerOneMillion + '\'' + 
			",deathsPerOneMillion = '" + deathsPerOneMillion + '\'' + 
			",todayRecovered = '" + todayRecovered + '\'' + 
			",casesPerOneMillion = '" + casesPerOneMillion + '\'' + 
			",updated = '" + updated + '\'' + 
			",deaths = '" + deaths + '\'' + 
			",activePerOneMillion = '" + activePerOneMillion + '\'' + 
			",todayCases = '" + todayCases + '\'' + 
			",todayDeaths = '" + todayDeaths + '\'' + 
			"}";
		}
}