package com.example.alzbot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alzbot.service.NotificationHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddAlarmtoMeds extends AppCompatActivity {
    AddAlarmAdapter addAlarmAdapter;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference medicinesRef;
    List<Medicines> medicines=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarmto_meds);
        database = FirebaseDatabase.getInstance();
        recyclerView=findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicinesRef= database.getReference("medicines");
        medicines=new ArrayList<>();
        medicinesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Medicines>> t = new GenericTypeIndicator<List<Medicines>>() {};
                medicines=dataSnapshot.getValue(t);
                if(medicines==null)
                {
                    medicines=new ArrayList<>();
                }
                addAlarmAdapter=new AddAlarmAdapter(medicines);
                recyclerView.setAdapter(addAlarmAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Medicines> asd=addAlarmAdapter.getMedicinesList();
                for (Medicines m :
                        asd) {
                    String h=m.getTime().split(":")[0];
                    String min=m.getTime().split(":")[1];
                    NotificationHelper.scheduleRepeatingRTCNotification(v.getContext(), h, min);
                    NotificationHelper.enableBootReceiver(v.getContext());
                }
                medicinesRef.setValue(asd);
                AlzManager.initializeInstance(v.getContext());
            }
        });
    }
}
