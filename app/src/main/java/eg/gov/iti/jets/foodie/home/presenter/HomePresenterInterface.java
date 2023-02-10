package eg.gov.iti.jets.foodie.home.presenter;


import java.util.regex.Matcher;

import eg.gov.iti.jets.foodie.model.Meal;

public interface HomePresenterInterface {
    public void addFavouriteMeal(Meal meal);

    public void getRandomMeals();

}
