package eg.gov.iti.jets.foodie.plan.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.favourites.presenter.FavouritePresenter;
import eg.gov.iti.jets.foodie.favourites.view.FavouriteAdapter;
import eg.gov.iti.jets.foodie.favourites.view.FavouritesFragment;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;
import eg.gov.iti.jets.foodie.plan.presenter.PlanPresenter;
import eg.gov.iti.jets.foodie.plan.presenter.PlanPresenterInterface;
import eg.gov.iti.jets.foodie.search.view.SearchFragment;
import eg.gov.iti.jets.foodie.search.view.SearchedMealsAdapter;


public class PlanFragment extends Fragment implements AllMealsClickListener, PlanViewInterface {
    private static final String TAG = "PlanFragment";
    private RecyclerView saturdayRecyclerView, sundayRecyclerView, mondayRecyclerView, tuesdayRecyclerView, wednesdayRecyclerView, thursdayRecyclerView, fridayRecyclerView;
    private DayAdapter wednesdayAdapter, saturdayAdapter, sundayAdapter, mondayAdapter, tuesdayAdapter, thursdayAdapter, fridayAdapter;
    private List<Meal> allMeals;
    public static Dialog searchDialog;
    private String day;
    private PlanPresenterInterface planPresenterInterface;
    private MaterialButton saturdayAddMaterialButton, sundayAddMaterialButton, mondayAddMaterialButton, tuesdayAddMaterialButton, thursdayAddMaterialButton, wednesdayAddMaterialButton, fridayAddMaterialButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dumy();
        init(view);
        planPresenterInterface = new PlanPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));
        allMeals = new ArrayList<>();
        day = null;
        saturdayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        sundayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        mondayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        tuesdayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        thursdayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        wednesdayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        fridayAdapter = new DayAdapter(getContext(), PlanFragment.this);


        planPresenterInterface.getPlannedDayMeals("saturday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                saturdayAdapter.setAllMeals(meals);
                saturdayRecyclerView.setAdapter(saturdayAdapter);
                saturdayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("sunday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                sundayAdapter.setAllMeals(meals);
                sundayRecyclerView.setAdapter(sundayAdapter);
                sundayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("monday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mondayAdapter.setAllMeals(meals);
                mondayRecyclerView.setAdapter(mondayAdapter);
                mondayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("tuesday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                tuesdayAdapter.setAllMeals(meals);
                tuesdayRecyclerView.setAdapter(tuesdayAdapter);
                tuesdayAdapter.notifyDataSetChanged();

            }
        });
        planPresenterInterface.getPlannedDayMeals("wednesday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                wednesdayAdapter.setAllMeals(meals);
                wednesdayRecyclerView.setAdapter(wednesdayAdapter);
                wednesdayAdapter.notifyDataSetChanged();

            }
        });
        planPresenterInterface.getPlannedDayMeals("thursday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                thursdayAdapter.setAllMeals(meals);
                thursdayRecyclerView.setAdapter(thursdayAdapter);
                thursdayAdapter.notifyDataSetChanged();

            }
        });

        planPresenterInterface.getPlannedDayMeals("friday").observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                fridayAdapter.setAllMeals(meals);
                fridayRecyclerView.setAdapter(fridayAdapter);
                fridayAdapter.notifyDataSetChanged();

            }
        });


//        observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                favMeals.clear();
//                for (Meal meal: meals)
//                {
//                    if(meal.isFav())
//                        favMeals.add(meal);
//                }
//                favouriteAdapter.setAllMeals(favMeals);
//                favouriteRecyclerView.setAdapter(favouriteAdapter);
//                favouriteAdapter.notifyDataSetChanged();
//            }
//        });

        saturdayAddMaterialButton.setOnClickListener(v -> {
            day = "saturday";
            showDialog(day);
        });
        sundayAddMaterialButton.setOnClickListener(v -> {
            day = "sunday";
            showDialog(day);
        });
        mondayAddMaterialButton.setOnClickListener(v -> {
            day = "monday";
            showDialog(day);

        });
        tuesdayAddMaterialButton.setOnClickListener(v -> {
            day = "tuesday";
            showDialog(day);

        });
        wednesdayAddMaterialButton.setOnClickListener(v -> {
            day = "wednesday";
            showDialog(day);
        });
        thursdayAddMaterialButton.setOnClickListener(v -> {
            day = "thursday";
            showDialog(day);

        });
        fridayAddMaterialButton.setOnClickListener(v -> {
            day = "friday";
            showDialog(day);

        });

    }

    public void showDialog(String day) {
        View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_search, null);
        searchDialog = new Dialog(getContext());
        searchDialog.setContentView(dialogLayout);
        searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        searchDialog.show();
        RecyclerView searchResultCardRecyclerView = searchDialog.findViewById(R.id.searchResultCardRecyclerView);
        DialogSearchAdapter dialogSearchAdapter = new DialogSearchAdapter(getContext(), PlanFragment.this, day);

        EditText searchDialogEditText = searchDialog.findViewById(R.id.searchDialogEditText);

        searchDialogEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planPresenterInterface.getSearchedMeals(charSequence + "");
                try {
                    Thread.sleep(700);

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                dialogSearchAdapter.setAllMeals(allMeals);
                searchResultCardRecyclerView.setAdapter(dialogSearchAdapter);
                searchResultCardRecyclerView.setVisibility(View.VISIBLE);
                dialogSearchAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @Override
    public void showMeals(List<Meal> meals) {
        if (meals != null) {
            allMeals.clear();
            for (Meal meal : meals)
                allMeals.add(meal);
        }
    }

    public void init(View view) {
        saturdayRecyclerView = view.findViewById(R.id.saturdayRecyclerView);
        sundayRecyclerView = view.findViewById(R.id.sundayRecyclerView);
        mondayRecyclerView = view.findViewById(R.id.mondayRecyclerView);
        tuesdayRecyclerView = view.findViewById(R.id.tuesdayRecyclerView);
        wednesdayRecyclerView = view.findViewById(R.id.wednesdayRecyclerView);
        thursdayRecyclerView = view.findViewById(R.id.thursdayRecyclerView);
        fridayRecyclerView = view.findViewById(R.id.fridayRecyclerView);
        saturdayAddMaterialButton = view.findViewById(R.id.saturdayAddMaterialButton);
        sundayAddMaterialButton = view.findViewById(R.id.sundayAddMaterialButton);
        mondayAddMaterialButton = view.findViewById(R.id.mondayAddMaterialButton);
        tuesdayAddMaterialButton = view.findViewById(R.id.tuesdayAddMaterialButton);
        wednesdayAddMaterialButton = view.findViewById(R.id.wednesdayAddMaterialButton);
        thursdayAddMaterialButton = view.findViewById(R.id.thursdayAddMaterialButton);
        fridayAddMaterialButton = view.findViewById(R.id.fridayAddMaterialButton);
    }

    @Override
    public void addMealToDay(Meal meal) {

        planPresenterInterface.addMealPlanner(meal);
    }

    @Override
    public void deleteMealFromDay(Meal meal) {
        planPresenterInterface.deleteMeal(meal);
        saturdayAdapter.notifyDataSetChanged();
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
    }
}