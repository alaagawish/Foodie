package eg.gov.iti.jets.foodie.home.view;

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
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.model.Meal;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "HomeRecyclerViewAdapter";
    private List<Meal> allMeals;
    private Context context;
    private HomeMealsClickListener homeMealsClickListener;

    public HomeRecyclerViewAdapter(Context context, HomeMealsClickListener homeMealsClickListener) {
        super();
        this.context = context;
        this.homeMealsClickListener = homeMealsClickListener;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public HomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slide_pager_home__card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new HomeRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.ViewHolder holder, int position) {
        Meal meal = allMeals.get(position);
        holder.slidePagerMealTextView.setText(allMeals.get(position).getStrMeal());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.slidePagerImageView);
        holder.heartButton.setOnClickListener(e -> {
            //add to favourite list
            allMeals.add(meal);
            notifyDataSetChanged();
        });
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView slidePagerMealTextView;
        ImageView slidePagerImageView;
        ImageView heartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            slidePagerMealTextView = itemView.findViewById(R.id.slidePagerMealTextView);
            slidePagerImageView = itemView.findViewById(R.id.slidePagerImageView);
            heartButton = itemView.findViewById(R.id.heartButton);
        }
    }
}
