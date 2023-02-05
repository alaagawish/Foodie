package eg.gov.iti.jets.foodie.plan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import eg.gov.iti.jets.foodie.R;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.gov.iti.jets.foodie.R;

import eg.gov.iti.jets.foodie.model.Meal;


public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private static final String TAG = "DayAdapter";
    private List<Meal> allMeals;
    private Context context;
    private AllMealsClickListener allMealsClickListener;

    public DayAdapter(Context context, AllMealsClickListener allMealsClickListener) {
        super();
        this.context = context;
        this.allMealsClickListener = allMealsClickListener;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.planner_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new DayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder holder, int position) {
        Meal meal = allMeals.get(position);
        holder.mealNameTextView.setText(allMeals.get(position).getStrMeal());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealImageViewPlannerCard);
        holder.deleteMealImageViewPlannerCard.setOnClickListener(e -> {
            //delete from list
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
        TextView mealNameTextView;
        ImageView deleteMealImageViewPlannerCard, mealImageViewPlannerCard;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteMealImageViewPlannerCard = itemView.findViewById(R.id.deleteMealImageViewPlannerCard);
            mealImageViewPlannerCard = itemView.findViewById(R.id.mealImageViewPlannerCard);
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);


        }
    }
}
