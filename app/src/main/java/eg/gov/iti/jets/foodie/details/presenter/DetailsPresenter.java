package eg.gov.iti.jets.foodie.details.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodie.details.view.DetailsViewInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public class DetailsPresenter implements DetailsPresenterInterface, NetworkDelegation {
    private DetailsViewInterface detailsViewInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "MealsPresenter";

    public DetailsPresenter(DetailsViewInterface detailsViewInterface, RepositoryInterface repositoryInterface) {
        this.detailsViewInterface = detailsViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void addFavouriteMeal(Meal meal) {
        repositoryInterface.insertMeal(meal);
    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {

    }

    @Override
    public void onSuccessMeal(Meal meal) {

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

    }
}
