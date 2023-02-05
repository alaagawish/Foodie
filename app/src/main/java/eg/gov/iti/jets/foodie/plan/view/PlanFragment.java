package eg.gov.iti.jets.foodie.plan.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import eg.gov.iti.jets.foodie.model.Meal;


public class PlanFragment extends Fragment implements AllMealsClickListener {
    private static final String TAG = "PlanFragment";
    private RecyclerView saturdayRecyclerView, sundayRecyclerView, mondayRecyclerView, tuesdayRecyclerView, wednesdayRecyclerView, thursdayRecyclerView, fridayRecyclerView;
    private DayAdapter dayAdapter;
    private List<Meal> meals;
    private Dialog searchDialog;
    private MaterialButton saturdayAddMaterialButton, sundayAddMaterialButton, mondayAddMaterialButton, tuesdayAddMaterialButton, thursdayAddMaterialButton, wednesdayAddMaterialButton, fridayAddMaterialButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dumy();
        init(view);

        dayAdapter = new DayAdapter(getContext(), PlanFragment.this);
        dayAdapter.setAllMeals(meals);
        saturdayRecyclerView.setAdapter(dayAdapter);
        sundayRecyclerView.setAdapter(dayAdapter);
        mondayRecyclerView.setAdapter(dayAdapter);
        tuesdayRecyclerView.setAdapter(dayAdapter);
        wednesdayRecyclerView.setAdapter(dayAdapter);
        thursdayRecyclerView.setAdapter(dayAdapter);
        fridayRecyclerView.setAdapter(dayAdapter);
        dayAdapter.notifyDataSetChanged();

        saturdayAddMaterialButton.setOnClickListener(v -> {
            showDialog();
        });
        sundayAddMaterialButton.setOnClickListener(v -> {
            showDialog();
        });

    }

    public void dumy() {
        meals = new ArrayList<>();
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
        meals.add(new Meal("Meat", "https://th.bing.com/th/id/R.a86a695d7575310d4af66450ffe8ce1d?rik=7%2f4%2fov%2fN4ecSzQ&pid=ImgRaw&r=0"));
    }

    public void showDialog() {
        Log.d(TAG, "showDialog: ddddddddd");
        View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_search, null);
        searchDialog = new Dialog(getContext());
        searchDialog.setContentView(dialogLayout);
        searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        searchDialog.show();
        searchDialog.findViewById(R.id.searchDialogEditText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchDialogEditText = searchDialog.findViewById(R.id.searchDialogEditText);
                String searchText = searchDialogEditText.getText().toString().trim();
                if (searchText.isEmpty()) {
                    searchDialogEditText.setError("Enter a meal");
                } else {
//                    searchDialog.findViewById(R.id.searchResultRecyclerView).setVisibility(View.VISIBLE);
//                    searchDialog.dismiss();
                }
            }

        });
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
}