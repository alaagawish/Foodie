package eg.gov.iti.jets.foodie.meals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.details.view.DetailsActivity;
import eg.gov.iti.jets.foodie.home.presenter.HomePresenter;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
import eg.gov.iti.jets.foodie.meals.presenter.MealsPresenter;
import eg.gov.iti.jets.foodie.meals.presenter.MealsPresenterInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.Ingredient;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;

public class MealsActivity extends AppCompatActivity implements MealsClickListener, MealsViewInterface {
    private static final String TAG = "MealsActivity";
    private ImageButton backArrowCircularImageButton;
    private TextView mealsTypeTextView;
    private RecyclerView itemsRecyclerView;
    private String type;
    private MealsAdapter mealsAdapter;
    private MealsPresenterInterface mealsPresenterInterface;
    private List<Meal> meals;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        init();

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        mealsPresenterInterface = new MealsPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(this), this));
        if (type.equals("Category")) {
            Category name = (Category) intent.getSerializableExtra("name");
            mealsTypeTextView.setText(type + ": " + name.getStrCategory());
            Log.d(TAG, "onCreate: " + name.getStrCategory());
            mealsPresenterInterface.getFilteredMeals(name.getStrCategory(), 'c');
        } else if (type.equals("Country")) {
            Country name = (Country) intent.getSerializableExtra("name");
            mealsTypeTextView.setText(type + ": " + name.getStrArea());
            mealsPresenterInterface.getFilteredMeals(name.getStrArea(), 'a');

        } else if (type.equals("Ingredient")) {
            IngredientList name = (IngredientList) intent.getSerializableExtra("name");
            mealsTypeTextView.setText(type + ": " + name.getStrIngredient());
            mealsPresenterInterface.getFilteredMeals(name.getStrIngredient(), 'i');

        }

        backArrowCircularImageButton.setOnClickListener(e -> {
            Intent intent1 = new Intent(MealsActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    public void init() {
        meals = new ArrayList<>();
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        mealsTypeTextView = findViewById(R.id.mealsTypeTextView);
        backArrowCircularImageButton = findViewById(R.id.backArrowCircularImageButton);
        mealsAdapter = new MealsAdapter(this, this, !sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND"));


    }

    @Override
    public void showMealDetails(Meal meal) {
        this.meals.add(meal);
        mealsAdapter.setAllMeals(this.meals);
        itemsRecyclerView.setAdapter(mealsAdapter);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMeals(List<Meal> meals) {
        Log.d(TAG, "showMealsArea: ");
        for (int i = 0; i < meals.size(); i++) {
            mealsPresenterInterface.getMealDetails(meals.get(i).getIdMeal());
        }
    }

    @Override
    public void addFavor(Meal meal) {
        mealsPresenterInterface.addFavouriteMeal(meal);
    }

//    @Override
//    public void removeFavor(Meal meal) {
//        mealsPresenterInterface.removeFavouriteMeal(meal);
//
//    }
}