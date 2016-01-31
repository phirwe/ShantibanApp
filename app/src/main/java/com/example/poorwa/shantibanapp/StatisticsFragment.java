package com.example.poorwa.shantibanapp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


/**
 * Created by poorwa on 31/1/16.
 */
public class StatisticsFragment extends Fragment {

    View myView;
    ListView statisticsList;
    ArrayAdapter<String> statisticsAdapter;
    String[] statisticsArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.layout_statistics, container, false);
        statisticsList = (ListView) myView.findViewById(R.id.statisticsList);

        statisticsArray = getResources().getStringArray(R.array.statistics_types);
        statisticsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.layout_list, statisticsArray);
        statisticsList.setAdapter(statisticsAdapter);

        statisticsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        incomeStatistics();
                        break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), statisticsArray[1], Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity().getApplicationContext(), "Apoorva Manda", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return myView;

    }


    public void incomeStatistics() {

        String[] code = getResources().getStringArray(R.array.income_types);

// Pie Chart Section Value
        double[] distribution = {3.9, 12.9, 45.8, 1.9, 23.7, 1.6, 10.2};

// Color of each Pie Chart Sections
        int[] colors = {Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
                Color.YELLOW, Color.LTGRAY};

// Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Income Statistics");
        for (int i = 0; i < distribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        defaultRenderer.setBackgroundColor(Color.parseColor("#011936"));
        defaultRenderer.setLabelsColor(Color.parseColor("#ffffdd"));
        defaultRenderer.setAxesColor(Color.parseColor("#ffffdd"));
        defaultRenderer.setApplyBackgroundColor(true);
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("Income Statistics");
        defaultRenderer.setChartTitleTextSize(50);
        defaultRenderer.setLabelsTextSize(30);
        defaultRenderer.setLegendTextSize(30);
        //defaultRenderer.setZoomButtonsVisible(true);
        defaultRenderer.setZoomEnabled(true);

        // Creating an intent to plot bar chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getPieChartIntent(getActivity().getApplicationContext(), distributionSeries, defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        startActivity(intent);
    }
}

