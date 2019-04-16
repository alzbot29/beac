package com.example.alzbot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.alzbot.service.AlarmReceiver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
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
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
        final Intent myIntent = new Intent(this, AlarmReceiver.class);
        final Calendar calendar = Calendar.getInstance();
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Medicines> asd=addAlarmAdapter.getMedicinesList();
                for (Medicines m :
                        asd) {
                    String h=m.getTime().split(":")[0];
                    String min=m.getTime().split(":")[1];
                    calendar.add(Calendar.SECOND, 3);
                    //setAlarmText("You clicked a button");

                    final int hour = Integer.parseInt(h);
                    final int minute = Integer.parseInt(min);

                    Log.e("MyActivity", "In the receiver with " + hour + " and " + minute);


                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);

                    myIntent.putExtra("extra", "yes");
                    pending_intent = PendingIntent.getBroadcast(AddAlarmtoMeds.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                }
                medicinesRef.setValue(asd);
                AlzManager.initializeInstance(v.getContext());
            }
        });
    }
    private PendingIntent pending_intent;
    AlarmManager alarmManager;
}
