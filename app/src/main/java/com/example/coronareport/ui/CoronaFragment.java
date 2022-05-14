package com.example.coronareport.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.coronareport.R;
import com.example.coronareport.adapter.CoronaAdapter;
import com.example.coronareport.api.ApiInterface;
import com.example.coronareport.api.RetrofitInstance;
import com.example.coronareport.data.database.RoomDatabaseInstance;
import com.example.coronareport.data.models.countriesDataModel.CountriesDataModelItem;
import com.example.coronareport.databinding.CoronaFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaFragment extends Fragment implements CoronaAdapter.OnClickCountry {

    public String TAG = "CoronaFragment";
    private CoronaFragmentBinding binding;
    private ApiInterface apiInterface;
    private CoronaAdapter coronaAdapter;
    private NavController navController;

    private List<CountriesDataModelItem> list = new ArrayList<>();

    String[] DAY_PARAM = {"false", "false"};

    String SORT_PARAM = "todayCases";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        binding = CoronaFragmentBinding.inflate(inflater);

        coronaAdapter = new CoronaAdapter(getActivity(), new ArrayList<CountriesDataModelItem>(), this);
        binding.list.setAdapter(coronaAdapter);

        Log.i(TAG, "onCreateView: ");
        binding.list.setEmptyView(binding.emptyTw);

        RetrofitInstance retrofitInstance = new RetrofitInstance("https://disease.sh/");
        apiInterface = retrofitInstance.apiInterface();

        getCountriesDataFromDatabase();

        binding.swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimaryDark));
        binding.swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_options, R.layout.spinner_item);

        binding.daySpinner.setAdapter(spinnerAdapter);
        binding.daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        DAY_PARAM[0] = "true";
                        DAY_PARAM[1] = "false";
                        break;
                    case 2:
                        DAY_PARAM[0] = "false";
                        DAY_PARAM[1] = "true";
                        break;
                    default:
                        DAY_PARAM[0] = "false";
                        DAY_PARAM[1] = "false";
                        break;
                }
                getAllCountriesDataFromApi(DAY_PARAM[0], DAY_PARAM[1], SORT_PARAM);
                binding.swipeRefresh.setRefreshing(true);
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.search.setIconifiedByDefault(false);
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                coronaAdapter.getFilter().filter(newText);
                return true;
            }
        });
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(25);
                ImageView i = (ImageView) v;
                if (binding.search.getVisibility() == View.GONE) {
                    binding.search.setVisibility(View.VISIBLE);
                    i.setImageResource(R.drawable.search_off_icon);
                } else {
                    binding.search.setQuery("", false);
                    binding.search.setVisibility(View.GONE);
                    i.setImageResource(R.drawable.search_icon);
                }
            }
        });

        binding.sortBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sort_by_today_deaths:
                        SORT_PARAM = "todayDeaths";
                        break;
                    case R.id.sort_by_active_cases:
                        SORT_PARAM = "active";
                        break;
                    default:
                        SORT_PARAM = "todayCases";
                }
                Log.i(TAG, "onCheckedChanged: " + SORT_PARAM);
                binding.swipeRefresh.setRefreshing(true);
                getAllCountriesDataFromApi(DAY_PARAM[0], DAY_PARAM[1], SORT_PARAM);
                refreshList();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getCountriesDataFromDatabase() {
        List<CountriesDataModelItem> countriesList = RoomDatabaseInstance.getInstance(requireActivity()).coronaDao().getCountriesData();
        coronaAdapter.clear();
        if (countriesList.size() != 0) {
            binding.emptyTw.setImageResource(R.drawable.not_found);
            binding.shimmerList.stopShimmer();
            binding.shimmerList.setVisibility(View.GONE);
            coronaAdapter.addAll(countriesList);
            if (binding.swipeRefresh.isRefreshing()) {
                binding.swipeRefresh.setRefreshing(false);
            }
        }
    }

    private void getAllCountriesDataFromApi(String yesterday, String twoDaysAgo, String sort) {
        apiInterface.getAllCountriesData(yesterday, twoDaysAgo, sort, "false").enqueue(new Callback<List<CountriesDataModelItem>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CountriesDataModelItem>> call, Response<List<CountriesDataModelItem>> response) {
                coronaAdapter.clear();
                if (response.isSuccessful()) {
                    binding.emptyTw.setImageResource(R.drawable.not_found);
                    coronaAdapter.clear();
                    binding.shimmerList.stopShimmer();
                    binding.shimmerList.setVisibility(View.GONE);
                    list.clear();
                    list.addAll(response.body());
                    if (sort.equals("todayCases")) {
                        Collections.sort(list, CountriesDataModelItem.sortByCases);
                    }
                    if (sort.equals("todayDeaths")) {
                        Collections.sort(list, CountriesDataModelItem.sortByDeaths);
                    }
                    if (sort.equals("active")) {
                        Collections.sort(list, CountriesDataModelItem.sortByActiveCases);
                    }
                    coronaAdapter.addAll(list);
                    try {
                        RoomDatabaseInstance.getInstance(getActivity()).coronaDao().deleteAndInsertCountriesDataInTransaction(response.body());
                    } catch (Exception e) {
                        Log.i(TAG, "Exception: " + e.getLocalizedMessage());
                    }
                    if (binding.swipeRefresh.isRefreshing()) {
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<CountriesDataModelItem>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void refreshList() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getAllCountriesDataFromApi(DAY_PARAM[0], DAY_PARAM[1], SORT_PARAM);
        } else {
            coronaAdapter.clear();
            binding.emptyTw.setImageResource(R.drawable.disconnected_icon);
            Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_SHORT).show();
            binding.emptyTw.setVisibility(View.VISIBLE);
            getCountriesDataFromDatabase();
        }
        if (binding.swipeRefresh.isRefreshing()) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onCountryClick(String countryName) {
        CoronaFragmentDirections.ActionCoronaFragmentToCountyDetailsFragment action = CoronaFragmentDirections.actionCoronaFragmentToCountyDetailsFragment();
        action.setCOUNTRYNAME(countryName);
        navController.navigate(action);
    }
}