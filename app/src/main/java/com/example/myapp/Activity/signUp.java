package com.example.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signUp extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapp-a1d37-default-rtdb.firebaseio.com/");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final EditText name=(EditText) findViewById(R.id.signUp_name);
        final EditText phone=(EditText) findViewById(R.id.signUp_phone);

        final EditText email=(EditText)findViewById(R.id.signUp_email);
        final EditText password=(EditText)findViewById(R.id.signUp_password);

        Button signup=(Button) findViewById(R.id.signUp_button);
        TextView loginRedirect=(TextView) findViewById(R.id.loginRedirectText);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nametxt=name.getText().toString();
                final String phonetxt=phone.getText().toString();
                final String emailtxt=email.getText().toString();
                final  String passwordtxt=password.getText().toString();

                if(nametxt.isEmpty())
                {
                    name.setError("Enter the name");
                    name.requestFocus();
                    //Toast.makeText(signUp.this,"Enter the Name ",Toast.LENGTH_SHORT).show();

                }else if (phonetxt.isEmpty() ) {
                    phone.setError("Enter the Phone Number");
                    phone.requestFocus();
                    //Toast.makeText(signUp.this,"Enter the Phone Number",Toast.LENGTH_SHORT).show();

                } else if (phonetxt.length()<10|| phonetxt.length()>10) {
                    phone.setError("Enter the valid Phone Number");
                    phone.requestFocus();
                    //Toast.makeText(signUp.this,"Enter valid Phone Number",Toast.LENGTH_SHORT).show();

                }
                else if (emailtxt.isEmpty()) {
                    email.setError("Enter the email Id");
                    email.requestFocus();
                    //Toast.makeText(signUp.this,"Enter the Email",Toast.LENGTH_SHORT).show();

                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                    email.setError("Enter the valid email Id");
                    email.requestFocus();
                    //Toast.makeText(signUp.this,"Enter the Email",Toast.LENGTH_SHORT).show();

                }

                else if (passwordtxt.isEmpty()) {
                    password.setError("enter the Password");
                    password.requestFocus();
                    //Toast.makeText(signUp.this,"Enter the Password",Toast.LENGTH_SHORT).show();

                }else if (!(password.getText().toString().length() >= 5)) {
                    password.setError(" Password must be atleast 5 character long");
                    password.requestFocus();

                }
                else if (!passwordtxt.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$")) {
                    password.setError("Password must contain at least one uppercase letter and one special character");
                    password.requestFocus();
                }

                else
                {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonetxt))
                            {
                                Toast.makeText(signUp.this,"Phone Number is Alread Registered",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("users").child(phonetxt).child("Name").setValue(nametxt);
                                databaseReference.child("users").child(phonetxt).child("Email").setValue(emailtxt);
                                databaseReference.child("users").child(phonetxt).child("Password").setValue(passwordtxt);

                                Toast.makeText(signUp.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signUp.this, loginIn.class ));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }


            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUp.this,loginIn.class ));
            }
        });




    }


}