package eg.gov.iti.jets.foodie.login.presenter;

import android.util.Log;

import java.util.List;

import eg.gov.iti.jets.foodie.login.view.LoginViewInterface;
import eg.gov.iti.jets.foodie.meals.presenter.MealsPresenterInterface;
import eg.gov.iti.jets.foodie.meals.view.MealsViewInterface;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public class LoginPresenter implements LoginPresenterInterface, NetworkDelegation {
    private LoginViewInterface loginViewInterface;
    private RepositoryInterface repositoryInterface;
    private static final String TAG = "MealsPresenter";

    public LoginPresenter(LoginViewInterface loginViewInterface, RepositoryInterface repositoryInterface) {
        this.loginViewInterface = loginViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void addMeals(Meal meal) {
        repositoryInterface.insertMeal(meal);

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
        Log.d(TAG, "onFailure: " + error);

    }


//    @Override
//    public void showMealDetailsLog(String id) {
//        repositoryInterface.getMealDetails(this, id);
//    }

}
