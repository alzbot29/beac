package com.example.alzbot;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.Result;

public class AssistantActivity extends AppCompatActivity implements AIListener{

    private static final String TAG = "AssistantActivity";
    private AIService aiService;
    private TextView assistance_question_text, assistance_answer_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);

        assistance_question_text = (TextView)findViewById(R.id.assistance_question_text);
        assistance_answer_text = (TextView)findViewById(R.id.assistance_answer_text);


        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AssistantActivity.this,HomeActivity.class));
            }
        });

        final AIConfiguration config = new AIConfiguration("acbe80cae84249caa2b796604b6a44ed",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);

        aiService.setListener(this);

        ImageView mic = (ImageView)findViewById(R.id.mic_button);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiService.startListening();
            }
        });
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.RECORD_AUDIO},
                101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }


    @Override
    public void onResult(AIResponse result) {

        Log.d(TAG, "onResult: this is the result returned from dialogflow" + result.toString());
        Result result1 = result.getResult();
        Log.d(TAG, "onResult: this is the result returned from dialogflow" + result1.getFulfillment().getSpeech());

        assistance_question_text.setText(result1.getResolvedQuery());
        assistance_answer_text.setText(result1.getFulfillment().getSpeech());
        if(result1.getFulfillment().getSpeech().contains("njkdfsgl"))
        {
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=database.getReference();
            DatabaseReference patient =databaseReference.child("patient");
            patient.child("econtact").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    number=dataSnapshot.getValue().toString();
                    SmsManager.getDefault().sendTextMessage(number, null, "Your Family member is in danger!!", null, null);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
    String number="";

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
