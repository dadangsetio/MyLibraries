package com.tugas.mylibraries.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tugas.mylibraries.R;
import com.tugas.mylibraries.SQLiteHelper;
import com.tugas.mylibraries.object.Book;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecyclerViewAdpt extends RecyclerView.Adapter<RecyclerViewAdpt.ViewHolder> {
    List<Book> bookList;
    Dialog dialog;
    Context context;
    SQLiteHelper sqLiteHelper;

    public RecyclerViewAdpt(List<Book> bookList, Context context){
        this.bookList = bookList;
        this.context = context;
        sqLiteHelper = new SQLiteHelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Book booklisted = bookList.get(position);
        final int id = bookList.get(position).getId_Books();
        final String title = bookList.get(position).getTitle();
        final String author = bookList.get(position).getAuthor();
        final String publisher = bookList.get(position).getPublisher();
        final String language = bookList.get(position).getLanguage();
        final String year = String.valueOf(bookList.get(position).getYear());
        final String pages = String.valueOf(bookList.get(position).getPages());
        final int ratingValue = bookList.get(position).getRating();

        holder.tvTitle.setText(title);
        holder.tvAuthor.setText(author);
        holder.tvPublisher.setText(publisher +
                ", " +
                year);
        holder.tvLanguage.setText(language);
        holder.ratingBar.setRating(ratingValue);
        holder.tvPages.setText(pages + " pages");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_details, null);
                Button btnYearPicker = view.findViewById(R.id.btnDialogYearPicker);
                final TextInputEditText edTitle = view.findViewById(R.id.edDialogTitle);
                final TextInputEditText edAuhor = view.findViewById(R.id.edDialogAuthor);
                final TextInputEditText edPublisher = view.findViewById(R.id.edDialogPublisher);
                final TextInputEditText edLanguage = view.findViewById(R.id.edDialogLanguage);
                final TextInputEditText edYear = view.findViewById(R.id.edDialogYear);
                final TextInputEditText edPages = view.findViewById(R.id.edDialogPages);
                final RatingBar ratingBar = view.findViewById(R.id.ratingDialog);

                edTitle.setText(title);
                edAuhor.setText(author);
                edPublisher.setText(publisher);
                edPages.setText(pages);
                edYear.setText(year);
                edLanguage.setText(language);
                ratingBar.setRating(ratingValue);

                builder.setView(view);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteHelper.deleteBookRecord(booklisted);
                        notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Book book = new Book();
                        book.setId_Books(id);
                        book.setTitle(edTitle.getText().toString());
                        book.setAuthor(edAuhor.getText().toString());
                        book.setPublisher(edPublisher.getText().toString());
                        book.setYear(Integer.parseInt(edYear.getText().toString()));
                        book.setPages(Integer.parseInt(edPages.getText().toString()));
                        book.setLanguage(edLanguage.getText().toString());
                        book.setRating(Math.round(ratingBar.getRating()));
                        Log.d("asdada", "onClick: "+ edTitle);
                        sqLiteHelper.updateBookRecord(book);

                    }
                });
                btnYearPicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar currentdate = Calendar.getInstance();
                        MonthPickerDialog.Builder pickerBuilder = new MonthPickerDialog.Builder(context,
                                new MonthPickerDialog.OnDateSetListener() {
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
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvAuthor, tvPublisher, tvLanguage, tvPages;
        RatingBar ratingBar;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.viewTitle);
            tvAuthor = itemView.findViewById(R.id.viewAuthor);
            tvPublisher = itemView.findViewById(R.id.viewPublisher);
            tvLanguage = itemView.findViewById(R.id.viewLanguage);
            tvPages = itemView.findViewById(R.id.viewPages);
            ratingBar = itemView.findViewById(R.id.viewRating);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
