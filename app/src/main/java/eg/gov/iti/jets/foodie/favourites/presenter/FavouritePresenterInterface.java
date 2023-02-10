package eg.gov.iti.jets.foodie.favourites.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;


public interface FavouritePresenterInterface {
    public void removeFavouriteMeal(Meal meal);

    public LiveData<List<Meal>> getAllMealFavPlan();

}
