package eg.gov.iti.jets.foodie.home.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.IngredientList;

public class IngredientRecyclerViewAdapter  extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

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
        View view = inflater.inflate(R.layout.country_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new IngredientRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientList ingredientList = ingredient.get(position);
        holder.countryCardTextView.setText(ingredient.get(position).getStrIngredient());
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");
        holder.countryCardTextView.setOnClickListener(e -> {
//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("meal", allMeals.get(position));
//            context.startActivity(intent);
            //new activity with category meals
        });
    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryCardTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            countryCardTextView = itemView.findViewById(R.id.countryCardTextView);
        }
    }
}

