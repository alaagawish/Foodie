package eg.gov.iti.jets.foodie.favourites.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.MealFavPlan;

public interface FavouritePresenterInterface {
    public void removeFavouriteMeal(MealFavPlan mealFavPlan);

    public LiveData<List<MealFavPlan>> getAllMealFavPlan();

}
