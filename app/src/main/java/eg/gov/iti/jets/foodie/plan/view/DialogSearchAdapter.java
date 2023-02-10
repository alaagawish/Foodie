package eg.gov.iti.jets.foodie.plan.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.search.view.SearchClickListener;

public class DialogSearchAdapter extends RecyclerView.Adapter<DialogSearchAdapter.ViewHolder> {

    private static final String TAG = "DialogSearchAdapter";
    private List<Meal> allMeals;
    private Context context;
    private AllMealsClickListener allMealsClickListener;
    private String day;

    public DialogSearchAdapter(Context context, AllMealsClickListener allMealsClickListener, String day) {
        super();
        this.context = context;
//        this.allMeals = new ArrayList<>();
        this.day = day;
        this.allMealsClickListener = allMealsClickListener;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public DialogSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_meal_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new DialogSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogSearchAdapter.ViewHolder holder, int position) {
        Meal meal = allMeals.get(position);
        holder.mealSearchedNameCardTextView.setText(allMeals.get(position).getStrMeal());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealSearchedCardImageView);

        holder.searchCardConstraintLayout.setOnClickListener(e -> {
            Log.d(TAG, "onBindViewHolder: ");
            meal.setId(Integer.parseInt(meal.getIdMeal()));
            meal.setDay(day);
            allMealsClickListener.addMealToDay(meal);
            Toast.makeText(context, meal.getStrMeal() + " is added to " + meal.getDay().toUpperCase(), Toast.LENGTH_LONG).show();
            PlanFragment.searchDialog.dismiss();
        });

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealSearchedNameCardTextView;
        ImageView mealSearchedCardImageView;
        ConstraintLayout searchCardConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealSearchedCardImageView = itemView.findViewById(R.id.mealSearchedCardImageView);
            mealSearchedNameCardTextView = itemView.findViewById(R.id.mealSearchedNameCardTextView);
            searchCardConstraintLayout = itemView.findViewById(R.id.searchCardConstraintLayout);


        }
    }
}