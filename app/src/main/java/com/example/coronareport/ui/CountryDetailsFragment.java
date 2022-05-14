package com.example.coronareport.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coronareport.R;
import com.example.coronareport.adapter.CoronaAdapter;
import com.example.coronareport.api.ApiInterface;
import com.example.coronareport.api.RetrofitInstance;
import com.example.coronareport.data.database.RoomDatabaseInstance;
import com.example.coronareport.data.models.countryDataModel.CountryDataModel;
import com.example.coronareport.data.models.historicalData.HistoricalDataModel;
import com.example.coronareport.databinding.FragmentCountryDetailsBinding;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailsFragment extends Fragment {

    private FragmentCountryDetailsBinding binding;
    private String TAG = "CountryDetailsFragment";
    private ApiInterface apiInterface;
    private NavController navController;

    LineGraphSeries<DataPoint> caseSeries;
    LineGraphSeries<DataPoint> deathSeries;

    private ArrayList<Integer> casesList = new ArrayList<>();
    private ArrayList<Integer> deathsList = new ArrayList<>();

    String countryName;
    CountryDetailsFragmentArgs args;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();

        binding = FragmentCountryDetailsBinding.inflate(inflater);
        binding.graph.setBackgroundResource(R.drawable.accent_bg);

        if (getArguments() != null) {
            args = CountryDetailsFragmentArgs.fromBundle(getArguments());
            countryName = args.getCOUNTRYNAME();
        }

        RetrofitInstance retrofitInstance = new RetrofitInstance("https://disease.sh/");
        apiInterface = retrofitInstance.apiInterface();

        getCountryData();


        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        binding.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                binding.graph.removeAllSeries();
                switch (i) {
                    case R.id.rb2:
                        deathSeries.setAnimated(true);
                        caseSeries.setAnimated(false);
                        binding.graph.addSeries(deathSeries);
                        binding.trendlinetext.setTextColor(getResources().getColor(R.color.colorDeathTrend));
                        break;
                    default:
                        caseSeries.setAnimated(true);
                        deathSeries.setAnimated(false);
                        binding.graph.addSeries(caseSeries);
                        binding.trendlinetext.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getHistoricalData(String countryName) {
        apiInterface.getHistoricalData(countryName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    JSONObject mainJsonObject = null;
                    try {
                        mainJsonObject = new JSONObject(response.body().string());

                        JSONObject casesObject = mainJsonObject.getJSONObject("timeline").getJSONObject("cases");
                        JSONObject deathsObject = mainJsonObject.getJSONObject("timeline").getJSONObject("deaths");

                        // Separate the keys into a JSONArray
                        JSONArray caseskeys = casesObject.names();
                        JSONArray deathskeys = deathsObject.names();
                        // Retrieve the keys and values
                        for (int i = 0; i < caseskeys.length(); i++) {
                            String casesKey = caseskeys.getString(i);
                            int casesValue = casesObject.getInt(casesKey);

                            String deathsKey = deathskeys.getString(i);
                            int deathsValue = deathsObject.getInt(deathsKey);

                            casesList.add(casesValue);
                            deathsList.add(deathsValue);
                        }

                        DataPoint[] caseDataPoints = new DataPoint[casesList.size() - 1];
                        DataPoint[] deathDataPoints = new DataPoint[deathsList.size() - 1];

                        int prevCasesData = casesList.get(0);
                        int prevDeathData = deathsList.get(0);

                        for (int i = 0; i < casesList.size() - 1; i++) {
                            caseDataPoints[i] = new DataPoint(i, casesList.get(i + 1) - prevCasesData);
                            deathDataPoints[i] = new DataPoint(i, deathsList.get(i + 1) - prevDeathData);
                            prevCasesData = casesList.get(i);
                            prevDeathData = deathsList.get(i);
                        }

                        caseSeries = new LineGraphSeries<>(caseDataPoints);
                        deathSeries = new LineGraphSeries<>(deathDataPoints);

                        Paint paint = new Paint();
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(getResources().getColor(R.color.colorAccent));
                        caseSeries.setCustomPaint(paint);


                        Paint paint2 = new Paint();
                        paint2.setStyle(Paint.Style.STROKE);
                        paint2.setColor(getResources().getColor(R.color.colorDeathTrend));
                        deathSeries.setCustomPaint(paint2);

                        caseSeries.setDataPointsRadius(10);
                        deathSeries.setDataPointsRadius(10);

                        caseSeries.setDrawDataPoints(true);
                        deathSeries.setDrawDataPoints(true);

                        deathSeries.setAnimated(true);
                        caseSeries.setAnimated(true);

                        binding.graph.addSeries(caseSeries);
                        binding.graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i(TAG, "catch: " + e.getLocalizedMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void getCountryData(String countryName) {
        apiInterface.getCountryData(countryName).enqueue(new Callback<CountryDataModel>() {
            @Override
            public void onResponse(Call<CountryDataModel> call, Response<CountryDataModel> response) {
                if (response.isSuccessful()) {
                    Picasso.get()
                            .load(response.body().getCountryInfo().getFlag())
                            .placeholder(R.drawable.world_icon)
                            .into(binding.flag, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    binding.shimmerBoi.stopShimmer();
                                    binding.shimmerBoi.setVisibility(View.GONE);
                                    binding.countryInfo.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    Log.i(TAG, "Problem loading Flag");
                                }
                            });
                    setTexts(response.body());
                    Log.i(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<CountryDataModel> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void findCountryWithName(String countryName) {
        try {
            CountryDataModel countryDataModel = RoomDatabaseInstance.getInstance(requireActivity()).coronaDao().findCountryWithName(countryName);
            if (countryDataModel != null) {
                setTexts(countryDataModel);
                binding.shimmerBoi.stopShimmer();
                binding.shimmerBoi.setVisibility(View.GONE);
                binding.countryInfo.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {

        }

    }

    private void getCountryData() {
        ConnectivityManager connMgr = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            binding.countryInfo.setVisibility(View.GONE);
            binding.rg.setVisibility(View.VISIBLE);
            getCountryData(countryName);
            getHistoricalData(countryName);
        } else {
            binding.rg.setVisibility(View.INVISIBLE);
            findCountryWithName(countryName);
            Toast.makeText(requireActivity(), R.string.no_connection, Toast.LENGTH_LONG).show();
        }
    }

    private void setTexts(CountryDataModel countryDataModel) {

        binding.ccountry.setText(countryDataModel.getCountry());

        CoronaAdapter.setText(binding.cactiveCount, countryDataModel.getActive());

        CoronaAdapter.setText(binding.ccasesCount, countryDataModel.getTodayCases());

        CoronaAdapter.setText(binding.ctotalCasesCount, countryDataModel.getTodayCases());

        CoronaAdapter.setText(binding.cdeathCount, countryDataModel.getTodayDeaths());

        CoronaAdapter.setText(binding.ctotalDeathCount, countryDataModel.getDeaths());

        CoronaAdapter.setText(binding.crecovered, countryDataModel.getRecovered());

        CoronaAdapter.setText(binding.ccritical, countryDataModel.getCritical());

        CoronaAdapter.setText(binding.ctests, countryDataModel.getTests());

    }

}