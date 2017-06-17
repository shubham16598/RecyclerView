package com.example.shubham16598.fireapp;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference mdatabase;
    Button b1;
    EditText e1;
    EditText e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1= (Button)findViewById(R.id.addString);
        e1= (EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString().trim();
                String email = e2.getText().toString().trim();
                mdatabase = FirebaseDatabase.getInstance().getReference();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Email",email);

                mdatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Sucessful",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"Unsuccesful",Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }
        });

    }
}
