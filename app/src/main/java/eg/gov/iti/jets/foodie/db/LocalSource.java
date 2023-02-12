package eg.gov.iti.jets.foodie.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;
import io.reactivex.rxjava3.core.Completable;

public class LocalSource implements LocalSourceInterface {
    private static LocalSource localSource = null;
    private LiveData<List<Meal>> plannedMeals, favMeals, storedMeals, saturdayMeals, sundayMeals, mondayMeals, tuesdayMeals, wednesdayMeals, thursdayMeals, fridayMeals;
    private MealFavPlanDAO mealFavPlanDAO;

    private LocalSource(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealFavPlanDAO = appDatabase.mealFavPlanDAO();
        storedMeals = mealFavPlanDAO.getAllMeals();
        plannedMeals = mealFavPlanDAO.getPlannedMeals();
        favMeals = mealFavPlanDAO.getAllFavMeals();
        saturdayMeals = mealFavPlanDAO.getMealsByDay("saturday");
        sundayMeals = mealFavPlanDAO.getMealsByDay("sunday");
        mondayMeals = mealFavPlanDAO.getMealsByDay("monday");
        tuesdayMeals = mealFavPlanDAO.getMealsByDay("tuesday");
        thursdayMeals = mealFavPlanDAO.getMealsByDay("thursday");
        wednesdayMeals = mealFavPlanDAO.getMealsByDay("wednesday");
        fridayMeals = mealFavPlanDAO.getMealsByDay("friday");
    }


    public static LocalSource getInstance(Context context) {
        if (localSource == null) localSource = new LocalSource(context);
        return localSource;
    }

    @Override
    public Completable insertFavMeal(Meal meal) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        return mealFavPlanDAO.insertFavMeal(meal);
//            }
//        }).start();

    }


    @Override
    public Completable deleteTable() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        return mealFavPlanDAO.deleteTableData();
//            }
//        }).start();

    }

    @Override
    public Completable removeMeal(Meal meal) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        return mealFavPlanDAO.deleteFromFavMeal(meal);
//            }
//
//        }).start();

    }



    @Override
    public Completable removePlannedMeal(Meal meal) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        return mealFavPlanDAO.deleteFromPlan(meal);
//            }
//
//        }).start();

    }


    @Override
    public LiveData<List<Meal>> getAllMealsStored() {
        return storedMeals;
    }

    @Override
    public LiveData<List<Meal>> getStoredMealsByDay(String day) {
        switch (day) {
            case "saturday":
                return saturdayMeals;
            case "sunday":
                return sundayMeals;
            case "monday":
                return mondayMeals;
            case "tuesday":
                return tuesdayMeals;
            case "wednesday":
                return wednesdayMeals;
            case "thursday":
                return thursdayMeals;
            case "friday":
                return fridayMeals;
            default:
                return null;

        }
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return favMeals;
    }

    @Override
    public LiveData<List<Meal>> getPlannedMealss() {
        return plannedMeals;
    }
}