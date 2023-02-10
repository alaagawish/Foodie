package eg.gov.iti.jets.foodie.favourites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.favourites.presenter.FavouritePresenter;
import eg.gov.iti.jets.foodie.favourites.presenter.FavouritePresenterInterface;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;

public class FavouritesFragment extends Fragment implements FavouriteMealsClickListener, FavouriteViewInterface{

    RecyclerView favouriteRecyclerView;
    FavouriteAdapter favouriteAdapter;
    FavouritePresenterInterface favouritePresenterInterface;
    List<Meal> favMeals;

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

        favouritePresenterInterface = new FavouritePresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));
        favouriteRecyclerView = view.findViewById(R.id.favouriteRecyclerView);
        favouriteAdapter = new FavouriteAdapter(getContext(), FavouritesFragment.this);
        favMeals = new ArrayList<>();

        favouritePresenterInterface.getAllMealFavPlan().observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favMeals.clear();
                for (Meal meal: meals)
                {
                    if(meal.isFav())
                        favMeals.add(meal);
                }
                favouriteAdapter.setAllMeals(favMeals);
                favouriteRecyclerView.setAdapter(favouriteAdapter);
                favouriteAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Meal meal) {
        favouritePresenterInterface.removeFavouriteMeal(meal);
        favouriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeFavMeal(Meal meal) {
        favouritePresenterInterface.removeFavouriteMeal(meal);
        favouriteAdapter.notifyDataSetChanged();
    }
}