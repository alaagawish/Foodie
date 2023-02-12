package eg.gov.iti.jets.foodie.model;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.db.LocalSourceInterface;
import eg.gov.iti.jets.foodie.db.MealFavPlanDAO;
import eg.gov.iti.jets.foodie.network.NetworkDelegation;
import eg.gov.iti.jets.foodie.network.RemoteSource;

public class Repository implements RepositoryInterface {
    private Context context;
    RemoteSource remoteSource;
    LocalSourceInterface localSourceInterface;
    private static final String TAG = "Repository";
    private static Repository repository = null;

    @Override
    public LiveData<List<Meal>> getPlannedMealss() {
        return localSourceInterface.getPlannedMealss();
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSourceInterface localSourceInterface, Context context) {
        if (repository == null)
            repository = new Repository(remoteSource, localSourceInterface, context);
        return repository;

    }

    private Repository(RemoteSource remoteSource, LocalSourceInterface localSourceInterface, Context context) {
        this.remoteSource = remoteSource;
        this.localSourceInterface = localSourceInterface;
        this.context = context;

    }

    @Override
    public void insertMeal(Meal meal) {
        localSourceInterface.insertFavMeal(meal);

    }

    @Override
    public void removeMeal(Meal meal) {
        localSourceInterface.removeMeal(meal);

    }

    @Override
    public void deleteDBTable() {
        localSourceInterface.deleteTable();

    }

    @Override
    public void removeMealPlanned(Meal meal) {
        Log.d(TAG, "removeMealPlanned: ");
        localSourceInterface.removePlannedMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getMealsDB() {
        return localSourceInterface.getAllMealsStored();
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return localSourceInterface.getAllFavMeals();
    }

    @Override
    public LiveData<List<Meal>> getMealsByDayDB(String day) {
        return localSourceInterface.getStoredMealsByDay(day);
    }

    @Override
    public void getAllRandomMeals(NetworkDelegation networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);

    }

    @Override
    public void getMealDetails(NetworkDelegation networkDelegate, String id) {
        remoteSource.enqueueCallById(networkDelegate, id);
    }

    @Override
    public void getSearchedMeals(NetworkDelegation networkDelegate, String search) {
        remoteSource.enqueueCallBySearch(networkDelegate, search);
    }

    @Override
    public void getFilteredMeals(NetworkDelegation networkDelegate, String name, char c) {
        remoteSource.enqueueCall(networkDelegate, name, c);

    }
}