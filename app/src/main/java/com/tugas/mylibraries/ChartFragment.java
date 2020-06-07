package com.tugas.mylibraries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.tugas.mylibraries.adapter.Pager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View view;
    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, container, false);
        tabLayout = view.findViewById(R.id.createTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Bar"));
        tabLayout.addTab(tabLayout.newTab().setText("Pie"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = view.findViewById(R.id.createViewPager);
        Pager adapter = new Pager(getFragmentManager());
        adapter.addFragment(new ChartBarFragment(), "Bar Chart");
        adapter.addFragment(new ChartPieFragment(), "Pie Chart");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
