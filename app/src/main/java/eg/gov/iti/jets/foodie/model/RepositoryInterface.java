package eg.gov.iti.jets.foodie.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.db.MealFavPlanDAO;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.db.MealFavPlanDAO;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public interface RepositoryInterface {
    public void insertMeal(Meal meal);

    public void removeMeal(Meal meal);

    public LiveData<List<Meal>> getMealsDB();

    public void getAllRandomMeals(NetworkDelegation networkDelegate);

    public void getFilteredMeals(NetworkDelegation networkDelegate, String name, char c);

    public void getMealDetails(NetworkDelegation networkDelegate, String id);

    public void getSearchedMeals(NetworkDelegation networkDelegate, String search);
}