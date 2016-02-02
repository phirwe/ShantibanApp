package com.example.poorwa.shantibanapp;

import android.app.FragmentManager;
import android.content.Intent;
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

/**
 * Created by poorwa on 30/1/16.
 */
public class IncomeFragment extends Fragment {
    View myView;
    ListView incomeList;
    ArrayAdapter<String> incomeAdapter;
    String[] incomeArray;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.layout_income, container,false);
        incomeList = (ListView) myView.findViewById(R.id.incomeList);

        incomeArray = getResources().getStringArray(R.array.income_types);
        incomeAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.layout_list, incomeArray);
        incomeList.setAdapter(incomeAdapter);

        incomeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        intent = new Intent(getActivity().getApplicationContext(), AnnualMaintenanceActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity().getApplicationContext(), ClubHouseActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity().getApplicationContext(), NonOccupancyActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity().getApplicationContext(), PlotSaleActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity().getApplicationContext(), BankFdInterestActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity().getApplicationContext(), SavingsBankInterestActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getActivity().getApplicationContext(), MiscellaneousActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getActivity().getApplicationContext(), "Apoorva Manda", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return myView;

    }
}