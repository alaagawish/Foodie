package eg.gov.iti.jets.foodie.profile.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface ProfilePresenterInterface {
    LiveData<List<Meal>> getAllFavMeal();

    LiveData<List<Meal>> getAllPlannedMeal();

}
