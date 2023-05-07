package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.example.assignment3.databinding.ActivityMainBinding;
import com.example.assignment3.databinding.HomeActivityBinding;
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

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck()) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(binding.emailInput.getText().toString(), binding.passwordInput.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    String msg = "Login Successful";
                                    toastMsg(msg);
                                    startActivity(new Intent(MainActivity.this,
                                            HomeActivity.class));
                                }
                            });
                }
            }
        });





////        setContentView(R.layout.activity_main);
////FirebaseApp.initializeApp(this);
//        auth = FirebaseAuth.getInstance();
//        EditText emailEditText = findViewById(R.id.emailEditText);
//        EditText passwordEditText = findViewById(R.id.passwordEditText);
//        Button registerButton =findViewById(R.id.signupButton);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//                                              @Override
//                                              public void onClick(View v) {
//                                                  startActivity(new Intent(MainActivity.this,
//                                                          SignupActivity.class));
//                                              }
//                                          }
//        );
//
//        Button loginButton =findViewById(R.id.signinButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String txt_Email = emailEditText.getText().toString();
//                String txt_Pwd = passwordEditText.getText().toString();
//                loginUser(txt_Email,txt_Pwd);
//            }
//        });
//    }
//    private void loginUser(String txt_email, String txt_pwd) {
//// call the object and provide it with email id and password
//        auth.signInWithEmailAndPassword(txt_email,
//                txt_pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                String msg = "Login Successful";
//                toastMsg(msg);
//                startActivity(new Intent(MainActivity.this,
//                        HomeActivity.class));
//            }
//        });
//    }
//    public void toastMsg(String message){
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void toastMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private boolean loginCheck() {
        Boolean validEmail = false;
        Boolean validPassword = false;
        Editable emailInput = binding.emailInput.getText();
        Editable passwordInput = binding.passwordInput.getText();
        if (emailInput.toString().isEmpty()) {
            binding.emailError.setError(getResources().getString(R.string.email_error));
            validEmail = false;
        } else {
            binding.emailError.setErrorEnabled(false);
            validEmail = true;
        }
        if (passwordInput.toString().isEmpty()) {
            binding.passwordError.setError(getResources().getString(R.string.password_empty_error));
            validPassword = false;
        } else {
            binding.passwordError.setErrorEnabled(false);
            validPassword = true;
        }
        if (validEmail && validPassword) {
            return true;
        } else {
            return false;
        }
    }
}