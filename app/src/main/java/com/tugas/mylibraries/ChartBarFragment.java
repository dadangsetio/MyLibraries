package com.tugas.mylibraries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tugas.mylibraries.object.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartBarFragment extends Fragment {
    private View view;
    private BarChart barChart;
    public ChartBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
        barChart = view.findViewById(R.id.barChart);

        List<Book> bookList = sqLiteHelper.getAllBookRecord();
        List<BarEntry> barEntryList = new ArrayList<>();
        BubbleSort(bookList, bookList.size());
        for (Book book : bookList){
            System.out.println(book.getRating());
        }
        int rating = bookList.get(0).getRating();
        int n = 1;
        for (int i=1; i<bookList.size(); i++){
            int t = bookList.get(i).getRating();
            if (rating == t){
                n++;
            }else {
                barEntryList.add(new BarEntry(rating, n));
                System.out.println(rating +"-----"+n);
                n=1;
                rating=t;
            }
        }
        barEntryList.add(new BarEntry(rating, n));
        BarDataSet dataSet = new BarDataSet(barEntryList, "Rating");
        dataSet.setColor(ColorTemplate.JOYFUL_COLORS[1]);
        BarData barData = new BarData(dataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);
        barChart.animateY(5000);
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setData(barData);

        barChart.setDragEnabled(true);
        barChart.invalidate();
        return view;
    }

    public void BubbleSort(List<Book> list, int count){
        int i, j;
        for (i=0; i<count-1; i++){
            for (j=0; j<count-i-1; j++){
                if(list.get(j).getRating() > list.get(j+1).getRating()){
                    int temp = list.get(j).getRating();
                    list.get(j).setRating(list.get(j+1).getRating());
                    list.get(j+1).setRating(temp);
                }
            }
        }
    }

}
