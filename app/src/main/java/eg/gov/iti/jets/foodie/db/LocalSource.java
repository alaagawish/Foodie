package eg.gov.iti.jets.foodie.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealFavPlan;

public class LocalSource implements LocalSourceInterface {
    private static LocalSource localSource = null;
    private LiveData<List<MealFavPlan>> storedMeals;
    private MealFavPlanDAO mealFavPlanDAO;

    private LocalSource(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealFavPlanDAO = appDatabase.mealFavPlanDAO();
        storedMeals = mealFavPlanDAO.getAllFavMeals();
    }


    public static LocalSource getInstance(Context context) {
        if (localSource == null)
            localSource = new LocalSource(context);
        return localSource;
    }

    @Override
    public void insertFavMeal(MealFavPlan mealFavPlan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealFavPlanDAO.insertFavMeal(mealFavPlan);
            }
        }).start();

    }

    @Override
    public void removeMeal(MealFavPlan mealFavPlan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealFavPlanDAO.deleteFromFavMeal(mealFavPlan);
            }

        }).start();

    }


    @Override
    public LiveData<List<MealFavPlan>> getAllMealsStored() {
        return storedMeals;
    }

}