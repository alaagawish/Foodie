package eg.gov.iti.jets.foodie.plan.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface PlanPresenterInterface {

    void getSearchedMeals(String search);

    void addMealPlanner(Meal meal);

    void deleteMeal(Meal meal);

    LiveData<List<Meal>> getPlannedDayMeals(String day);

}
