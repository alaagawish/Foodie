package eg.gov.iti.jets.foodie.signup.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
import eg.gov.iti.jets.foodie.model.Meal;


public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private Intent intent;
    private Button signUpButton;
    private TextView loginText;
    private EditText usernameEditTextSignUp;
    private EditText emailEditTextSignUp;
    private EditText passwordEditTextSignUp;
    private EditText confirmPasswordEditTextLogin;
    private String emailVal = "^(.+)@(.+)$";
    private String passwordVal = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,20}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        SharedPreferences sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://foodie-b0b13-default-rtdb.firebaseio.com/");

        signUpButton = findViewById(R.id.signUpButton);
        loginText = findViewById(R.id.loginTextView);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameEditTextSignUp.getText().toString().trim();
                String email = emailEditTextSignUp.getText().toString().trim();
                String pass = passwordEditTextSignUp.getText().toString().trim();
                String confirmPass = confirmPasswordEditTextLogin.getText().toString().trim();
                Pattern passwordPattern = Pattern.compile(passwordVal);
                Pattern emailPattern = Pattern.compile(emailVal);
                Matcher passwordMatcher = passwordPattern.matcher(pass);
                Matcher emailMatcher = emailPattern.matcher(email);
                if (!userName.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !confirmPass.isEmpty() && confirmPass.equals(pass)) {
                    if (emailMatcher.matches() && passwordMatcher.matches()) {
                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Meal meal = new Meal();
                                    assert meal != null;
                                    meal.setEmail(email);
                                    meal.setUserName(userName);
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                                    auth.getCurrentUser().updateProfile(profileUpdates);
                                    database.getReference().child("meal").child(email.replaceAll("[\\.#$\\[\\]]", "")).setValue(meal);
                                    SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(LoginActivity.PASSWORD, pass);
                                    editor.putString(LoginActivity.EMAIL, email);
                                    editor.commit();
                                    Toast.makeText(SignupActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignupActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else if (!emailMatcher.matches()) {
                        Toast.makeText(SignupActivity.this, "Your mail doesn't match email format", Toast.LENGTH_SHORT).show();

                    } else if (!passwordMatcher.matches()) {
                        Toast.makeText(SignupActivity.this, "Your password must have at least 8 digit contains at least an upper case, a lower case, a special character and a digit ,spaces don't allow.", Toast.LENGTH_LONG).show();

                    }
                } else {
                    if (userName.isEmpty() && email.isEmpty() && pass.isEmpty() && confirmPass.isEmpty()) {
                        usernameEditTextSignUp.setError("Username can not be empty!");
                        emailEditTextSignUp.setError("Email can not be empty!");
                        passwordEditTextSignUp.setError("Password can not be empty!");
                        confirmPasswordEditTextLogin.setError("Confirm password can not be empty!");
                    }
                    if (userName.isEmpty()) {
                        usernameEditTextSignUp.setError("UserName can not be empty!");
                    }
                    if (email.isEmpty()) {
                        emailEditTextSignUp.setError("Email can not be empty!");
                    }
                    if (pass.isEmpty()) {
                        passwordEditTextSignUp.setError("Password can not be empty!");
                    }
                    if (confirmPass.isEmpty()) {
                        confirmPasswordEditTextLogin.setError("Confirm password can not be empty!");
                    }
                    if (!confirmPass.equals(pass)) {
                        confirmPasswordEditTextLogin.setError("Password and Confirm password don't match!");
                    }
                }
            }
        });

        loginText.setOnClickListener(v -> {
            intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void init() {
        signUpButton = findViewById(R.id.signUpButton);
        loginText = findViewById(R.id.loginTextView);
        usernameEditTextSignUp = findViewById(R.id.usernameEditTextSignUp);
        emailEditTextSignUp = findViewById(R.id.emailEditTextSignUp);
        passwordEditTextSignUp = findViewById(R.id.passwordEditTextSignUp);
        confirmPasswordEditTextLogin = findViewById(R.id.confirmPasswordEditTextLogin);

    }

    public void back(View view) {
        finish();
    }
}