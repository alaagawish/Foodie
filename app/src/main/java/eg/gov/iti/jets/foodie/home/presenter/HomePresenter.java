package eg.gov.iti.jets.foodie.home.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodie.home.view.HomeMealsViewInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;
import eg.gov.iti.jets.foodie.model.MealFavPlan;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public class HomePresenter implements HomePresenterInterface, NetworkDelegation {
    private HomeMealsViewInterface homeMealsViewInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "HomePresenter";

    public HomePresenter(HomeMealsViewInterface homeMealsViewInterface, RepositoryInterface repositoryInterface) {
        this.homeMealsViewInterface = homeMealsViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void addFavouriteMeal(MealFavPlan mealFavPlan) {
        repositoryInterface.insertMeal(mealFavPlan);
    }

    @Override
    public void getRandomMeals() {
        repositoryInterface.getAllRandomMeals(this);
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        Log.d(TAG, "onSuccess: ");
        homeMealsViewInterface.showMeals(meals);
    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        homeMealsViewInterface.showCountries(countries);
    }


    @Override
    public void onSuccessMeal(Meal meals) {
        Log.d(TAG, "onSuccessMeal: ");
    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {
        Log.d(TAG, "onSuccessIngredient: ");
        homeMealsViewInterface.showIngrediants(ingredientLists);
    }

    @Override
    public void onSuccessCategory(List<Category> categories) {
        Log.d(TAG, "onSuccessCategory: ");
        homeMealsViewInterface.showCategories(categories);

    }

    @Override
    public void onSuccessString(String img) {
        Log.d(TAG, "onSuccessString: ");
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: no products");
    }


    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {

    }

}
