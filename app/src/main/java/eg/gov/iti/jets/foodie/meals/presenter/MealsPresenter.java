package eg.gov.iti.jets.foodie.meals.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodie.home.presenter.HomePresenterInterface;
import eg.gov.iti.jets.foodie.home.view.HomeMealsViewInterface;
import eg.gov.iti.jets.foodie.meals.view.MealsViewInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public class MealsPresenter implements MealsPresenterInterface, NetworkDelegation {
    private MealsViewInterface mealsViewInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "MealsPresenter";

    public MealsPresenter(MealsViewInterface mealsViewInterface, RepositoryInterface repositoryInterface) {
        this.mealsViewInterface = mealsViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        Log.d(TAG, "onSuccess: ");
        mealsViewInterface.showMealDetails(meals.get(0));
    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {
        Log.d(TAG, "onSuccessMealByFilter: " + meals.get(0).getStrMeal());
        mealsViewInterface.showMeals(meals);
    }

    @Override
    public void onSuccessMeal(Meal meals) {

    }

    @Override
    public void onSuccessCategory(List<Category> categories) {

    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }

    @Override
    public void onSuccessString(String img) {

    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: " + error);

    }

    @Override
    public void addFavouriteMeal(Meal meal) {
        repositoryInterface.insertMeal(meal);
    }

    @Override
    public void getMealDetails(String id) {
        repositoryInterface.getMealDetails(this, id);
    }

    @Override
    public void getFilteredMeals(String name, char c) {
        repositoryInterface.getFilteredMeals(this, name, c);
    }
}
