package eg.gov.iti.jets.foodie.network;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;
import eg.gov.iti.jets.foodie.model.MealByIngredient;
import eg.gov.iti.jets.foodie.model.MyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API_Service {
    @GET("api/json/v1/1/filter.php?a={area}")
    Call<List<MealByFilter>> getAllMealsByArea(@Path("area") String area);

    @GET("api/json/v1/1/filter.php?i={ingredient}")
    Call<List<MealByFilter>> getAllMealsByIngredient(@Path("ingredient") String ingredient);

    @GET("api/json/v1/1/filter.php?c={category}")
    Call<List<MealByFilter>> getAllMealsByCategory(@Path("category") String category);

    @GET("api/json/v1/1/search.php?s={name}")
    Call<List<Meal>> getAllMealsBySearch(@Path("name") String name);


    @GET("api/json/v1/1/search.php?f={c}")
    Call<Meal> getAllMealsByFirstLetter(@Path("c") char c);


    @GET("api/json/v1/1/lookup.php?i={id}")
    Call<Meal> getAllMealsById(@Path("id") String id);


    @GET("api/json/v1/1/random.php")
    Call<List<Meal>> getMealsByRandom();


    @GET("api/json/v1/1/categories.php")
    Call<List<Category>> getAllCategories();

//    @GET("api/json/v1/1/lookup.php?i={id}")
//    Call<List<Meal>> getAllMealsByCategory(@Path("id") String id);

//    @GET("api/json/v1/1/list.php?a=list")
//    Call<Area> getAllAreas();

    @GET("api/json/v1/1/list.php?i=list")
    Call<List<IngredientList>> getAllIngredients();

//    @GET("api/json/v1/1/list.php?c=list")
//    Call<Area> getAllCategories();

    @GET("images/ingredients/{name}.png")
    Call<String> getImageOfIngredient(@Path("name") String name);

    @GET("images/ingredients/{name}-Small.png")
    Call<String> getSmallImageOfIngredient(@Path("name") String name);

}