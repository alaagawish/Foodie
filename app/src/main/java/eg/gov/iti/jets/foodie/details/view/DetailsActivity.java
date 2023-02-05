package eg.gov.iti.jets.foodie.details.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Ingredient;

public class DetailsActivity extends AppCompatActivity implements AllIngredientsClickListener {
    YouTubePlayerView youTubePlayerView;
    RecyclerView allIngredientsRecyclerView;
    IngredientsAdapter ingredientsAdapter;
    ImageButton backArrowCircularImageButton,likeCircularImageButton;
    List<Ingredient> dumyIngredients;
    boolean favFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        dumy();

        ingredientsAdapter.setAllIngredients(dumyIngredients);
        allIngredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.notifyDataSetChanged();
        getLifecycle().addObserver(youTubePlayerView);
        backArrowCircularImageButton.setOnClickListener(e -> {
            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "SuehATaxMEQ";
//                youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.cueVideo(videoId, 0);
            }
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
        likeCircularImageButton=findViewById(R.id.likeCircularImageButton);
    }

    public void dumy() {
        dumyIngredients.add(new Ingredient("kiwi", "129g", "https://th.bing.com/th/id/R.b4c59039ce8b34a01ffb09e53c19dccb?rik=ZqKmxcf4pVLZ5Q&pid=ImgRaw&r=0"));
        dumyIngredients.add(new Ingredient("kiwi", "129g", "https://th.bing.com/th/id/R.b4c59039ce8b34a01ffb09e53c19dccb?rik=ZqKmxcf4pVLZ5Q&pid=ImgRaw&r=0"));
        dumyIngredients.add(new Ingredient("kiwi", "129g", "https://th.bing.com/th/id/R.b4c59039ce8b34a01ffb09e53c19dccb?rik=ZqKmxcf4pVLZ5Q&pid=ImgRaw&r=0"));
        dumyIngredients.add(new Ingredient("kiwi", "129g", "https://th.bing.com/th/id/R.b4c59039ce8b34a01ffb09e53c19dccb?rik=ZqKmxcf4pVLZ5Q&pid=ImgRaw&r=0"));
        dumyIngredients.add(new Ingredient("kiwi", "129g", "https://th.bing.com/th/id/R.b4c59039ce8b34a01ffb09e53c19dccb?rik=ZqKmxcf4pVLZ5Q&pid=ImgRaw&r=0"));
    }
}