package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;


public interface LocalSourceInterface {
    public void insertFavMeal(Meal meal);

    public void removeMeal(Meal meal);

    public void removePlannedMeal(Meal meal);

    public LiveData<List<Meal>> getAllMealsStored();
    public LiveData<List<Meal>> getPlannedMealss();

    public LiveData<List<Meal>> getAllFavMeals();

    public LiveData<List<Meal>> getStoredMealsByDay(String day);
}

