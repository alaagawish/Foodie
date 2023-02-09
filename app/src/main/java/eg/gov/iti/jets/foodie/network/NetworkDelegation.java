package eg.gov.iti.jets.foodie.network;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;

public interface NetworkDelegation {
    public void onSuccess(List<Meal> meals);

    public void onSuccessCountries(List<Country> countries);

    public void onSuccessMealByFilter(List<Meal> meals);

    public void onSuccessMeal(Meal meal);

    public void onSuccessCategory(List<Category> categories);

    public void onSuccessIngredient(List<IngredientList> ingredientLists);

    public void onSuccessString(String img);

    public void onFailure(String error);
}
