package com.example.assignment3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.databinding.SignupBinding;
import com.example.assignment3.entity.User;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    private SignupBinding binding;

    private Button dateButton;
    private DatePickerDialog datePickerDialog;
    private String date;
    private String todayDate;
    private Date datePicker;
    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        List<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");
        list.add("Gender");
        final int listsize = list.size() -1;

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list){
            @Override
            public int getCount(){
                return(listsize);
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.genderInput.setAdapter(spinnerAdapter);
        binding.genderInput.setSelection(listsize);

        binding.backButton.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, MainActivity.class)));

        binding.submitButton.setOnClickListener(v -> submitCheck());

        initDatePicker();
        dateButton = findViewById(R.id.date_picker);
        dateButton.setText(todayDate());
    }

    private void submitCheck() {

        boolean validEmail;
        String emailInput = Objects.requireNonNull(binding.emailInput.getText()).toString();
        // Empty email input
        if(emailInput.isEmpty()){
            binding.emailError.setError(getResources().getString(R.string.email_error));
            validEmail = false;
        }
        // Wrong format email input
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            binding.emailError.setError(getResources().getString(R.string.email_error));
            validEmail = false;
        }
        // Correct email input
        else{
            binding.emailError.setErrorEnabled(false);
            validEmail = true;
        }

        boolean validPassword;
        String passwordInput = binding.passwordInput.getText().toString();
        String repeatPasswordInput = binding.repeatPasswordInput.getText().toString();

        // Empty password input
        if(passwordInput.isEmpty()){
            binding.passwordError.setError(getResources().getString(R.string.password_empty_error));
        }
        // Password length error
        else if(passwordInput.length() < 6){
            binding.passwordError.setError(getResources().getString(R.string.password_length_error));
        }
        // Whitespace error
        else if(passwordInput.contains(" ")){
            binding.passwordError.setError(getResources().getString(R.string.password_space_error));
        }
        else {
            binding.passwordError.setErrorEnabled(false);
        }

        // Check whether two passwords are the same
        if(!repeatPasswordInput.equals(passwordInput)){
            binding.repeatPasswordError.setError(getResources().getString(R.string.repeat_password_error));
            validPassword = false;
        }
        else {
            binding.repeatPasswordError.setErrorEnabled(false);
            validPassword = true;
        }

        boolean validUsername;
        String usernameInput = binding.usernameInput.getText().toString();
        // Empty username input
        if(usernameInput.isEmpty()){
            binding.usernameError.setError(getResources().getString(R.string.username_empty_error));
            validUsername = false;
        }
        else{
            binding.usernameError.setErrorEnabled(false);
            validUsername = true;
        }

        boolean validGender;
        String genderInput = binding.genderInput.getSelectedItem().toString();
        // Check whether choose a gender
        if(genderInput.equals("Gender")){
            TextView errorText = (TextView) binding.genderInput.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText(getResources().getString(R.string.gender_error));
            validGender = false;
        }
        else{
            validGender = true;
        }

        boolean validBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        try {
            datePicker = sdf.parse(dateButton.getText().toString());
            currentDate = sdf.parse(todayDate);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if (dateButton.getText().toString().equals(todayDate)){
            binding.datePickerError.setText(getResources().getString(R.string.birthday_empty_error));
            binding.datePickerError.setVisibility(View.VISIBLE);
            validBirthday = false;
        }else if (datePicker.after(currentDate)){
            binding.datePickerError.setText(getResources().getString(R.string.birthday_after_error));
            binding.datePickerError.setVisibility(View.VISIBLE);
            validBirthday = false;
        }else{
            binding.datePickerError.setVisibility((View.INVISIBLE));
            validBirthday = true;
        }

        if (validEmail && validBirthday && validGender && validUsername && validPassword) {
            binding.progressBar.setVisibility(View.VISIBLE);
            User user_info = new User(emailInput, usernameInput, genderInput, dateButton.getText().toString());
            auth.createUserWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignupActivity.this, "Congratulations! Register successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Register failed." +
                                            "\nEmail might has been registered already.",
                                    Toast.LENGTH_LONG).show();
                            binding.progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
        }
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            month = month + 1;
            // update local variable string date
            date = makeDateString(day, month, year);
            // set the text for birthday button from date picker
            dateButton.setText(date);
        };

        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        // initiate date picker
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month ,day);
    }

    private String todayDate(){
        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        todayDate = day+ " "+ getMonthFormat(month) +" "+ year;
        return makeDateString(day,month,year);
    }

    private String getMonthFormat(int month){
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month == 12)
            return "Dec";

        return "Jan";
    }

    private String makeDateString(int day, int month, int year){
        return day+ " "+ getMonthFormat(month) +" "+ year;
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

}
