package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;


public interface LocalSourceInterface {
    void insertFavMeal(Meal meal);

    void removeMeal(Meal meal);

    void deleteTable();

    void removePlannedMeal(Meal meal);

    LiveData<List<Meal>> getAllMealsStored();

    LiveData<List<Meal>> getPlannedMealss();

    LiveData<List<Meal>> getAllFavMeals();

    LiveData<List<Meal>> getStoredMealsByDay(String day);
}