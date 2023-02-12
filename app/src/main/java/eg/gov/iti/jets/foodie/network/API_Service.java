package eg.gov.iti.jets.foodie.network;

import eg.gov.iti.jets.foodie.home.model.CategoryResponse;
import eg.gov.iti.jets.foodie.home.model.CountryResponse;
import eg.gov.iti.jets.foodie.home.model.IngredientResponse;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MyResponse;
import io.reactivex.rxjava3.core.Single;
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


    @GET("api/json/v1/1/lookup.php?")
    Single<MyResponse> getMealById(@Query("i") String id);


    @GET("api/json/v1/1/random.php")
    Single<MyResponse> getMealsByRandom();

    @GET("api/json/v1/1/list.php?a=list")
    Single<CountryResponse> getAllCountries();

    @GET("api/json/v1/1/categories.php")
    Single<CategoryResponse> getAllCategories();

    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getAllIngredients();

}