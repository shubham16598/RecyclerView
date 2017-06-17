package com.example.shubham16598.fireapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference mdatabase;
    Button b1;
    EditText e1;
    EditText e2;
    TextView t1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1= (Button)findViewById(R.id.addString);
        b2= (Button)findViewById(R.id.button3);
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

        //Retrieving Data from firebase...

      /*  mdatabase = FirebaseDatabase.getInstance().getReference().child("Name");
        t1 = (TextView)findViewById(R.id.name_view);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                t1.setText("Name :"+ name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */
        //ye first method h data retrieve krne ka kisi child ka...for eg..here child name is "Name"


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

    }
}
