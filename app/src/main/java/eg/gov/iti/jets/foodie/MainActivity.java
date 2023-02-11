package eg.gov.iti.jets.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import eg.gov.iti.jets.foodie.favourites.view.FavouritesFragment;
import eg.gov.iti.jets.foodie.home.view.HomeFragment;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
import eg.gov.iti.jets.foodie.plan.view.PlanFragment;
import eg.gov.iti.jets.foodie.profile.view.ProfileFragment;
import eg.gov.iti.jets.foodie.search.view.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private FavouritesFragment favouritesFragment = new FavouritesFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private PlanFragment planFragment = new PlanFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, homeFragment).commit();
        SharedPreferences sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, homeFragment).commit();
                        return true;
                    case R.id.profile:
                        if (sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND")) {
                           dialog();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, profileFragment).commit();
                        }
                        return true;
                    case R.id.planner:
                        if (sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND")) {
                            dialog();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, planFragment).commit();
                        }
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, searchFragment).commit();
                        return true;
                    case R.id.favourite:
                        if (sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND")) {
                            dialog();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, favouritesFragment).commit();
                        }
                        return true;


                }
                return false;
            }
        });

    }

    public void back(View view) {
        finish();
    }
    public void dialog()
    {
        View dialogLayout = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_guest, null);
        HomeFragment.searchDialog = new Dialog(MainActivity.this);
        HomeFragment.searchDialog.setContentView(dialogLayout);
        HomeFragment.searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        HomeFragment.searchDialog.show();
    }

    public void init() {
        planFragment = new PlanFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        favouritesFragment = new FavouritesFragment();
        searchFragment = new SearchFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigator);

    }
}