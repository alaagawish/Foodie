package eg.gov.iti.jets.foodie.network;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
    String id = "11";
    int cacheSize = 10 * 1024 * 1024;
    Cache cache;
    OkHttpClient okHttpClient;
    String ingredientImgName = "";
    String ingredientName = "Salt";

    Gson gson;
    Retrofit.Builder builder;
    Retrofit retrofit;

    private API_Client() {
        cache = new Cache(MainActivity.cacheFile, cacheSize);
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        gson = new GsonBuilder().create();
        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create());
        retrofit = builder.build();


    }

    public synchronized static API_Client getInstance() {
        if (api_client == null) {
            api_client = new API_Client();

        }
        return api_client;
    }

    @Override
    public void enqueueCallById(NetworkDelegation networkDelegate, String id) {
        Single<MyResponse> mealById = api_service.getMealById(id);
        Disposable d = mealById.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            networkDelegate.onSuccess(response.getMeals());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//        d.dispose();
    }

    @Override
    public void enqueueCall(NetworkDelegation networkDelegate, String name, char c) {

        api_service = retrofit.create(API_Service.class);
        if (c == 'a') {
            Single<MyResponse> allMealsByArea = api_service.getAllMealsByArea(name);
            Disposable d1 = allMealsByArea.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> {
                                Log.d(TAG, "enqueueCall: " + myResponse.getMeals().get(0).getStrMeal());
                                networkDelegate.onSuccessMealByFilter(myResponse.getMeals());
                            },
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));

//            d1.dispose();
        } else if (c == 'i') {
            Single<MyResponse> allMealsByIngredient = api_service.getAllMealsByIngredient(name);
            Disposable d = allMealsByIngredient.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> networkDelegate.onSuccessMealByFilter(myResponse.getMeals()),
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));
//            d.dispose();
        } else if (c == 'c') {
            Single<MyResponse> allMealsByCategory = api_service.getAllMealsByCategory(name);
            Disposable d = allMealsByCategory.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myResponse -> networkDelegate.onSuccessMealByFilter(myResponse.getMeals()),
                            throwable -> networkDelegate.onFailure(throwable.getMessage()));
//            d.dispose();
        }


    }

    @Override
    public void enqueueCallBySearch(NetworkDelegation networkDelegate, String search) {

        api_service = retrofit.create(API_Service.class);

        Single<MyResponse> allMealsBySearch = api_service.getAllMealsBySearch(search);

        Disposable d = allMealsBySearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            Log.d(TAG, "enqueueCall: search ");
                            networkDelegate.onSuccess(myResponse.getMeals());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//        d.dispose();
    }

    @Override
    public void enqueueCall(NetworkDelegation networkDelegate) {

        api_service = retrofit.create(API_Service.class);


        Single<MyResponse> mealsByRandom = api_service.getMealsByRandom();
        Single<CountryResponse> countries = api_service.getAllCountries();
        Single<CategoryResponse> allCategories = api_service.getAllCategories();
        Single<IngredientResponse> allIngredients = api_service.getAllIngredients();
        Log.d(TAG, "startCall: ");

        Disposable d3 = mealsByRandom.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            Log.d(TAG, "enqueueCall: random " + myResponse.getMeals().get(0).getStrMeal());
                            networkDelegate.onSuccess(myResponse.getMeals());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));

        Disposable d2 = countries.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            networkDelegate.onSuccessCountries(myResponse.getCountries());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));


        Disposable d1 = allCategories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                            Log.d(TAG, "enqueueCall: category " + categories.getCategories().get(0).getStrCategory());
                            networkDelegate.onSuccessCategory(categories.getCategories());
                        },
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));

        Disposable d = allIngredients.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredientResponse -> networkDelegate.onSuccessIngredient(ingredientResponse.getMeals()),
                        throwable -> networkDelegate.onFailure(throwable.getMessage()));
//        d.dispose();
//        d1.dispose();
//        d2.dispose();
//        d3.dispose();

    }
}