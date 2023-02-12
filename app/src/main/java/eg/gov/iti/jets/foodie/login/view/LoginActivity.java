package eg.gov.iti.jets.foodie.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.login.presenter.LoginPresenter;
import eg.gov.iti.jets.foodie.login.presenter.LoginPresenterInterface;
import eg.gov.iti.jets.foodie.meals.presenter.MealsPresenter;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;
import eg.gov.iti.jets.foodie.signup.view.SignupActivity;


//public class LoginActivity extends AppCompatActivity implements LoginViewInterface {
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public static final String PREF = "PREF";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    private LoginPresenterInterface loginPresenterInterface;
    private Button loginButton;
    private TextView skipText, signUpText;
    private Intent intent;
    private EditText emailEditTextLogin, passwordEditTextLogin;
    private ImageButton googleButton;
    private GoogleSignInClient client, gsc;
    public static List<Meal> favMeals, plannedMeals;
    private FirebaseAuth auth;
    private Meal meall;
    private FirebaseDatabase firebaseDatabase;
    private GoogleSignInOptions gso;
    private DatabaseReference databaseReference;
    private String emailVal = "^(.+)@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        SharedPreferences sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        if (!sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND")) {
            Log.d(TAG, "onCreate: " + sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND"));
            retriveDataToDB(sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").replaceAll("[\\.#$\\[\\]]", ""));
            finish();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            retriveDataToDB(account.getEmail().replaceAll("[\\.#$\\[\\]]", ""));
            finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }


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
                                        editor.putString(LoginActivity.PASSWORD, passwordEditTextLogin.getText().toString().trim());
                                        editor.putString(LoginActivity.EMAIL, email);
                                        editor.commit();
                                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                        FirebaseUser firebaseUser = auth.getCurrentUser();

                                        retriveDataToDB(sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").replaceAll("[\\.#$\\[\\]]", ""));


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
                            meal.setUserName(user.getDisplayName());
                            firebaseDatabase.getReference().child("meal").child(user.getEmail().replaceAll("[\\.#$\\[\\]]", "")).setValue(meal);

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
        favMeals = new ArrayList<>();
        plannedMeals = new ArrayList<>();
//        loginPresenterInterface = new LoginPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(this), this));
        loginButton = findViewById(R.id.loginButton);
        skipText = findViewById(R.id.skipTextView);
        signUpText = findViewById(R.id.signUpTextView);
        googleButton = findViewById(R.id.googleButton);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://foodie-b0b13-default-rtdb.firebaseio.com/");
        passwordEditTextLogin = findViewById(R.id.passwordEditTextLogin);
        emailEditTextLogin = findViewById(R.id.emailEditTextLogin);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();


    }

    public void back(View view) {
        finish();
    }

    public void retriveDataToDB(String name) {
        Log.d(TAG, "retriveDataToDB: name " + name);
        if (!name.equals("NOT FOUND")) {
            databaseReference = firebaseDatabase.getReference().child("meal").child(name);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (dataSnapshot.getKey().equals("favList")) {

                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                Log.d(TAG, "onDataChange: dddddddddddddd " + data.child("idMeal").getValue().toString());
                                meall = new Meal(Integer.parseInt(data.child("id").getValue().toString()), data.child("idMeal").getValue().toString(), true, "temp", data.child("strMeal").getValue().toString(), data.child("strCategory").getValue().toString(), data.child("strArea").getValue().toString(), data.child("strInstructions").getValue().toString(), data.child("strMealThumb").getValue().toString(), data.child("strYoutube").getValue().toString());
                                ingredients(data);
                                favMeals.add(meall);

                            }

                        } else if (dataSnapshot.getKey().equals("plannedList")) {

                            for (DataSnapshot data : dataSnapshot.getChildren()) {

                                Log.d(TAG, "onDataChange: pppppppp " + data.child("day").getValue().toString());
                                meall = new Meal(Integer.parseInt(data.child("id").getValue().toString()), data.child("idMeal").getValue().toString(), false, data.child("day").getValue().toString(), data.child("strMeal").getValue().toString(), data.child("strCategory").getValue().toString(), data.child("strArea").getValue().toString(), data.child("strInstructions").getValue().toString(), data.child("strMealThumb").getValue().toString(), data.child("strYoutube").getValue().toString());
                                ingredients(data);
                                plannedMeals.add(meall);
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        } else {
            Toast.makeText(this, "Can't retrive your data.....", Toast.LENGTH_SHORT).show();
        }
    }

    public void ingredients(DataSnapshot data) {

        if(data.child("strIngredient1").getValue().toString() != null && !data.child("strIngredient1").getValue().toString().equals(""))
        {
            meall.setStrIngredient1(data.child("strIngredient1").getValue().toString());
            meall.setStrMeasure1(data.child("strMeasure1").getValue().toString());
            if(data.child("strIngredient2").getValue().toString() != null && !data.child("strIngredient2").getValue().toString().equals(""))
            {
                meall.setStrIngredient2(data.child("strIngredient2").getValue().toString());
                meall.setStrMeasure2(data.child("strMeasure2").getValue().toString());
                if(data.child("strIngredient3").getValue().toString() != null && !data.child("strIngredient3").getValue().toString().equals(""))
                {
                    meall.setStrIngredient3(data.child("strIngredient3").getValue().toString());
                    meall.setStrMeasure3(data.child("strMeasure3").getValue().toString());
                    if(data.child("strIngredient4").getValue().toString() != null && !data.child("strIngredient4").getValue().toString().equals(""))
                    {
                        meall.setStrIngredient4(data.child("strIngredient4").getValue().toString());
                        meall.setStrMeasure4(data.child("strMeasure4").getValue().toString());
                        if(data.child("strIngredient5").getValue().toString() != null && !data.child("strIngredient5").getValue().toString().equals(""))
                        {
                            meall.setStrIngredient5(data.child("strIngredient5").getValue().toString());
                            meall.setStrMeasure5(data.child("strMeasure5").getValue().toString());
                            if(data.child("strIngredient6").getValue().toString() != null && !data.child("strIngredient6").getValue().toString().equals(""))
                            {
                                meall.setStrIngredient6(data.child("strIngredient6").getValue().toString());
                                meall.setStrMeasure6(data.child("strMeasure6").getValue().toString());
                                if(data.child("strIngredient7").getValue().toString() != null && !data.child("strIngredient7").getValue().toString().equals(""))
                                {
                                    meall.setStrIngredient7(data.child("strIngredient7").getValue().toString());
                                    meall.setStrMeasure7(data.child("strMeasure7").getValue().toString());
                                    if(data.child("strIngredient8").getValue().toString() != null && !data.child("strIngredient8").getValue().toString().equals(""))
                                    {
                                        meall.setStrIngredient8(data.child("strIngredient8").getValue().toString());
                                        meall.setStrMeasure8(data.child("strMeasure8").getValue().toString());
                                        if(data.child("strIngredient9").getValue().toString() != null && !data.child("strIngredient9").getValue().toString().equals(""))
                                        {
                                            meall.setStrIngredient9(data.child("strIngredient9").getValue().toString());
                                            meall.setStrMeasure9(data.child("strMeasure9").getValue().toString());
                                            if(data.child("strIngredient10").getValue().toString() != null && !data.child("strIngredient10").getValue().toString().equals(""))
                                            {
                                                meall.setStrIngredient10(data.child("strIngredient10").getValue().toString());
                                                meall.setStrMeasure10(data.child("strMeasure10").getValue().toString());
                                                if(data.child("strIngredient11").getValue().toString() != null && !data.child("strIngredient11").getValue().toString().equals(""))
                                                {
                                                    meall.setStrIngredient11(data.child("strIngredient11").getValue().toString());
                                                    meall.setStrMeasure11(data.child("strMeasure11").getValue().toString());
                                                    if(data.child("strIngredient12").getValue().toString() != null && !data.child("strIngredient12").getValue().toString().equals(""))
                                                    {
                                                        meall.setStrIngredient12(data.child("strIngredient12").getValue().toString());
                                                        meall.setStrMeasure12(data.child("strMeasure12").getValue().toString());
                                                        if(data.child("strIngredient13").getValue().toString() != null && !data.child("strIngredient13").getValue().toString().equals(""))
                                                        {
                                                            meall.setStrIngredient13(data.child("strIngredient13").getValue().toString());
                                                            meall.setStrMeasure13(data.child("strMeasure13").getValue().toString());
                                                            if(data.child("strIngredient14").getValue().toString() != null && !data.child("strIngredient14").getValue().toString().equals(""))
                                                            {
                                                                meall.setStrIngredient14(data.child("strIngredient14").getValue().toString());
                                                                meall.setStrMeasure14(data.child("strMeasure14").getValue().toString());
                                                                if(data.child("strIngredient15").getValue().toString() != null && !data.child("strIngredient15").getValue().toString().equals(""))
                                                                {
                                                                    meall.setStrIngredient15(data.child("strIngredient15").getValue().toString());
                                                                    meall.setStrMeasure15(data.child("strMeasure15").getValue().toString());
                                                                    if(data.child("strIngredient16").getValue().toString() != null && !data.child("strIngredient16").getValue().toString().equals(""))
                                                                    {
                                                                        meall.setStrIngredient16(data.child("strIngredient16").getValue().toString());
                                                                        meall.setStrMeasure16(data.child("strMeasure16").getValue().toString());
                                                                        if(data.child("strIngredient17").getValue().toString() != null && !data.child("strIngredient17").getValue().toString().equals(""))
                                                                        {
                                                                            meall.setStrIngredient17(data.child("strIngredient17").getValue().toString());
                                                                            meall.setStrMeasure17(data.child("strMeasure17").getValue().toString());
                                                                            if(data.child("strIngredient18").getValue().toString() != null && !data.child("strIngredient18").getValue().toString().equals(""))
                                                                            {
                                                                                meall.setStrIngredient18(data.child("strIngredient18").getValue().toString());
                                                                                meall.setStrMeasure18(data.child("strMeasure18").getValue().toString());
                                                                                if(data.child("strIngredient19").getValue().toString() != null && !data.child("strIngredient19").getValue().toString().equals(""))
                                                                                {
                                                                                    meall.setStrIngredient19(data.child("strIngredient19").getValue().toString());
                                                                                    meall.setStrMeasure19(data.child("strMeasure19").getValue().toString());
                                                                                    if(data.child("strIngredient20").getValue().toString() != null && !data.child("strIngredient20").getValue().toString().equals(""))
                                                                                    {
                                                                                        meall.setStrIngredient20(data.child("strIngredient20").getValue().toString());
                                                                                        meall.setStrMeasure20(data.child("strMeasure20").getValue().toString());
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

