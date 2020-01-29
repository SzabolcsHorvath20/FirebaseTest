package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String teszt;

    private TextView display;

    //private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        database = FirebaseDatabase.getInstance();
        //ref = database.getReference().child("tesztadatok").child("tesztadat1").child("adatString");

        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teszt = (String)dataSnapshot.getValue();
                display.setText(teszt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        /*ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teszt = (String)dataSnapshot.getValue();
                display.setText(teszt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        ref = database.getReference().child("tesztadatok");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adatKiiras((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestClass test = new TestClass("tesztadatString", 404);
                myRef.setValue(test);
            }
        });*/
    }

    private void adatKiiras(Map<String, Object> adatok){
        ArrayList<TestClass> adatLista = new ArrayList<>();
        for (Map.Entry<String, Object> entry : adatok.entrySet()) {
            Map adat = (Map) entry.getValue();
            adatLista.add(new TestClass((String) adat.get("adatString"), (long) adat.get("adatNumber")));
        }
        StringBuilder builder = new StringBuilder();
        for (TestClass adat : adatLista) {
            builder.append(adat + "\n");
        }
        display.setText(builder.toString());
    }
}
