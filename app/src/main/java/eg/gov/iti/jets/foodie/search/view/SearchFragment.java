package eg.gov.iti.jets.foodie.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.home.presenter.HomePresenter;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;
import eg.gov.iti.jets.foodie.plan.view.DayAdapter;
import eg.gov.iti.jets.foodie.plan.view.PlanFragment;
import eg.gov.iti.jets.foodie.search.presenter.SearchPresenter;
import eg.gov.iti.jets.foodie.search.presenter.SearchPresenterInterface;


public class SearchFragment extends Fragment implements SearchClickListener, SearchViewInterface {
    private static final String TAG = "SearchFragment";
    RecyclerView searchResultRecyclerView;
    SearchedMealsAdapter searchedMealsAdapter;
    EditText searchEditText;
    List<Meal> meals;
    SearchPresenterInterface searchPresenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dumy();
        init(view);
        searchPresenterInterface = new SearchPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "onTextChanged: " + charSequence);


                searchPresenterInterface.getSearchedMeals(charSequence + "");


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    public void init(View view) {
        searchResultRecyclerView = view.findViewById(R.id.searchResultRecyclerView);
        searchEditText = view.findViewById(R.id.searchEditText);
    }

    @Override
    public void showMeals(List<Meal> meals) {
        if (meals != null) {
            Log.d(TAG, "showMeals: " + meals.get(0).getStrMeal());
            searchedMealsAdapter = new SearchedMealsAdapter(getContext(), SearchFragment.this);
            searchedMealsAdapter.setAllMeals(meals);
            searchResultRecyclerView.setAdapter(searchedMealsAdapter);
        }
    }


}