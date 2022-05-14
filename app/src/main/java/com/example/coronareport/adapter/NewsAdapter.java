package com.example.coronareport.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.coronareport.R;
import com.example.coronareport.data.models.newsData.ArticlesItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<ArticlesItem> {

    private Context context;
    private ArrayList<ArticlesItem> newsList;

    public NewsAdapter(Context context, ArrayList<ArticlesItem> newsList) {
        super(context, 0, newsList);
        this.context = context;
        this.newsList = newsList;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        ArticlesItem articles =  newsList.get(position);

        ImageView iw = (ImageView) listItemView.findViewById(R.id.newsImage);
        iw.setClipToOutline(true);
        Glide.with(context)
                .load(articles.getUrlToImage())
                .placeholder(R.drawable.coronavirus_banner)
                .centerCrop()
                .into(iw);


        TextView tw = (TextView) listItemView.findViewById(R.id.source);
        tw.setText(articles.getSource().getName());

        TextView tw2 = (TextView) listItemView.findViewById(R.id.title);

        String displayTitle = articles.getTitle().split(" - | \\| ")[0];

        tw2.setText(displayTitle);

        TextView tw3 = (TextView) listItemView.findViewById(R.id.time);

        String displayDate = "";
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(articles.getPublishedAt());
//            displayDate = new SimpleDateFormat("hh:mm aa MMM dd, yyyy", Locale.getDefault()).format(parsedDate);
            displayDate = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tw3.setText(displayDate);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChromeCustomTab(articles.getUrl());
            }
        });

        return listItemView;

    }
    private void openChromeCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams params = new CustomTabColorSchemeParams.Builder()
                .setNavigationBarColor(ContextCompat.getColor(context,R.color.colorPrimaryDark))
                .setToolbarColor(ContextCompat.getColor(context,R.color.colorAccent))
                .setSecondaryToolbarColor(ContextCompat.getColor(context,R.color.colorPrimaryDark))
                .build();
        builder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));

    }

}

