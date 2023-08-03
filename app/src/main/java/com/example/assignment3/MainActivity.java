package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.example.assignment3.databinding.ActivityMainBinding;
import com.example.assignment3.databinding.HomeActivityBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private HomeActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = HomeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();

        binding.signUp.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);
        });

        binding.loginButton.setOnClickListener(view12 -> {
            if (loginCheck()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(binding.emailInput.getText().toString(), binding.passwordInput.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            String msg = "Login Successful";
                            toastMsg(msg);
                            startActivity(new Intent(MainActivity.this,
                                    HomeActivity.class));
                        }).addOnFailureListener(e -> {
                            String msg = "User name or password is invalid, try again!";
                            toastMsg(msg);
                        });
            }
        });

    }

    private void toastMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private boolean loginCheck() {
        boolean validEmail = false;
        boolean validPassword = false;
        Editable emailInput = binding.emailInput.getText();
        Editable passwordInput = binding.passwordInput.getText();
        if (emailInput.toString().isEmpty()) {
            binding.emailError.setError(getResources().getString(R.string.email_error));
        } else {
            binding.emailError.setErrorEnabled(false);
            validEmail = true;
        }
        if (passwordInput.toString().isEmpty()) {
            binding.passwordError.setError(getResources().getString(R.string.password_empty_error));
        } else {
            binding.passwordError.setErrorEnabled(false);
            validPassword = true;
        }
        return validEmail && validPassword;
    }
}