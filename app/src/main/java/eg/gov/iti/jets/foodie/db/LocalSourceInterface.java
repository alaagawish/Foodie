package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;


public interface LocalSourceInterface {
    public void insertFavMeal(Meal meal);

    public void removeMeal(Meal meal);

    public LiveData<List<Meal>> getAllMealsStored();
}

