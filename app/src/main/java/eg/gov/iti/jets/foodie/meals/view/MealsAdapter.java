package eg.gov.iti.jets.foodie.meals.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.details.view.DetailsActivity;
import eg.gov.iti.jets.foodie.home.view.HomeFragment;
import eg.gov.iti.jets.foodie.meals.view.MealsAdapter;
import eg.gov.iti.jets.foodie.home.view.HomeMealsClickListener;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.Ingredient;
import eg.gov.iti.jets.foodie.model.Meal;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    private static final String TAG = "MealsAdapter";
    private List<Meal> meals;
    private Context context;
    private MealsClickListener mealsClickListener;
    boolean flag;

    public MealsAdapter(Context context, MealsClickListener mealsClickListener, boolean flag) {
        super();
        meals = new ArrayList<>();
        this.context = context;
        this.mealsClickListener = mealsClickListener;
        this.flag = flag;
    }

    public void setAllMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slide_pager_home__card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new MealsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.slidePagerMealTextView.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.slidePagerImageView);
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");
        meal.setId(Integer.parseInt(meal.getIdMeal()));
        holder.mealCardConstraintLayout.setOnClickListener(e -> {

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("meal", meals.get(position));
            context.startActivity(intent);
            //new activity with category meals
        });
        holder.heartButton.setOnClickListener(e -> {
            if(flag) {
                if (!meal.isFav()) {
                    Log.d(TAG, "onBindViewHolder: add to fav");
                    meal.setFav(true);
                    holder.heartButton.setImageResource(R.drawable.baseline_favorite_24);

                } else {
                    Log.d(TAG, "onBindViewHolder: removed from fav");
                    meal.setFav(false);
                    holder.heartButton.setImageResource(R.drawable.baseline_favorite_border_24);
                }
                mealsClickListener.addFavor(meal);
            }
            else {
                View dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_guest, null);
                HomeFragment.searchDialog = new Dialog(context);
                HomeFragment.searchDialog.setContentView(dialogLayout);
                HomeFragment.searchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                HomeFragment.searchDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView slidePagerMealTextView;
        ImageView slidePagerImageView;
        ImageButton heartButton;

        ConstraintLayout mealCardConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            slidePagerMealTextView = itemView.findViewById(R.id.slidePagerMealTextView);
            slidePagerImageView = itemView.findViewById(R.id.slidePagerImageView);
            mealCardConstraintLayout = itemView.findViewById(R.id.mealCardConstraintLayout);
            heartButton = itemView.findViewById(R.id.heartButton);
        }
    }
}

