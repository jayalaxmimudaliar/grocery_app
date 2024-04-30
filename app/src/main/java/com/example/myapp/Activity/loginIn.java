package com.example.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Objects;

public class loginIn extends AppCompatActivity {
    public static String PREFS_NAME="MyPreFiles";
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapp-a1d37-default-rtdb.firebaseio.com/");
    public static String newPref="newMyPref";




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        final EditText phone=(EditText) findViewById(R.id.login_phone);
        final EditText password=(EditText) findViewById(R.id.login_password);
        final EditText email=(EditText) findViewById(R.id.login_email);
        Button loginButton=(Button) findViewById(R.id.login_button);
        TextView signUpRedirect =(TextView) findViewById(R.id.signUpRedirectText);
        TextView forgetRedirect =(TextView) findViewById(R.id.forgetRedirectText);
        SharedPreferences sharedPreferences=getSharedPreferences(loginIn.PREFS_NAME,0);









        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonetxt=phone.getText().toString();
                String passwordtxt=password.getText().toString();
                String emailtxt=email.getText().toString();





                SharedPreferences.Editor editor=sharedPreferences.edit();


                Log.i("called",sharedPreferences.getString("PhoneNumber",""));
                //   SharedPreferences.Editor editor=sharedPreferences.edit();
                if(sharedPreferences.getString("PhoneNumber","")==phonetxt){
                    editor.putBoolean("phone",false);
                    editor.commit();
                    Log.i("if called","called");

                }else {
                    editor.clear();
                    editor.putBoolean("phone",true);
                    editor.commit();

                    Log.i("else called","called");
                }

                editor.putString("PhoneNumber",phonetxt);
                //editor.putBoolean("phone",true);
                //editor.putString("period","True");
                editor.putString("Email",emailtxt);
                Log.i("number",phonetxt);

                editor.commit();

                // editor.putBoolean("hasLoggedIn",true);
                editor.commit();


                if (phonetxt.isEmpty()) {
                    phone.setError("Enter the Phone Number");
                    phone.requestFocus();
                    // Toast.makeText(loginIn.this,"Enter the Phone Number",Toast.LENGTH_SHORT).show();

                }
                else if (!isValidPhoneNumber(phonetxt)) {
                    phone.setError("Enter a valid Phone Number");
                    phone.requestFocus();
                }
                else if (phonetxt.length()<10|| phonetxt.length()>10) {
                    phone.setError("Enter the valid Phone Number");
                    phone.requestFocus();
                    // Toast.makeText(loginIn.this,"Enter valid Phone Number",Toast.LENGTH_SHORT).show();

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

                    //Toast.makeText(loginIn.this,"Enter the Password",Toast.LENGTH_SHORT).show();

                }
                else if (!(password.getText().toString().length() >= 5)) {
                    password.setError(" Password must be atleast 5 character long");
                    password.requestFocus();

                }
                else
                {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {

                                if (!(snapshot.child("phonetxt").exists()))
                                {

                                    startActivity(new Intent(loginIn.this, loginIn.class));
                                    email.setText("");
                                    password.setText("");
                                    phone.setText("");
                                    // Toast.makeText(loginIn.this,"Invalid Login",Toast.LENGTH_SHORT).show();

                                }
                                final String getpassword=snapshot.child(phonetxt).child("Password").getValue(String.class);
                                final String getemail=snapshot.child(phonetxt).child("Email").getValue(String.class);
                                Log.i("password",getpassword);
                                Log.i("email",getemail);




                                if(Objects.equals(getpassword,passwordtxt) && Objects.equals(getemail,emailtxt))
                                {
                                    Toast.makeText(loginIn.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(loginIn.this, MainActivity.class));
                                    finish();

                                    SharedPreferences sharedPreferences11=getSharedPreferences(loginIn.newPref,0);
                                    SharedPreferences.Editor editSPlash =sharedPreferences11.edit();


                                    editSPlash.putBoolean("hasLoggedIn",true);
                                    editSPlash.commit();

                                }
                                else
                                {
                                    Toast.makeText(loginIn.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    password.setText("");
                                    phone.setText("");
                                }
                            }
                            else
                            {
                                Toast.makeText(loginIn.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                email.setText("");
                                password.setText("");
                                phone.setText("");

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });






        forgetRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(loginIn.this, forgetPassword.class));

            }
        });

        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginIn.this,signUp.class ));
            }
        });
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        // Define a regular expression for a 10-digit phone number
        String phoneRegex = "^[6789]\\d{9}$";

        // Check if the provided phone number matches the regex
        return phoneNumber.matches(phoneRegex);
    }





}