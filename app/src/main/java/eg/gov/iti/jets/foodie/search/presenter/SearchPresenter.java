package eg.gov.iti.jets.foodie.search.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodie.home.presenter.HomePresenterInterface;
import eg.gov.iti.jets.foodie.home.view.HomeMealsViewInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealFavPlan;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;
import eg.gov.iti.jets.foodie.search.view.SearchViewInterface;

public class SearchPresenter implements SearchPresenterInterface, NetworkDelegation {
    private SearchViewInterface searchViewInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "SearchPresenter";

    public SearchPresenter(SearchViewInterface searchViewInterface, RepositoryInterface repositoryInterface) {
        this.searchViewInterface = searchViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        Log.d(TAG, "onSuccess: ");
        searchViewInterface.showMeals(meals);

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {

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
    public void getSearchedMeals(String search) {
        repositoryInterface.getSearchedMeals(this, search);
    }
}