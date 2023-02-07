package eg.gov.iti.jets.foodie.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MyResponse;
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
                .build();
        api_service = retrofit.create(API_Service.class);
        Call<List<Meal>> mealsByRandom = api_service.getMealsByRandom();
        Log.d(TAG, "startCall: ");
        mealsByRandom.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {

                Log.d(TAG, "onResponse: meals by random retrivied successfully");
//                networkDelegate.onSuccess(response.body().);
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                Log.d(TAG, "onFailure: on retriving meals by random" + t.getLocalizedMessage());
                networkDelegate.onFailure(t.getMessage());
            }
        });


    }
}

