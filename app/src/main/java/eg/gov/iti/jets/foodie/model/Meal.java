package eg.gov.iti.jets.foodie.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable {
    private static final String TAG = "Meal";
    private String idMeal;
    private String strMeal;
    private String strDrinkAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private List<Ingredient> ingredientList;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    public Meal() {
        ingredientList = new ArrayList<>();
    }

    public Meal(String strMeal, String strMealThumb) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public Meal(String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube) {
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
//        this.ingredientList = ingredientList;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public List<Ingredient> getIngredientList() {
        if (strIngredient1 != null && !strIngredient1.equals(""))
            ingredientList.add(new Ingredient(strIngredient1, strMeasure1));
        if (strIngredient2 != null && !strIngredient2.equals(""))

            ingredientList.add(new Ingredient(strIngredient2, strMeasure2));
        if (strIngredient3 != null && !strIngredient3.equals(""))

            ingredientList.add(new Ingredient(strIngredient3, strMeasure3));
        if (strIngredient4 != null && !strIngredient4.equals(""))

            ingredientList.add(new Ingredient(strIngredient4, strMeasure4));
        if (strIngredient5 != null && !strIngredient5.equals(""))

            ingredientList.add(new Ingredient(strIngredient5, strMeasure5));
        if (strIngredient6 != null && !strIngredient6.equals(""))

            ingredientList.add(new Ingredient(strIngredient6, strMeasure6));
        if (strIngredient7 != null && !strIngredient7.equals(""))

            ingredientList.add(new Ingredient(strIngredient7, strMeasure7));
        if (strIngredient8 != null && !strIngredient8.equals(""))

            ingredientList.add(new Ingredient(strIngredient8, strMeasure8));
        if (strIngredient9 != null && !strIngredient9.equals(""))

            ingredientList.add(new Ingredient(strIngredient9, strMeasure9));
        if (strIngredient10 != null && !strIngredient10.equals(""))

            ingredientList.add(new Ingredient(strIngredient10, strMeasure10));
        if (strIngredient11 != null && !strIngredient11.equals(""))

            ingredientList.add(new Ingredient(strIngredient11, strMeasure11));
        if (strIngredient12 != null && !strIngredient12.equals(""))

            ingredientList.add(new Ingredient(strIngredient12, strMeasure12));
        if (strIngredient13 != null && !strIngredient13.equals(""))

            ingredientList.add(new Ingredient(strIngredient13, strMeasure13));
        if (strIngredient14 != null && !strIngredient14.equals(""))

            ingredientList.add(new Ingredient(strIngredient14, strMeasure14));
        if (strIngredient15 != null && !strIngredient15.equals(""))

            ingredientList.add(new Ingredient(strIngredient15, strMeasure15));
        if (strIngredient16 != null && !strIngredient16.equals(""))

            ingredientList.add(new Ingredient(strIngredient16, strMeasure16));
        if (strIngredient17 != null && !strIngredient17.equals(""))

            ingredientList.add(new Ingredient(strIngredient17, strMeasure17));
        if (strIngredient18 != null && !strIngredient18.equals(""))

            ingredientList.add(new Ingredient(strIngredient18, strMeasure18));
        if (strIngredient19 != null && !strIngredient19.equals(""))

            ingredientList.add(new Ingredient(strIngredient19, strMeasure19));
        if (strIngredient20 != null && !strIngredient20.equals(""))

            ingredientList.add(new Ingredient(strIngredient20, strMeasure20));

        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
