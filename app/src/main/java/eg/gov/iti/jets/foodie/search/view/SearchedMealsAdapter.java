package eg.gov.iti.jets.foodie.search.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.details.view.DetailsActivity;
import eg.gov.iti.jets.foodie.meals.view.MealsActivity;
import eg.gov.iti.jets.foodie.model.Meal;


public class SearchedMealsAdapter extends RecyclerView.Adapter<SearchedMealsAdapter.ViewHolder> {

    private static final String TAG = "SearchedMealsAdapter";
    private List<Meal> allMeals;
    private Context context;
    private SearchClickListener searchClickListener;

    public SearchedMealsAdapter(Context context, SearchClickListener searchClickListener) {
        super();
        this.context = context;
        this.searchClickListener = searchClickListener;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public SearchedMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_meal_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new SearchedMealsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedMealsAdapter.ViewHolder holder, int position) {
        Meal meal = allMeals.get(position);
        holder.mealSearchedNameCardTextView.setText(allMeals.get(position).getStrMeal());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealSearchedCardImageView);

        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");
        holder.searchCardConstraintLayout.setOnClickListener(e -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("meal", meal);
            context.startActivity(intent);
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