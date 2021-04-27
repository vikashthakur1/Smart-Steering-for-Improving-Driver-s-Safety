package com.example.mysteeringwheel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {
    TextView textView,forgetpass;
    EditText email,pass;
    Button loginbutton;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = (EditText) findViewById(R.id.et1);
        pass = (EditText) findViewById(R.id.et2);
        progressBar = (ProgressBar) findViewById(R.id.p1);
        textView = (TextView) findViewById(R.id.tv4);
        forgetpass=(TextView)findViewById(R.id.tv2);
        firebaseAuth = FirebaseAuth.getInstance();

        loginbutton = (Button) findViewById(R.id.button);



        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    email.setError("plese enter email");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("please enter valid email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    pass.setError("please enter password");
                    return;
                }
                if (Password.length() < 6) {
                    pass.setError("minimum six digit");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),SteeringActivity.class));
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                         }
                    }
                });
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ForgetActivity.class);
                startActivities(new Intent[]{i});
            }

        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SignActivity.class);
                startActivities(new Intent[]{i});
            }

        });


    }
}
