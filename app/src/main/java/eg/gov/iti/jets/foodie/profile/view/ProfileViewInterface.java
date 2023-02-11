package eg.gov.iti.jets.foodie.profile.view;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface ProfileViewInterface {
    void favMeals(List<Meal> meals);

    void plannedMeals(List<Meal> meals);


}
