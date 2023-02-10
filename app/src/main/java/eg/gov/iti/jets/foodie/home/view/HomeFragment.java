package eg.gov.iti.jets.foodie.home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.details.view.DetailsActivity;
import eg.gov.iti.jets.foodie.home.presenter.HomePresenter;
import eg.gov.iti.jets.foodie.home.presenter.HomePresenterInterface;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;

public class HomeFragment extends Fragment implements HomeMealsClickListener, HomeMealsViewInterface {

    private SliderView sliderView;
    private Meal randomMeal;
    private SliderAdapter sliderAdapter;
    private HomePresenterInterface homePresenterInterface;
    private ImageButton randomHeartButton;
    private ImageView randomImageView;
    private TextView randomMealTextView;
    private static final String TAG = "HomeFragment";
    private CardView randomCardView;
    private ArrayList<Slider> sliderArrayList;
    private RecyclerView ingredientsHomeRecyclerView, categoriesHomeRecyclerView, countriesHomeRecyclerView;
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    private CountryRecyclerViewAdapter countryRecyclerViewAdapter;
    private IngredientRecyclerViewAdapter ingredientRecyclerViewAdapter;
    List<Meal> meals;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderArrayList = new ArrayList<>();
        sliderArrayList.add(new Slider("https://www.meingenuss.de/images/box-editor/-13324-0.jpeg?v="));
        sliderArrayList.add(new Slider("https://westernnews.media.clients.ellingtoncms.com/img/photos/2018/02/09/Pancakes.jpeg"));
        sliderArrayList.add(new Slider("https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        sliderAdapter = new SliderAdapter(sliderArrayList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        homePresenterInterface = new HomePresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));
        homePresenterInterface.getRandomMeals();


        randomCardView.setOnClickListener(e -> {
            Intent intent = new Intent(getContext(), DetailsActivity.class);
            intent.putExtra("meal", randomMeal);
            startActivity(intent);

        });


    }

    @Override
    public void showMeals(List<Meal> meals) {
        randomMeal = meals.get(0);
        Glide.with(getContext()).load(meals.get(0).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(randomImageView);
        randomMealTextView.setText(meals.get(0).getStrMeal());
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoryRecyclerViewAdapter.setAllCategories(categories);
        categoriesHomeRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        categoryRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIngrediants(List<IngredientList> ingredient) {
        ingredientRecyclerViewAdapter.setAllIngredients(ingredient);
        ingredientsHomeRecyclerView.setAdapter(ingredientRecyclerViewAdapter);
        ingredientRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCountries(List<Country> countries) {
        countryRecyclerViewAdapter.setAllCountries(countries);
        countriesHomeRecyclerView.setAdapter(countryRecyclerViewAdapter);
        countryRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMeal(Meal meal) {
//        Meal meal1 = new Meal();
//
//        meal1.setMeal(meal);

        homePresenterInterface.addFavouriteMeal(meal);
    }

    public void init(View view) {
        randomHeartButton = view.findViewById(R.id.randomHeartButton);
        randomImageView = view.findViewById(R.id.randomImageView);
        randomMealTextView = view.findViewById(R.id.randomMealTextView);
        randomCardView = view.findViewById(R.id.randomCardView);
        sliderView = view.findViewById(R.id.imageSlider);

        categoriesHomeRecyclerView = view.findViewById(R.id.categoriesHomeRecyclerView);
        countriesHomeRecyclerView = view.findViewById(R.id.countriesHomeRecyclerView);
        ingredientsHomeRecyclerView = view.findViewById(R.id.ingredientsHomeRecyclerView);


        ingredientRecyclerViewAdapter = new IngredientRecyclerViewAdapter(getContext(), HomeFragment.this);
        countryRecyclerViewAdapter = new CountryRecyclerViewAdapter(getContext(), HomeFragment.this);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getContext(), HomeFragment.this);
        ingredientsHomeRecyclerView.setAdapter(ingredientRecyclerViewAdapter);
        categoriesHomeRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        countriesHomeRecyclerView.setAdapter(countryRecyclerViewAdapter);
    }

}