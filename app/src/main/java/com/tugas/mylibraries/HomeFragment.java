package com.tugas.mylibraries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugas.mylibraries.adapter.RecyclerViewAdpt;
import com.tugas.mylibraries.object.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Book> bookList;
    private View view;
    private SQLiteHelper sqLiteHelper;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);
         bookList = new ArrayList<>();
         recyclerView = view.findViewById(R.id.viewRecycler);
         recyclerView.setHasFixedSize(true);
         sqLiteHelper = new SQLiteHelper(getContext());
         layoutManager = new LinearLayoutManager(view.getContext());
         recyclerView.setLayoutManager(layoutManager);
         adapter = new RecyclerViewAdpt(sqLiteHelper.getAllBookRecord(), getContext());
         recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}
