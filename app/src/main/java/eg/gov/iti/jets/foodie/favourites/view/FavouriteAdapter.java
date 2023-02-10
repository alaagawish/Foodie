package eg.gov.iti.jets.foodie.favourites.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.home.view.HomeMealsClickListener;
import eg.gov.iti.jets.foodie.model.Meal;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private static final String TAG = "FavouriteAdapter";
    private List<Meal> allMeals;
    private Context context;
    private FavouriteMealsClickListener favouriteMealsClickListener;

    public FavouriteAdapter(Context context, FavouriteMealsClickListener favouriteMealsClickListener) {
        super();
        this.context = context;
        this.favouriteMealsClickListener = favouriteMealsClickListener;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourite_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new FavouriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        Meal meal = allMeals.get(position);
        holder.favouriteMealTextView.setText(allMeals.get(position).getStrMeal());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.favouriteImageView);
        holder.favouriteHeartImageButton.setOnClickListener(e -> {
            //remove from favourite list
            allMeals.remove(meal);
            notifyDataSetChanged();
        });
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView favouriteMealTextView;
        ImageView favouriteImageView;
        ImageButton favouriteHeartImageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            favouriteMealTextView = itemView.findViewById(R.id.favouriteMealTextView);
            favouriteImageView = itemView.findViewById(R.id.favouriteImageView);
            favouriteHeartImageButton = itemView.findViewById(R.id.favouriteHeartImageButton);
        }
    }
}