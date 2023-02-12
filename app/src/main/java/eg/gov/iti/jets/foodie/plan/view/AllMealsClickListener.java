package eg.gov.iti.jets.foodie.plan.view;

import eg.gov.iti.jets.foodie.model.Meal;

public interface AllMealsClickListener {

    void addMealToDay(Meal meal);

    void deleteMealFromDay(Meal meal);
}
