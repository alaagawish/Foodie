package eg.gov.iti.jets.foodie.home.view;

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

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.meals.view.MealsActivity;
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.IngredientList;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "IngredientRecyclerViewAdapter";
    private List<IngredientList> ingredient;
    private Context context;
    private HomeMealsClickListener homeMealsClickListener;

    public IngredientRecyclerViewAdapter(Context context, HomeMealsClickListener homeMealsClickListener) {
        super();
        ingredient = new ArrayList<>();
        this.context = context;
        this.homeMealsClickListener = homeMealsClickListener;
    }

    public void setAllIngredients(List<IngredientList> ingredient) {
        this.ingredient = ingredient;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new IngredientRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientList ingredientList = ingredient.get(position);

        holder.categoryNameCardTextView.setText(ingredient.get(position).getStrIngredient());

        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredient.get(position).getStrIngredient().replaceAll(" ", "%20") + ".png")
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.categoryNameCardImageView);

        holder.categoryConstraintLayout.setOnClickListener(e -> {
            Intent intent = new Intent(context, MealsActivity.class);
            intent.putExtra("name", ingredientList);
            intent.putExtra("type", "Ingredient");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryNameCardTextView;
        ImageView categoryNameCardImageView;
        ConstraintLayout categoryConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryNameCardTextView = itemView.findViewById(R.id.categoryNameCardTextView);
            categoryConstraintLayout = itemView.findViewById(R.id.categoryConstraintLayout);
            categoryNameCardImageView = itemView.findViewById(R.id.categoryNameCardImageView);
        }
    }
}

