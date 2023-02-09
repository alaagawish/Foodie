package eg.gov.iti.jets.foodie.network;

import android.util.Log;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import eg.gov.iti.jets.foodie.home.model.CategoryResponse;
import eg.gov.iti.jets.foodie.home.model.CountryResponse;
import eg.gov.iti.jets.foodie.home.model.IngredientResponse;
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
    String area = "canadian";
    String id = "11";
    String ingredient = "chicken";
    char c = 'a';
    String ingredientImgName = "";
    String searchName = "kosh";
    String ingredientName = "Salt", category = "Dessert";


    private API_Client() {
    }

    public synchronized static API_Client getInstance() {
        if (api_client == null) {
            api_client = new API_Client();
        }
        return api_client;
    }

    @Override
    public void enqueueCall(NetworkDelegation networkDelegate, String name, char c) {

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        api_service = retrofit.create(API_Service.class);
        if (c == 'a') {
            Single<MyResponse> allMealsByArea = api_service.getAllMealsByArea(name);
            allMealsByArea.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> {
                                Log.d(TAG, "enqueueCall: kkkkkkkkkk" + myResponse.getMeals().get(0).getStrMeal());
                                networkDelegate.onSuccessMealByFilter(myResponse.getMeals());
                            },
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));


        } else if (c == 'i') {
            Single<MyResponse> allMealsByIngredient = api_service.getAllMealsByIngredient(name);
            allMealsByIngredient.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> networkDelegate.onSuccessMealByFilter(myResponse.getMeals()),
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));

        } else if (c == 'c') {
            Single<MyResponse> allMealsByCategory = api_service.getAllMealsByCategory(name);
            allMealsByCategory.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> networkDelegate.onSuccessMealByFilter(myResponse.getMeals()),
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));

        }


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


//        Single<List<Meal>> allMealsBySearch = api_service.getAllMealsBySearch(searchName);
//        Single<Meal> allMealsByFirstLetter = api_service.getAllMealsByFirstLetter(c);
//        Single<Meal> mealById = api_service.getMealById(id);
        Single<MyResponse> mealsByRandom = api_service.getMealsByRandom();
        Single<CountryResponse> countries = api_service.getAllCountries();
        Single<CategoryResponse> allCategories = api_service.getAllCategories();
        Single<IngredientResponse> allIngredients = api_service.getAllIngredients();
//        Single<String> largeImage = api_service.getImageOfIngredient(ingredientName);
//        Single<String> smallImage = api_service.getSmallImageOfIngredient(ingredientImgName);
        Log.d(TAG, "startCall: ");


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

        allIngredients.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredientResponse -> networkDelegate.onSuccessIngredient(ingredientResponse.getMeals()),
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));

//        largeImage.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessString(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//
//        smallImage.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> networkDelegate.onSuccessString(meals),
//                        throwable -> networkDelegate.onFailure(throwable.getMessage()));


//              use disposter to close all observers
    }
}

