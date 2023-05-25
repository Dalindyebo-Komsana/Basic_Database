package com.example.basicfirebasedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.basicfirebasedb.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
String username, password;
FirebaseDatabase db;
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = binding.txtUsername.getText().toString();
                password = binding.txtPassword.getText().toString();

                if(!username.isEmpty() && !password.isEmpty())
                {
                    User user = new User(username, password);
                    db = FirebaseDatabase.getInstance();
                    ref = db.getReference("User");
                    ref.child(username).setValue("User").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.txtUsername.setText("");
                            binding.txtPassword.setText("");
                            Toast.makeText(MainActivity.this, "Successful",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}