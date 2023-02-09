package eg.gov.iti.jets.foodie.details.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import eg.gov.iti.jets.foodie.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.gov.iti.jets.foodie.model.Ingredient;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private static final String TAG = "IngredientsAdapter";
    private List<Ingredient> allIngredients;
    private Context context;
    private AllIngredientsClickListener allIngredientsClickListener;

    public IngredientsAdapter(Context context, AllIngredientsClickListener allIngredientsClickListener) {
        super();
        allIngredients = new ArrayList<>();
        this.context = context;
        this.allIngredientsClickListener = allIngredientsClickListener;
    }

    public void setAllIngredients(List<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = allIngredients.get(position);
            holder.ingredientNameCardTextView.setText(allIngredients.get(position).getName());
            holder.ingredientAmountCardTextView.setText(allIngredients.get(position).getAmount());
            Glide.with(context).load(allIngredients.get(position).getThumbnail())
                    .apply(new RequestOptions().override(200, 160))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.ingredientCardImageView);


        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

    }

    @Override
    public int getItemCount() {
        return allIngredients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientAmountCardTextView, ingredientNameCardTextView;
        CircleImageView ingredientCardImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientCardImageView = itemView.findViewById(R.id.ingredientCardImageView);
            ingredientAmountCardTextView = itemView.findViewById(R.id.ingredientAmountCardTextView);
            ingredientNameCardTextView = itemView.findViewById(R.id.ingredientNameCardTextView);


        }
    }
}
