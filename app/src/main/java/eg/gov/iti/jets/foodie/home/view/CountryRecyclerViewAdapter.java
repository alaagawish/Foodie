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
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.Meal;

public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "CountryRecyclerViewAdapter";
    private List<Country> countries;
    private Context context;
    private HomeMealsClickListener homeMealsClickListener;

    public CountryRecyclerViewAdapter(Context context, HomeMealsClickListener homeMealsClickListener) {
        super();
        countries = new ArrayList<>();
        this.context = context;
        this.homeMealsClickListener = homeMealsClickListener;
    }

    public void setAllCountries(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.country_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new CountryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerViewAdapter.ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.countryCardTextView.setText(countries.get(position).getStrArea());
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
        return countries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryCardTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            countryCardTextView = itemView.findViewById(R.id.countryCardTextView);
        }
    }
}

