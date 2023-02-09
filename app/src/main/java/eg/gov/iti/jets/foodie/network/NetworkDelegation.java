package eg.gov.iti.jets.foodie.network;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;

public interface NetworkDelegation {
    public void onSuccess(List<Meal> meals);

    public void onSuccessMealByFilter(List<MealByFilter> meals);

    public void onSuccessMeal(Meal meals);

    public void onSuccessCategory(List<Category> categories);

    public void onSuccessIngredient(List<IngredientList> ingredientLists);

    public void onSuccessString(String img);


    public void onFailure(String error);
}
