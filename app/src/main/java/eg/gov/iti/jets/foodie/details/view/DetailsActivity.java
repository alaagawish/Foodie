package eg.gov.iti.jets.foodie.details.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.details.presenter.DetailsPresenter;
import eg.gov.iti.jets.foodie.details.presenter.DetailsPresenterInterface;
import eg.gov.iti.jets.foodie.home.view.HomeFragment;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
import eg.gov.iti.jets.foodie.meals.presenter.MealsPresenter;
import eg.gov.iti.jets.foodie.model.Ingredient;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;
import kr.co.prnd.readmore.ReadMoreTextView;

public class DetailsActivity extends AppCompatActivity implements MealClickListener, DetailsViewInterface, AllIngredientsClickListener {
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
    DetailsPresenterInterface detailsPresenterInterface;
    SharedPreferences sharedPreferences1;

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
        sharedPreferences1 = getSharedPreferences(LoginActivity.PREF, MODE_PRIVATE);
        if (!meal.isFav()) {
            likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_border_24);
        } else {
            likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_24);
        }

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                if (meal.getStrYoutube() != null && !meal.getStrYoutube().equals("")) {
                    videoId = meal.getStrYoutube().split("=")[1];
//                youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.cueVideo(videoId, 0);
                } else {
                    youTubePlayer.cueVideo("qdhWz7qAaCU", 0);
                }

            }
        });

        backArrowCircularImageButton.setOnClickListener(e -> {
            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void AddFav(View view) {
        if (sharedPreferences1.getString(LoginActivity.EMAIL, "NOT FOUND").equals("NOT FOUND")) {
            View dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_guest, null);
            HomeFragment.searchDialog = new Dialog(this);
            HomeFragment.searchDialog.setContentView(dialogLayout);
            HomeFragment.searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            HomeFragment.searchDialog.show();
        } else {
            meal.setId(Integer.parseInt(meal.getIdMeal()));
            if (!meal.isFav()) {
                meal.setFav(true);
                likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_24);

            } else {
                meal.setFav(false);
                likeCircularImageButton.setImageResource(R.drawable.baseline_favorite_border_24);
            }
            addMealToFav(meal);
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
        detailsPresenterInterface = new DetailsPresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(this), this));

    }

    @Override
    public void addMealToFav(Meal meal) {
        detailsPresenterInterface.addFavouriteMeal(meal);
    }
}