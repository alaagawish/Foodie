package eg.gov.iti.jets.foodie.home.view;

import android.content.Context;
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
import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Meal;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "CategoryRecyclerViewAdapter";
    private List<Category> categories;
    private Context context;
    private HomeMealsClickListener homeMealsClickListener;

    public CategoryRecyclerViewAdapter(Context context, HomeMealsClickListener homeMealsClickListener) {
        super();
        categories = new ArrayList<>();
        this.context = context;
        this.homeMealsClickListener = homeMealsClickListener;
    }

    public void setAllCategories(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new CategoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryNameCardTextView.setText(categories.get(position).getStrCategory());
        Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.categoryNameCardImageView);

        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");
        holder.categoryConstraintLayout.setOnClickListener(e -> {
//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("meal", allMeals.get(position));
//            context.startActivity(intent);
            //new activity with category meals
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
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
