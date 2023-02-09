package eg.gov.iti.jets.foodie.details.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Ingredient;
import eg.gov.iti.jets.foodie.model.Meal;
import kr.co.prnd.readmore.ReadMoreTextView;

public class DetailsActivity extends AppCompatActivity implements AllIngredientsClickListener {
    private static final String TAG = "DetailsActivity";
    private YouTubePlayerView youTubePlayerView;
    private RecyclerView allIngredientsRecyclerView;
    private IngredientsAdapter ingredientsAdapter;
    private ImageButton backArrowCircularImageButton, likeCircularImageButton;
    private List<Ingredient> dumyIngredients;
    private ReadMoreTextView mealPreparationTextView;
    boolean favFlag = false;
    private Meal meal;
    private ImageView mealImageView;
    private TextView mealNameTextview, areaTextView, foodTypeTextView;
    String videoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        Intent intentt = getIntent();
        meal = (Meal) intentt.getSerializableExtra("meal");
        mealNameTextview.setText(meal.getStrMeal());

        Picasso.get().load(meal.getStrMealThumb()).into(mealImageView);

        ingredientsAdapter.setAllIngredients(meal.getIngredientList());
        allIngredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.notifyDataSetChanged();
        getLifecycle().addObserver(youTubePlayerView);
        mealPreparationTextView.setText(meal.getStrInstructions());
        areaTextView.setText(meal.getStrArea());
        foodTypeTextView.setText(meal.getStrCategory());
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
              videoId = meal.getStrYoutube().split("=")[1];
//                youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        backArrowCircularImageButton.setOnClickListener(e -> {
            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void AddFav(View view) {
        if (!favFlag) {
            favFlag = true;
            likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            favFlag = false;
            likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_border_24);

        }
    }

    public void init() {
        dumyIngredients = new ArrayList<>();
        youTubePlayerView = findViewById(R.id.youtubePlayerView);
        allIngredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        ingredientsAdapter = new IngredientsAdapter(this, this);
        backArrowCircularImageButton = findViewById(R.id.backArrowCircularImageButton);
        likeCircularImageButton = findViewById(R.id.likeCircularImageButton);
        mealNameTextview = findViewById(R.id.mealNameTextview);
        mealImageView = findViewById(R.id.mealImageView);
        mealPreparationTextView = findViewById(R.id.mealPreparationTextView);
        areaTextView = findViewById(R.id.areaTextView);
        foodTypeTextView = findViewById(R.id.foodTypeTextView);

    }

    public void dumy() {
        dumyIngredients.add(new Ingredient("Chicken", "360g", "https://www.unileverfoodsolutions.co.in/wp-content/uploads/2020/11/Featured-3.jpg"));
        dumyIngredients.add(new Ingredient("Chicken", "360g", "https://www.unileverfoodsolutions.co.in/wp-content/uploads/2020/11/Featured-3.jpg"));
        dumyIngredients.add(new Ingredient("Chicken", "360g", "https://www.unileverfoodsolutions.co.in/wp-content/uploads/2020/11/Featured-3.jpg"));
        dumyIngredients.add(new Ingredient("Chicken", "360g", "https://www.unileverfoodsolutions.co.in/wp-content/uploads/2020/11/Featured-3.jpg"));
        dumyIngredients.add(new Ingredient("Chicken", "360g", "https://www.unileverfoodsolutions.co.in/wp-content/uploads/2020/11/Featured-3.jpg"));
    }
}