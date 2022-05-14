package com.example.coronareport.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.coronareport.R;
import com.example.coronareport.adapter.NewsAdapter;
import com.example.coronareport.api.ApiInterface;
import com.example.coronareport.api.RetrofitInstance;
import com.example.coronareport.data.database.RoomDatabaseInstance;
import com.example.coronareport.data.models.newsData.ArticlesItem;
import com.example.coronareport.data.models.newsData.NewsModel;
import com.example.coronareport.databinding.NewsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {

    private String TAG = "NewsFragment";
    private NewsFragmentBinding binding;
    private NewsAdapter newsAdapter;
    private String SOURCE_PARAM = "us";
    private ArrayList<ArticlesItem> articlesItem = new ArrayList<>();
    ApiInterface apiInterface;

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        binding = NewsFragmentBinding.inflate(inflater);

        newsAdapter = new NewsAdapter(getActivity(), new ArrayList<ArticlesItem>());
        binding.newsListView.setAdapter(newsAdapter);
        binding.newsListView.setEmptyView(binding.emptyTwNews);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.news_spinner_options, R.layout.spinner_item);
        binding.newsSpinner.setAdapter(spinnerAdapter);

        //get data from database
        getDataFromDatabase();

        return binding.getRoot();
    }


    private void getDataFromDatabase() {
        newsAdapter.clear();
        List<ArticlesItem> articlesItemList = RoomDatabaseInstance.getInstance(requireActivity()).coronaDao().getAllArticles();
            binding.newsShimmerList.stopShimmer();
            binding.newsShimmerList.setVisibility(View.GONE);
            newsAdapter.addAll(articlesItemList);
    }

    private void getDataFromApi() {
        binding.newsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        SOURCE_PARAM = "in";
                        break;
                    case 2:
                        SOURCE_PARAM = "au";
                        break;
                    case 3:
                        SOURCE_PARAM = "ru";
                        break;
                    case 4:
                        SOURCE_PARAM = "fr";
                        break;
                    case 5:
                        SOURCE_PARAM = "gb";
                        break;
                    default:
                        SOURCE_PARAM = "us";
                        break;
                }
                apiInterface.getNews(SOURCE_PARAM).enqueue(new Callback<NewsModel>() {
                    @Override
                    public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            newsAdapter.clear();
                            binding.newsShimmerList.stopShimmer();
                            binding.newsShimmerList.setVisibility(View.GONE);
                            try {
                                RoomDatabaseInstance.getInstance(getActivity()).coronaDao().deleteAndInsertArticlesInTransaction(response.body().getArticles());
                            }catch (Exception e){
                                Log.i(TAG, "Exception: " + e.getLocalizedMessage());
                            }
                            newsAdapter.addAll(response.body().getArticles());
                        }
                        if (binding.newsSwipeRefresh.isRefreshing()) {
                            binding.newsSwipeRefresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsModel> call, Throwable t) {
                        Log.i(TAG, "onFailure: " + t.getLocalizedMessage());

                    }
                });
                binding.newsSwipeRefresh.setRefreshing(true);
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //get data from api

        // create network call
        RetrofitInstance retrofitInstance = new RetrofitInstance("https://saurav.tech/NewsAPI/");

        apiInterface = retrofitInstance.apiInterface();

        getDataFromApi();

        binding.newsSwipeRefresh.setProgressBackgroundColorSchemeColor(binding.getRoot().getResources().getColor(R.color.colorPrimaryDark));
        binding.newsSwipeRefresh.setColorSchemeColors(binding.getRoot().getResources().getColor(R.color.colorAccent));
        binding.newsSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
    }

    private void refreshList() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getDataFromApi();
        } else {
            binding.emptyTwNews.setImageResource(R.drawable.disconnected_icon);
            Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_SHORT).show();
            binding.emptyTwNews.setVisibility(View.VISIBLE);
            getDataFromDatabase();
        }
        if (binding.newsSwipeRefresh.isRefreshing()) {
            binding.newsSwipeRefresh.setRefreshing(false);
        }
    }

}