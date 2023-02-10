package eg.gov.iti.jets.foodie.plan.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;
import eg.gov.iti.jets.foodie.plan.view.PlanViewInterface;
import eg.gov.iti.jets.foodie.search.view.SearchViewInterface;

public class PlanPresenter implements PlanPresenterInterface, NetworkDelegation {
    private static final String TAG = "PlanPresenter";
    private PlanViewInterface planViewInterface;
    private RepositoryInterface repositoryInterface;

    public PlanPresenter(PlanViewInterface planViewInterface, RepositoryInterface repositoryInterface) {
        this.planViewInterface = planViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        Log.d(TAG, "onSuccess: ");
        planViewInterface.showMeals(meals);
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
    public void getSearchedMeals(String search) {
        repositoryInterface.getSearchedMeals(this, search);
    }

    @Override
    public void onFailure(String error) {
        Log.d(TAG, "onFailure: ");
    }

    @Override
    public void addMealPlanner(Meal meal) {
        repositoryInterface.insertMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getPlannedDayMeals(String day) {
        return repositoryInterface.getMealsByDayDB(day);
    }


    @Override
    public void deleteMeal(Meal meal) {
        repositoryInterface.removeMealPlanned(meal);
    }
}
