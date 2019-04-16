package com.example.alzbot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddPerson extends AppCompatActivity {

    private EditText livesIn, age, place, time, relation, notes, naam;
    private Button submit;
    private String naamm,livee,agee,placee,timee,relationn,notess,temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        naam = (EditText)findViewById(R.id.nishant);
        livesIn = (EditText)findViewById(R.id.livesIn);
        age = (EditText)findViewById(R.id.age);
        place = (EditText)findViewById(R.id.PlaceOfMeeting);
        time = (EditText)findViewById(R.id.Time);
        relation = (EditText)findViewById(R.id.Relation);
        notes = (EditText)findViewById(R.id.Notes);
        submit = (Button)findViewById(R.id.submit);


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        // textView is the TextView view that should display it

        time.setText(currentDateTimeString);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                naamm = naam.getText().toString();
                livee = livesIn.getText().toString();
                agee = age.getText().toString();
                placee = place.getText().toString();
                timee = time.getText().toString();
                relationn = relation.getText().toString();
                notess = notes.getText().toString();
                People people=new People(naamm,livee,agee,placee,timee,relationn,notess);
                FirebaseDatabase database =FirebaseDatabase.getInstance();
                DatabaseReference databaseReference=database.getReference();
                DatabaseReference databaseReference1=databaseReference.child("person");
                databaseReference1.push().setValue(people).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddPerson.this, naamm+" added to personnel contacts", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });

            }
        });









    }
}
