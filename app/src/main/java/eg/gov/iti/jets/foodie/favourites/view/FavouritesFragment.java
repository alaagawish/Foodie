package eg.gov.iti.jets.foodie.favourites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealFavPlan;

public class FavouritesFragment extends Fragment implements FavouriteMealsClickListener{

    RecyclerView favouriteRecyclerView;
    FavouriteAdapter favouriteAdapter;
    List<Meal> meals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dumy();
        favouriteRecyclerView = view.findViewById(R.id.favouriteRecyclerView);
        favouriteAdapter = new FavouriteAdapter(getContext(), FavouritesFragment.this);
        favouriteAdapter.setAllMeals(meals);
        favouriteRecyclerView.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();
    }

    public void dumy() {
        meals = new ArrayList<>();
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        meals.add(new Meal("Pasta", "https://www.meingenuss.de/images/box-editor/-13324-0.jpeg?v="));
        meals.add(new Meal("Green Salad", "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2012/2/28/4/FNM_040112-Spring-Greens-011_s4x3.jpg.rend.hgtvcom.826.620.suffix/1371606120248.jpeg"));
        meals.add(new Meal("Appetizer", "https://www.walderwellness.com/wp-content/uploads/2021/11/Parmesan-Crusted-Brussels-Sprouts-Bites-Walder-Wellness-7-768x1152.jpg"));
        meals.add(new Meal("spicy chicken", "https://vismaifood.com/storage/app/uploads/public/105/fc7/89f/thumb__700_0_0_0_auto.jpg"));
    }

    @Override
    public void onClick(MealFavPlan mealFavPlan) {

    }
}