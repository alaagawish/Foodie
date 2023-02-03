package eg.gov.iti.jets.foodie.signup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;


public class SignupActivity extends AppCompatActivity {

    Intent intent;
    Button signUpButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpButton = findViewById(R.id.signUpButton);
        loginText = findViewById(R.id.loginTextView);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loginText.setOnClickListener( v -> {
            intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}