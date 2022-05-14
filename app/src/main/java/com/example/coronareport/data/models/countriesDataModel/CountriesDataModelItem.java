package com.example.coronareport.data.models.countriesDataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

@Entity(tableName = "CountriesDataTable")
public class CountriesDataModelItem{

	@SerializedName("continent")
	private String continent;

	@SerializedName("country")
	private String country;

	@SerializedName("recoveredPerOneMillion")
	private Double recoveredPerOneMillion;

	@SerializedName("cases")
	private int cases;

	@SerializedName("critical")
	private Double critical;

	@SerializedName("oneCasePerPeople")
	private Double oneCasePerPeople;

	@SerializedName("active")
	private Double active;

	@SerializedName("testsPerOneMillion")
	private Double testsPerOneMillion;

	@SerializedName("population")
	private Double population;

	@SerializedName("oneDeathPerPeople")
	private Double oneDeathPerPeople;

	@SerializedName("recovered")
	private Double recovered;

	@SerializedName("oneTestPerPeople")
	private Double oneTestPerPeople;

	@SerializedName("tests")
	private Double tests;

	@SerializedName("criticalPerOneMillion")
	private Double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	private Double deathsPerOneMillion;

	@SerializedName("todayRecovered")
	private Double todayRecovered;

	@SerializedName("casesPerOneMillion")
	private Double casesPerOneMillion;

	@SerializedName("countryInfo")
	private CountriesInfo countriesInfo;

	@SerializedName("updated")
	private Double updated;

	@SerializedName("deaths")
	private Double deaths;

	@SerializedName("activePerOneMillion")
	private Double activePerOneMillion;

	@SerializedName("todayCases")
	private Double todayCases;

	@SerializedName("todayDeaths")
	private Double todayDeaths;

	@PrimaryKey(autoGenerate = true)
	private int id;

	public static Comparator<CountriesDataModelItem> sortByCases = new Comparator<CountriesDataModelItem>() {
		@Override
		public int compare(CountriesDataModelItem o1, CountriesDataModelItem o2) {
			return o2.getCases() - o1.getCases();
		}
	};

	public static Comparator<CountriesDataModelItem> sortByDeaths = new Comparator<CountriesDataModelItem>() {
		@Override
		public int compare(CountriesDataModelItem o1, CountriesDataModelItem o2) {
			return (int) (o2.getDeaths() - o1.getDeaths());
		}
	};

	public static Comparator<CountriesDataModelItem> sortByActiveCases = new Comparator<CountriesDataModelItem>() {
		@Override
		public int compare(CountriesDataModelItem o1, CountriesDataModelItem o2) {
			return (int) (o2.getActive() - o1.getActive());
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CountriesInfo getCountriesInfo() {
		return countriesInfo;
	}

	public void setCountriesInfo(CountriesInfo countriesInfo) {
		this.countriesInfo = countriesInfo;
	}

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

	public Double getRecoveredPerOneMillion() {
		return recoveredPerOneMillion;
	}

	public void setRecoveredPerOneMillion(Double recoveredPerOneMillion) {
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}

	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public Double getCritical() {
		return critical;
	}

	public void setCritical(Double critical) {
		this.critical = critical;
	}

	public Double getOneCasePerPeople() {
		return oneCasePerPeople;
	}

	public void setOneCasePerPeople(Double oneCasePerPeople) {
		this.oneCasePerPeople = oneCasePerPeople;
	}

	public Double getActive() {
		return active;
	}

	public void setActive(Double active) {
		this.active = active;
	}

	public Double getTestsPerOneMillion() {
		return testsPerOneMillion;
	}

	public void setTestsPerOneMillion(Double testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}

	public Double getPopulation() {
		return population;
	}

	public void setPopulation(Double population) {
		this.population = population;
	}

	public Double getOneDeathPerPeople() {
		return oneDeathPerPeople;
	}

	public void setOneDeathPerPeople(Double oneDeathPerPeople) {
		this.oneDeathPerPeople = oneDeathPerPeople;
	}

	public Double getRecovered() {
		return recovered;
	}

	public void setRecovered(Double recovered) {
		this.recovered = recovered;
	}

	public Double getOneTestPerPeople() {
		return oneTestPerPeople;
	}

	public void setOneTestPerPeople(Double oneTestPerPeople) {
		this.oneTestPerPeople = oneTestPerPeople;
	}

	public Double getTests() {
		return tests;
	}

	public void setTests(Double tests) {
		this.tests = tests;
	}

	public Double getCriticalPerOneMillion() {
		return criticalPerOneMillion;
	}

	public void setCriticalPerOneMillion(Double criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	public Double getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}

	public void setDeathsPerOneMillion(Double deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	public Double getTodayRecovered() {
		return todayRecovered;
	}

	public void setTodayRecovered(Double todayRecovered) {
		this.todayRecovered = todayRecovered;
	}

	public Double getCasesPerOneMillion() {
		return casesPerOneMillion;
	}

	public void setCasesPerOneMillion(Double casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}

	public CountriesInfo getCountryInfo() {
		return countriesInfo;
	}

	public void setCountryInfo(CountriesInfo countriesInfo) {
		this.countriesInfo = countriesInfo;
	}

	public Double getUpdated() {
		return updated;
	}

	public void setUpdated(Double updated) {
		this.updated = updated;
	}

	public Double getDeaths() {
		return deaths;
	}

	public void setDeaths(Double deaths) {
		this.deaths = deaths;
	}

	public Double getActivePerOneMillion() {
		return activePerOneMillion;
	}

	public void setActivePerOneMillion(Double activePerOneMillion) {
		this.activePerOneMillion = activePerOneMillion;
	}

	public Double getTodayCases() {
		return todayCases;
	}

	public void setTodayCases(Double todayCases) {
		this.todayCases = todayCases;
	}

	public Double getTodayDeaths() {
		return todayDeaths;
	}

	public void setTodayDeaths(Double todayDeaths) {
		this.todayDeaths = todayDeaths;
	}

	@Override
 	public String toString(){
		return 
			"CountriesDataModelItem{" + 
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
			",countryInfo = '" + countriesInfo + '\'' +
			",updated = '" + updated + '\'' + 
			",deaths = '" + deaths + '\'' + 
			",activePerOneMillion = '" + activePerOneMillion + '\'' + 
			",todayCases = '" + todayCases + '\'' + 
			",todayDeaths = '" + todayDeaths + '\'' + 
			"}";
		}
}