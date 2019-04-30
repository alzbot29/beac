package com.example.alzbot;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class SetupDetails extends AppCompatActivity {

    EditText name,address,drname,drmobile,ename,econtact,eaddress;
    Button dob,save;
    String dobstr="";
    private DatePickerDialog datePickerDialog;
    Calendar dateSelected = Calendar.getInstance();
    SimpleDateFormat dateFormatter;
    Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_details);
        initui();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();
        reference.child("patient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient patient=dataSnapshot.getValue(Patient.class);
                try {
                    name.setText(patient.getName());
                    address.setText(patient.getAddress());
                    drname.setText(patient.getDrname());
                    drmobile.setText(patient.getDrmobile());
                    ename.setText(patient.getEname());
                    econtact.setText(patient.getEcontact());
                    eaddress.setText(patient.getEaddress());
                    save.setText(patient.getSave());
                    dob.setText(patient.getDob());
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dateFormatter=new SimpleDateFormat("yyyy-MM-dd");

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTimeField() ;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient=new Patient();
                patient.setName(name.getText().toString());
                patient.setAddress(address.getText().toString());
                patient.setDrname(drname.getText().toString());
                patient.setDrmobile(drmobile.getText().toString());
                patient.setEname(ename.getText().toString());
                patient.setEcontact(econtact.getText().toString());
                patient.setEaddress(eaddress.getText().toString());
                patient.setDob(dobstr);
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference reference=database.getReference();
                reference.child("patient").setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SetupDetails.this, "Patient Detials Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void initui(){
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        drname=findViewById(R.id.drname);
        drmobile=findViewById(R.id.drmobile);
        ename=findViewById(R.id.ename);
        econtact=findViewById(R.id.econtact);
        eaddress=findViewById(R.id.eaddress);
        save=findViewById(R.id.save);
        dob=findViewById(R.id.dob);

    }
    private void setDateTimeField() {
        Calendar newCalendar = dateSelected;
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
                dob.setText("DOB : "+dateFormatter.format(dateSelected.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        dobstr=dateFormatter.format(dateSelected.getTime());

    }
}
