package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foody.common.Common;
import com.example.foody.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class LogIn extends AppCompatActivity {

    EditText editUserPhone, editPassword;
    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editUserPhone = (EditText) findViewById(R.id.editUserPhone);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(LogIn.this);
                mDialog.setMessage("Please Waiting");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Checking for user existence
                        if (snapshot.child(editUserPhone.getText().toString()).exists()) {

                            //Getting user information
                            mDialog.dismiss();

                            User user = snapshot.child(editUserPhone.getText().toString()).getValue(User.class);


                            if (user.getPassword().equals(editPassword.getText().toString())) {
                                mDialog.dismiss();

                                Intent homeIntent = new Intent(LogIn.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                            }

                        }else{

                            mDialog.dismiss();
                            Toast.makeText(LogIn.this, "No User", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });


            }

        });
        ;
    }
}