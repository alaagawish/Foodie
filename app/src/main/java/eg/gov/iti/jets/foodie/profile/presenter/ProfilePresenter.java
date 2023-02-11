package eg.gov.iti.jets.foodie.profile.presenter;

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
import eg.gov.iti.jets.foodie.profile.view.ProfileViewInterface;

public class ProfilePresenter implements ProfilePresenterInterface {

    private static final String TAG = "ProfilePresenter";
    private ProfileViewInterface profileViewInterface;
    private RepositoryInterface repositoryInterface;

    public ProfilePresenter(ProfileViewInterface profileViewInterface, RepositoryInterface repositoryInterface) {
        this.profileViewInterface = profileViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeal() {
        return repositoryInterface.getAllFavMeals();
    }

    @Override
    public LiveData<List<Meal>> getAllPlannedMeal() {
        Log.d(TAG, "getAllPlannedMeal: ");
        return repositoryInterface.getPlannedMealss();
    }

    @Override
    public void deleteDBTable() {
        Log.d(TAG, "deleteDBTable: deleting data");
        repositoryInterface.deleteDBTable();
    }
}