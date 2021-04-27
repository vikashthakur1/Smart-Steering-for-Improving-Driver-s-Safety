package com.example.mysteeringwheel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignActivity extends AppCompatActivity {
    EditText name,email,pass,repass;
    Button Signbutton;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        name=(EditText)findViewById(R.id.ed1);
        email=(EditText)findViewById(R.id.ed2);
        pass=(EditText)findViewById(R.id.ed3);
        repass=(EditText)findViewById(R.id.ed4);

        Signbutton=(Button)findViewById(R.id.b1);
        progressBar=(ProgressBar)findViewById(R.id.p1);
        firebaseAuth=FirebaseAuth.getInstance();

        Signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString().trim();
                String Email=email.getText().toString().trim();
                String Password=pass.getText().toString().trim();
                String Repassword=repass.getText().toString().trim();

                if(TextUtils.isEmpty(Name)){
                    name.setError("please enter name");
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    email.setError("please enter email ID");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("please enter valid email");
                    email.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    pass.setError("please enter passord");
                    return;
                }
                if(Password.length()<6){
                    pass.setError("please enter minimum six digit");
                    return;
                }
                if(TextUtils.isEmpty(Repassword)){
                    repass.setError("please enter RePassword");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                 if(Password.equals(Repassword)) {
                     firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 Toast.makeText(SignActivity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(getApplicationContext(), MainActivity.class));
                             } else {
                                 Toast.makeText(SignActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                 progressBar.setVisibility(View.GONE);
                             }

                         }
                     });
                 }

            }
        });
    }
}
