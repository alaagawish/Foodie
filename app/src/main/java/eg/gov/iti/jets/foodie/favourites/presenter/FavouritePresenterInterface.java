package eg.gov.iti.jets.foodie.favourites.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface FavouritePresenterInterface {
    void removeFavouriteMeal(Meal meal);

    LiveData<List<Meal>> getAllMealFavPlan();

}
