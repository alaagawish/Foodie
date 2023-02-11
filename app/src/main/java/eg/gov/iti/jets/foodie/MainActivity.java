package eg.gov.iti.jets.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import eg.gov.iti.jets.foodie.favourites.view.FavouritesFragment;
import eg.gov.iti.jets.foodie.home.view.HomeFragment;
import eg.gov.iti.jets.foodie.plan.view.PlanFragment;
import eg.gov.iti.jets.foodie.profile.view.ProfileFragment;
import eg.gov.iti.jets.foodie.search.view.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private FavouritesFragment favouritesFragment;
    private SearchFragment searchFragment;
    private PlanFragment planFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, homeFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, profileFragment).commit();
                        return true;
                    case R.id.planner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, planFragment).commit();
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, searchFragment).commit();
                        return true;
                    case R.id.favourite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, favouritesFragment).commit();
                        return true;


                }
                return false;
            }
        });

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