package eg.gov.iti.jets.foodie.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.usersSignInGoogle;
import eg.gov.iti.jets.foodie.signup.view.SignupActivity;


public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView skipText;
    TextView signUpText;
    Intent intent;
    ImageButton googleButton;

    private GoogleSignInClient client, gsc;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        skipText = findViewById(R.id.skipTextView);
        signUpText = findViewById(R.id.signUpTextView);
        googleButton = findViewById(R.id.googleButton);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://foodie-b0b13-default-rtdb.firebaseio.com/");

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }

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
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = auth.getCurrentUser();
                            usersSignInGoogle usersSignInGoogle = new usersSignInGoogle();
                            assert usersSignInGoogle != null;
                            usersSignInGoogle.setEmail(user.getEmail());
                            usersSignInGoogle.setUserName(user.getDisplayName());
                            database.getReference().child("usersSignInGoogle").child(user.getDisplayName()).setValue(usersSignInGoogle);
                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}