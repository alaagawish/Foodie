package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;
import io.reactivex.rxjava3.core.Completable;


public interface LocalSourceInterface {
    Completable insertFavMeal(Meal meal);

    Completable removeMeal(Meal meal);

    Completable deleteTable();

    Completable removePlannedMeal(Meal meal);

    LiveData<List<Meal>> getAllMealsStored();

    LiveData<List<Meal>> getPlannedMealss();

    LiveData<List<Meal>> getAllFavMeals();

    LiveData<List<Meal>> getStoredMealsByDay(String day);
}