package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class SignUp extends AppCompatActivity {

    EditText editUserName, editUserPhone, editPassword;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUserName = (EditText) findViewById(R.id.editUserName);
        editUserPhone = (EditText) findViewById(R.id.editUserPhone) ;
        editPassword = (EditText) findViewById(R.id.editPassword);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog Dialog = new ProgressDialog(SignUp.this);
                Dialog.setMessage("Please Waiting");
                Dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.child(editUserPhone.getText().toString()).exists()){

                            Dialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone number is already registered", Toast.LENGTH_SHORT).show();
                        }else{
                            Dialog.dismiss();
                            User user = new User(editUserName.getText().toString(),editPassword.getText().toString());
                            table_user.child(editUserPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}