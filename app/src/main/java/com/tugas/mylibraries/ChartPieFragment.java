package com.tugas.mylibraries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tugas.mylibraries.object.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartPieFragment extends Fragment {
    View view;
    public ChartPieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_game, container, false);
        PieChart pieChart = view.findViewById(R.id.pieChart);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
        List<Book> bookList = sqLiteHelper.getAllBookRecord();
        List<PieEntry> pieEntryList = new ArrayList<>();
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
                pieEntryList.add(new PieEntry(rating, n));
                System.out.println(rating +"-----"+n);
                n=1;
                rating=t;
            }
        }
        pieChart.getDescription().setEnabled(false);
        pieEntryList.add(new PieEntry(rating, n));
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Rating");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.animateXY(5000, 5000);

        pieChart.invalidate();

        // Inflate the layout for this fragment
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
