package com.example.alzbot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 0;

    private static final String TAG = "HomeActivity";
    private Button capture,peopleIKnow;
    private Bitmap photo;
    private String temp;
    private ByteArrayOutputStream bao;
    FirebaseDatabase database;
    DatabaseReference databaseReference,userPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

         database =FirebaseDatabase.getInstance();
         databaseReference=database.getReference();

        FloatingActionButton assist = (FloatingActionButton)findViewById(R.id.assistant);

        assist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AssistantActivity.class));
            }
        });



        capture = findViewById(R.id.capture);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Profile.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dashboard:
                startActivity(new Intent(HomeActivity.this,DashboardActivity.class));
                return true;
                default:return true;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");


            FirebaseDatabase database =FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=database.getReference();
            DatabaseReference userPhoto=databaseReference.child("userPhoto");

            photo = (Bitmap) data.getExtras().get("data");
            Drawable d = new BitmapDrawable(getResources(), photo);
        }
    }
}
