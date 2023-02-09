package eg.gov.iti.jets.foodie.network;

import android.util.Log;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import eg.gov.iti.jets.foodie.home.model.CategoryResponse;
import eg.gov.iti.jets.foodie.home.model.CountryResponse;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealByFilter;
import eg.gov.iti.jets.foodie.model.MyResponse;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSource {
    API_Service api_service;
    private static final String BASE_URL = "https://themealdb.com/";
    private static final String TAG = "API_Client";
    private static API_Client api_client = null;
    String area = "Egypt";
    String id = "11";
    String ingredient = "";
    char c = 'a';
    String ingredientImgName = "";
    String searchName = "kosh";
    String ingredientName = "Salt", category = "Desert";


    private API_Client() {
    }

    public synchronized static API_Client getInstance() {
        if (api_client == null) {
            api_client = new API_Client();
        }
        return api_client;
    }

    @Override
    public void enqueueCall(NetworkDelegation networkDelegate) {

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        api_service = retrofit.create(API_Service.class);

//        Single<List<MealByFilter>> allMealsByArea = api_service.getAllMealsByArea(area);
//        Single<List<MealByFilter>> allMealsByIngredient = api_service.getAllMealsByIngredient(ingredient);
//        Single<List<MealByFilter>> allMealsByCategory = api_service.getAllMealsByCategory(category);
//        Single<List<Meal>> allMealsBySearch = api_service.getAllMealsBySearch(searchName);
//        Single<Meal> allMealsByFirstLetter = api_service.getAllMealsByFirstLetter(c);
//        Single<Meal> mealById = api_service.getMealById(id);
        Single<MyResponse> mealsByRandom = api_service.getMealsByRandom();
        Single<CountryResponse> countries = api_service.getAllCountries();
        Single<CategoryResponse> allCategories = api_service.getAllCategories();
//        Single<List<IngredientList>> allIngredients = api_service.getAllIngredients();
//        Single<String> largeImage = api_service.getImageOfIngredient(ingredientName);
//        Single<String> smallImage = api_service.getSmallImageOfIngredient(ingredientImgName);
        Log.d(TAG, "startCall: ");

//        allMealsByArea.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessMealByFilter(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));

//        allMealsByIngredient.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessMealByFilter(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//        allMealsByCategory.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessMealByFilter(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//
//        allMealsBySearch.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccess(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//
//        allMealsByFirstLetter.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessMeal(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//        mealById.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessMeal(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//
        mealsByRandom.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            Log.d(TAG, "enqueueCall: random " + myResponse.getMeals().get(0).getStrMeal());
                            networkDelegate.onSuccess(myResponse.getMeals());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));

        countries.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
//                            Log.d(TAG, "enqueueCall country: " + myResponse.getCountries().get(0).getStrArea());
                            networkDelegate.onSuccessCountries(myResponse.getCountries());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));


        allCategories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                            Log.d(TAG, "enqueueCall: category " + categories.getCategories().get(0).getStrCategory());
                            networkDelegate.onSuccessCategory(categories.getCategories());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//        allIngredients.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(ingredientLists -> networkDelegate.onSuccessIngredient(ingredientLists),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//        largeImage.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessString(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//        smallImage.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessString(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
    }
}

