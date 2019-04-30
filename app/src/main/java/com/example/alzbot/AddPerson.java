package com.example.alzbot;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddPerson extends AppCompatActivity {

    private EditText livesIn, age, place, time, relation, notes, naam;
    private Button submit;
    private String naamm,livee,agee,placee,timee,relationn,notess,temp;
    private ImageView image;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        naam = (EditText)findViewById(R.id.nishant);
        livesIn = (EditText)findViewById(R.id.livesIn);
        age = (EditText)findViewById(R.id.age);
        place = (EditText)findViewById(R.id.PlaceOfMeeting);
        time = (EditText)findViewById(R.id.Time);
        relation = (EditText)findViewById(R.id.Relation);
        notes = (EditText)findViewById(R.id.Notes);
        submit = (Button)findViewById(R.id.submit);
        image = findViewById(R.id.image);


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        // textView is the TextView view that should display it

        time.setText(currentDateTimeString);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto(v);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final StorageReference riversRef = mStorageRef.child(naam.getText().toString()+".jpg");
                File f = new File(v.getContext().getCacheDir(), "Pic");
                try {
                    f.createNewFile();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();

                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
                } catch (Exception e) {

                    final ProgressDialog ss=new ProgressDialog(v.getContext());
                    ss.setTitle("Uploading..");
                    ss.setCancelable(false);
                    ss.show();
                    naamm = naam.getText().toString();
                    livee = livesIn.getText().toString();
                    agee = age.getText().toString();
                    placee = place.getText().toString();
                    timee = time.getText().toString();
                    relationn = relation.getText().toString();
                    notess = notes.getText().toString();
                    People people=new People(naamm,livee,agee,placee,timee,relationn,notess,"");
                    FirebaseDatabase database =FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=database.getReference();
                    DatabaseReference databaseReference1=databaseReference.child("person");
                    databaseReference1.push().setValue(people).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(AddPerson.this, naamm+" added to personnel contacts", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            ss.dismiss();
                        }
                    });
                }
                final ProgressDialog ss=new ProgressDialog(v.getContext());
                ss.setTitle("Uploading..");
                ss.setCancelable(false);
                ss.show();
                riversRef.putFile(Uri.fromFile(f))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                               riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri downloadUrl) {
                                        Log.e("onSuccess: ",downloadUrl.toString() );
                                        naamm = naam.getText().toString();
                                        livee = livesIn.getText().toString();
                                        agee = age.getText().toString();
                                        placee = place.getText().toString();
                                        timee = time.getText().toString();
                                        relationn = relation.getText().toString();
                                        notess = notes.getText().toString();
                                        People people=new People(naamm,livee,agee,placee,timee,relationn,notess,downloadUrl.toString());
                                        FirebaseDatabase database =FirebaseDatabase.getInstance();
                                        DatabaseReference databaseReference=database.getReference();
                                        DatabaseReference databaseReference1=databaseReference.child("person");
                                        databaseReference1.push().setValue(people).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(AddPerson.this, naamm+" added to personnel contacts", Toast.LENGTH_SHORT).show();
                                                onBackPressed();
                                                ss.dismiss();
                                            }
                                        });
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                                ss.dismiss();
                            }
                        });

            }
        });
    }
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
    Bitmap bitmap;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        image.setImageBitmap(bitmap);
                        image.setContentDescription(selectedImage.toString());
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("x208a", e.getLocalizedMessage());
                    }
                }
        }
    }
}
