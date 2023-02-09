package eg.gov.iti.jets.foodie.network;

import java.util.List;

import eg.gov.iti.jets.foodie.home.model.CategoryResponse;
import eg.gov.iti.jets.foodie.home.model.CountryResponse;
import eg.gov.iti.jets.foodie.home.model.IngredientResponse;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;
import eg.gov.iti.jets.foodie.model.MealByIngredient;
import eg.gov.iti.jets.foodie.model.MyResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_Service {
    @GET("api/json/v1/1/filter.php?")
    Single<MyResponse> getAllMealsByArea(@Query("a") String area);


    @GET("api/json/v1/1/filter.php?")
    Single<MyResponse> getAllMealsByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php?")
    Single<MyResponse> getAllMealsByCategory(@Query("c") String category);

    @GET("api/json/v1/1/search.php?")
    Single<MyResponse> getAllMealsBySearch(@Query("s") String name);


    @GET("api/json/v1/1/search.php?f={c}")
    Single<Meal> getAllMealsByFirstLetter(@Path("c") char c);


    @GET("api/json/v1/1/lookup.php?i={id}")
    Single<Meal> getMealById(@Path("id") String id);


    @GET("api/json/v1/1/random.php")
    Single<MyResponse> getMealsByRandom();

    @GET("api/json/v1/1/list.php?a=list")
    Single<CountryResponse> getAllCountries();

    @GET("api/json/v1/1/categories.php")
    Single<CategoryResponse> getAllCategories();

    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getAllIngredients();

//    @GET("api/json/v1/1/lookup.php?i={id}")
//    Call<List<Meal>> getAllMealsByCategory(@Path("id") String id);

//    @GET("api/json/v1/1/list.php?a=list")
//    Call<Area> getAllAreas();

//    @GET("api/json/v1/1/list.php?c=list")
//    Call<Area> getAllCategories();

    @GET("images/ingredients/{name}.png")
    Single<String> getImageOfIngredient(@Path("name") String name);

    @GET("images/ingredients/{name}-Small.png")
    Single<String> getSmallImageOfIngredient(@Path("name") String name);

}