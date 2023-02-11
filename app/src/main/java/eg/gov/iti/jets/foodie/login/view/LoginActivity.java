package eg.gov.iti.jets.foodie.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.usersSignInGoogle;
import eg.gov.iti.jets.foodie.signup.view.SignupActivity;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public static final String PREF = "PREF";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    private Button loginButton;
    private TextView skipText;
    private TextView signUpText;
    private Intent intent;
    private EditText emailEditTextLogin, passwordEditTextLogin;
    private ImageButton googleButton;

    private GoogleSignInClient client, gsc;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private GoogleSignInOptions gso;
    private String emailVal = "^(.+)@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        SharedPreferences sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        if (!sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals( "NOT FOUND")) {
            finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
//        messageTextView.setText(sharedPreferences1.getString(MainActivity.MESSAGE, "NOT FOUND"));
//        phoneTextView.setText(sharedPreferences1.getString(MainActivity.PHONE, "NOT FOUND"));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditTextLogin.getText().toString().trim();
                String password = passwordEditTextLogin.getText().toString().trim();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString(LoginActivity.USERNAME, messageTextView.getText().toString());
                                        editor.putString(LoginActivity.PASSWORD, passwordEditTextLogin.getText().toString().trim());
                                        editor.putString(LoginActivity.EMAIL, emailEditTextLogin.getText().toString().trim());
                                        editor.commit();
                                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        passwordEditTextLogin.setError("Password can't be empty");
                    }
                } else if (email.isEmpty()) {
                    emailEditTextLogin.setError("Email can't be empty");
                } else {
                    emailEditTextLogin.setError("Please enter valid mail");
                }

            }
        });

        skipText.setOnClickListener(v -> {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        signUpText.setOnClickListener(v -> {
            intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i, 123);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Meal meal = new Meal();
                            assert meal != null;
                            meal.setEmail(user.getEmail());
                            meal.setPassword("1234");
                            meal.setUserName(user.getDisplayName());
                            database.getReference().child("meal").child(user.getDisplayName()).setValue(meal);

                            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString(LoginActivity.USERNAME, messageTextView.getText().toString());
                            editor.putString(LoginActivity.PASSWORD, meal.getPassword());
                            editor.putString(LoginActivity.EMAIL, user.getEmail());
                            editor.commit();
                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        loginButton = findViewById(R.id.loginButton);
        skipText = findViewById(R.id.skipTextView);
        signUpText = findViewById(R.id.signUpTextView);
        googleButton = findViewById(R.id.googleButton);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://foodie-b0b13-default-rtdb.firebaseio.com/");
        passwordEditTextLogin = findViewById(R.id.passwordEditTextLogin);
        emailEditTextLogin = findViewById(R.id.emailEditTextLogin);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

    }

    public void back(View view) {
        finish();
    }
}