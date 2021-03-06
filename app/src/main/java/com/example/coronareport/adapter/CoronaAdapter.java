package com.example.coronareport.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coronareport.R;
import com.example.coronareport.data.models.countriesDataModel.CountriesDataModelItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CoronaAdapter extends ArrayAdapter<CountriesDataModelItem> implements Filterable {

    private ArrayList<CountriesDataModelItem> originalData;
    private ArrayList<CountriesDataModelItem> displayData;
    private Context context;
    private OnClickCountry onClickCountry;

    public CoronaAdapter(Context context, ArrayList<CountriesDataModelItem> coronaList , OnClickCountry onClickCountry) {
        super(context, 0, coronaList);
        this.originalData = coronaList;
        this.displayData = coronaList;
        this.context = context;
        this.onClickCountry = onClickCountry;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        CountriesDataModelItem c = displayData.get(position);

        TextView tw = (TextView) listItemView.findViewById(R.id.country);
        tw.setText(c.getCountry());

        TextView tw2 = (TextView) listItemView.findViewById(R.id.casesCount);
        setText(tw2, c.getCases());

        TextView tw3 = (TextView) listItemView.findViewById(R.id.deathCount);
        setText(tw3, c.getDeaths().intValue());

        TextView tw4 = (TextView) listItemView.findViewById(R.id.activeCount);
        setText(tw4, c.getActive().intValue());

        ImageView countryFlag = (ImageView) listItemView.findViewById(R.id.country_flag);
        Glide.with(context)
                .load(c.getCountryInfo().getFlag())
                .into(countryFlag);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCountry.onCountryClick(c.getCountry());
            }
        });

        return listItemView;

    }

    @Override
    public int getCount() {
        return displayData.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                ArrayList<CountriesDataModelItem> filteredList = new ArrayList<>();

                if(originalData == null) {
                    originalData = new ArrayList<>(displayData);
                }

                if(charSequence == null || charSequence.length() == 0) {
                    results.count = originalData.size();
                    results.values = originalData;
                }

                else {
                    charSequence = charSequence.toString().toLowerCase();
                    for (CountriesDataModelItem c : originalData) {
                        if (c.getCountry().toLowerCase().startsWith(charSequence.toString())) {
                            filteredList.add(c);
                        }
                    }
                    results.count = filteredList.size();
                    results.values = filteredList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                displayData = (ArrayList<CountriesDataModelItem>) filterResults.values;
                if(displayData.isEmpty())
                    Toast.makeText(getContext(), R.string.not_found, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public static void setText(TextView tw, int count)
    {
        if(count == -1)
            tw.setText(R.string.not_known);
        else
            tw.setText(NumberFormat.getNumberInstance(Locale.US).format(count));
    }

    public interface OnClickCountry{
        void onCountryClick(String CountryName);
    }

}
