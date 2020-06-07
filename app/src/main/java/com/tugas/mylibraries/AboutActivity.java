package com.tugas.mylibraries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tugas.mylibraries.object.Book;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    private Button btBackup, btRestore;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sqLiteHelper = new SQLiteHelper(getBaseContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("data");
        btBackup = findViewById(R.id.btnBackup);
        btRestore = findViewById(R.id.btnRestore);
        final List<Book> list = sqLiteHelper.getAllBookRecord();
        btBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (final Book book : list){
                    reference.child(String.valueOf(book.getId_Books())).setValue(book);
                }
            }
        });
        btRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Book book = snapshot.getValue(Book.class);
                            for (int i=0; i<list.size();i++){
                                if (list.get(i).getId_Books() != book.getId_Books()){
                                    sqLiteHelper.addBookRecord(book);
                                }else {
                                    sqLiteHelper.updateBookRecord(book);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
