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
    void insertMeal(Meal meal);

    void removeMeal(Meal meal);

    void removeMealPlanned(Meal meal);

    LiveData<List<Meal>> getMealsDB();

    LiveData<List<Meal>> getAllFavMeals();
    LiveData<List<Meal>> getPlannedMealss();

    LiveData<List<Meal>> getMealsByDayDB(String day);

    void getAllRandomMeals(NetworkDelegation networkDelegate);

    void getFilteredMeals(NetworkDelegation networkDelegate, String name, char c);

    void getMealDetails(NetworkDelegation networkDelegate, String id);

    void getSearchedMeals(NetworkDelegation networkDelegate, String search);
}