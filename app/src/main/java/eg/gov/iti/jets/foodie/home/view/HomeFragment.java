package eg.gov.iti.jets.foodie.home.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eg.gov.iti.jets.foodie.R;

public class HomeFragment extends Fragment {

    Button categoryButton;
    Button ingredientButton;
    Button regionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryButton = view.findViewById(R.id.categoryButton);
        ingredientButton = view.findViewById(R.id.ingredientButton);
        regionButton = view.findViewById(R.id.regionButton);

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryButton.setBackgroundResource(R.color.green);
                ingredientButton.setBackgroundResource(R.color.transparent);
                regionButton.setBackgroundResource(R.color.transparent);
            }
        });

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryButton.setBackgroundResource(R.color.transparent);
                ingredientButton.setBackgroundResource(R.color.green);
                regionButton.setBackgroundResource(R.color.transparent);
            }
        });

        regionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryButton.setBackgroundResource(R.color.transparent);
                ingredientButton.setBackgroundResource(R.color.transparent);
                regionButton.setBackgroundResource(R.color.green);
            }
        });
    }
}