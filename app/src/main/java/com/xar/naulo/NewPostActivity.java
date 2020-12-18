package com.xar.naulo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPostActivity extends AppCompatActivity {
    private EditText mEditUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference firebaseReference = database.getReference("/postUrl").child("posts");


        Button mPostButton = findViewById(R.id.post_button);
        mEditUrl = findViewById(R.id.edit_url);

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditUrl.getText().toString();
                firebaseReference.setValue(text);
                finish();
                System.exit(0);
            }
        });
    }
}