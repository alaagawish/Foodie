package eg.gov.iti.jets.foodie.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public class LocalSource implements LocalSourceInterface {
    private static LocalSource localSource = null;
    private LiveData<List<Meal>> storedMeals;
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
    public void insertFavMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealFavPlanDAO.insertFavMeal(meal);
            }
        }).start();

    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealFavPlanDAO.deleteFromFavMeal(meal);
            }

        }).start();

    }


    @Override
    public LiveData<List<Meal>> getAllMealsStored() {
        return storedMeals;
    }

}