package eg.gov.iti.jets.foodie.plan.presenter;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;

public class PlanPresenter implements PlanPresenterInterface, NetworkDelegation {
    private static final String TAG = "PlanPresenter";

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
