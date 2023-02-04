package eg.gov.iti.jets.foodie.home.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import eg.gov.iti.jets.foodie.home.view.Slider;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;

public class HomeFragment extends Fragment {

    Button categoryButton;
    Button ingredientButton;
    Button regionButton;
    SliderView sliderView;
    SliderAdapter sliderAdapter;

    private ArrayList<Slider> sliderArrayList;


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
        sliderView = view.findViewById(R.id.imageSlider);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderArrayList = new ArrayList<>();
        sliderArrayList.add(new Slider("https://th.bing.com/th/id/R.4296f49fb527bdaf3039e31e1d0dcb2f?rik=0NrAyeJdAkOzwQ&pid=ImgRaw&r=0"));
        sliderArrayList.add(new Slider("https://westernnews.media.clients.ellingtoncms.com/img/photos/2018/02/09/Pancakes.jpeg"));
        sliderArrayList.add(new Slider("https://www.inliv.com/wp-content/uploads/2017/06/Untitled-design-e1530748897399.jpg"));
        sliderAdapter = new SliderAdapter(sliderArrayList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

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