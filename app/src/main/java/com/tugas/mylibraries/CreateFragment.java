package com.tugas.mylibraries;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.tugas.mylibraries.adapter.Pager;
import com.tugas.mylibraries.object.Book;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CreateFragment extends Fragment {
    private View view;
    Button btnYearPicker;
    TextInputEditText edTitle, edAuhor, edPublisher, edLanguage, edYear, edPages;
    RatingBar ratingBar;
    private FloatingActionButton floatingActionButton;
    private String title, author, publisher, language;
    private int year, pages, ratingValue;
    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);
        final SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());

        btnYearPicker = view.findViewById(R.id.btnYearPicker);
        edTitle = view.findViewById(R.id.edTitle);
        edAuhor = view.findViewById(R.id.edAuthor);
        edPublisher = view.findViewById(R.id.edPublisher);
        edLanguage = view.findViewById(R.id.edLanguage);
        edYear = view.findViewById(R.id.edYear);
        edPages = view.findViewById(R.id.edPages);
        ratingBar = view.findViewById(R.id.bookRating);
        floatingActionButton = view.findViewById(R.id.floatingButton);

        btnYearPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createYearPickerDialog();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edTitle.getText().toString();
                author = edAuhor.getText().toString();
                publisher = edPublisher.getText().toString();
                language = edLanguage.getText().toString();
                year = Integer.parseInt(edYear.getText().toString());
                pages = Integer.parseInt(edPages.getText().toString());
                ratingValue = Math.round(ratingBar.getRating());

                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setLanguage(language);
                book.setYear(year);
                book.setPages(pages);
                book.setRating(ratingValue);
                sqLiteHelper.addBookRecord(book);
                Toast.makeText(view.getContext(), "Sukses", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void createYearPickerDialog(){
        Calendar currentdate = Calendar.getInstance();
        MonthPickerDialog.Builder pickerBuilder = new MonthPickerDialog.Builder(getContext(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {

            }
        }, currentdate.get(Calendar.YEAR), currentdate.get(Calendar.MONTH));
        pickerBuilder.setMinYear(1990)
                .setActivatedYear(2020)
                .setMaxYear(2030)
                .showYearOnly()
                .setTitle("Select Publish Year")
                .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                    @Override
                    public void onYearChanged(int selectedYear) {
                        edYear.setText(String.valueOf(selectedYear));
                    }
                })
                .build()
                .show();
    }
}
