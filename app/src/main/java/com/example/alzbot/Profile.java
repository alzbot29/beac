package com.example.alzbot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    List<People> list=new ArrayList<>();
    RecyclerView recyclerView;
    PeopleAdapter peopleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference();
        DatabaseReference databaseReference1=databaseReference.child("person");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String key=childDataSnapshot.getKey();
                    People people=childDataSnapshot.getValue(People.class);
                    list.add(people);
                    Log.v("key",key); //displays the key for the node

                }
                recyclerView=findViewById(R.id.recyclerView2);
                Log.e("onDataChange: ", String
                        .valueOf(list.get(0).getDownloadUrl()));

                peopleAdapter=new PeopleAdapter(list);
                recyclerView.setAdapter(peopleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,AddPerson.class));
            }
        });
    }
}
