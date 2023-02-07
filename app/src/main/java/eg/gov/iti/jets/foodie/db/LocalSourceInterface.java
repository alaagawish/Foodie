package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.MealFavPlan;

public interface LocalSourceInterface {
    public void insertFavMeal(MealFavPlan mealFavPlan);

    public void removeMeal(MealFavPlan mealFavPlan);

    public LiveData<List<MealFavPlan>> getAllMealsStored();
}

