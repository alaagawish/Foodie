package eg.gov.iti.jets.foodie.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.db.MealFavPlanDAO;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public interface RepositoryInterface {
    public void insertMeal(MealFavPlan mealFavPlan);

    public void removeMeal(MealFavPlan mealFavPlan);

    public LiveData<List<MealFavPlan>> getMealsDB();

    public void getAllMeals(NetworkDelegation networkDelegate);
}
