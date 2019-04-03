package com.example.alzbot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SetupMeds extends AppCompatActivity {
    RecyclerView recyclerView;
    MedsAlarmAdapter medsAlarmAdapter;
    int SIZE=0;
    EditText name,qty;
    FirebaseDatabase database;
    DatabaseReference medicinesRef;
    List<Medicines> medicines=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_meds);

        database = FirebaseDatabase.getInstance();
        medicinesRef= database.getReference("medicines");
        medicines=new ArrayList<>();

        name=findViewById(R.id.name);
        qty=findViewById(R.id.number);
        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medsAlarmAdapter=new MedsAlarmAdapter(medicines);
        recyclerView.setAdapter(medsAlarmAdapter);

        medicinesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Medicines>> t = new GenericTypeIndicator<List<Medicines>>() {};
                medicines=dataSnapshot.getValue(t);
                if(medicines==null)
                {
                    medicines=new ArrayList<>();
                }
                medsAlarmAdapter=new MedsAlarmAdapter(medicines);
                recyclerView.setAdapter(medsAlarmAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()>0&&qty.getText().toString().length()>0){
                Medicines m=new Medicines(name.getText().toString(),qty.getText().toString(),"_");
                medicines.add(m);
                medsAlarmAdapter=new MedsAlarmAdapter(medicines);
                recyclerView.setLayoutManager(new LinearLayoutManager(SetupMeds.this));
                recyclerView.setAdapter(medsAlarmAdapter);
                }
                else {
                    Toast.makeText(SetupMeds.this, "Enter medicine name and quantity properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                medicinesRef.setValue(medsAlarmAdapter.getList()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SetupMeds.this, "Medicines Saved", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    private void setupalarm(){

    }
}
