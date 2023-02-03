package eg.gov.iti.jets.foodie.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.signup.view.SignupActivity;


public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView skipText;
    TextView signUpText;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        skipText = findViewById(R.id.skipTextView);
        signUpText = findViewById(R.id.signUpTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        skipText.setOnClickListener( v -> {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        signUpText.setOnClickListener( v -> {
            intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}