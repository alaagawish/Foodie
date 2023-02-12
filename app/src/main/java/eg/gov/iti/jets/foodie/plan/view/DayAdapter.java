package eg.gov.iti.jets.foodie.plan.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
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

import eg.gov.iti.jets.foodie.R;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.gov.iti.jets.foodie.R;

import eg.gov.iti.jets.foodie.details.view.DetailsActivity;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
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
        Log.d(TAG, "onBindViewHolder: dddddddd " + meal.getDay());
        holder.deleteMealImageViewPlannerCard.setOnClickListener(e -> {
            Log.d(TAG, "onBindViewHolder: delete " + meal.getStrMeal());
            allMealsClickListener.deleteMealFromDay(meal);
            notifyDataSetChanged();
        });
        holder.planCardConstraintLayout.setOnClickListener(e -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("meal", meal);
            context.startActivity(intent);
        });
        holder.addMealToCalenderImageView.setOnClickListener(e -> {
            if (!meal.getDay().equals("temp")) {
                Intent i = new Intent(Intent.ACTION_INSERT);
                i.setData(CalendarContract.Events.CONTENT_URI);
                i.putExtra(CalendarContract.Events.TITLE, meal.getStrMeal() + " is the meal of the day");
                i.putExtra(CalendarContract.Events.DESCRIPTION, " ");
                SharedPreferences sharedPreferences = context.getSharedPreferences(LoginActivity.PREF, Context.MODE_PRIVATE);
                i.putExtra(Intent.EXTRA_EMAIL, sharedPreferences.getString(LoginActivity.EMAIL, " "));
                if (i.resolveActivity(context.getPackageManager()) != null)
                    context.startActivity(i);
                else Toast.makeText(context, "Can't save the event..", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Error to save this Event", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView;
        ImageView deleteMealImageViewPlannerCard, mealImageViewPlannerCard, addMealToCalenderImageView;
        ConstraintLayout planCardConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            deleteMealImageViewPlannerCard = itemView.findViewById(R.id.deleteMealImageViewPlannerCard);
            mealImageViewPlannerCard = itemView.findViewById(R.id.mealImageViewPlannerCard);
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            planCardConstraintLayout = itemView.findViewById(R.id.planCardConstraintLayout);
            addMealToCalenderImageView = itemView.findViewById(R.id.addMealToCalenderImageView);

        }
    }

//    public int numberOfDays(String dayy) {
//        Log.d(TAG, "numberOfDays: " + day);
//        int number = 0;
//        if (day.equals("saturday")) {
//
//            if (dayy.equals("saturday")) {
//                number = 0;
//            } else if (dayy.equals("sunday")) {
//                number = 1;
//            } else if (dayy.equals("monday")) {
//                number = 2;
//            } else if (dayy.equals("tuesday")) {
//                number = 3;
//            } else if (dayy.equals("wednesday")) {
//                number = 4;
//            } else if (dayy.equals("thursday")) {
//                number = 5;
//            } else if (dayy.equals("friday")) {
//                number = 6;
//            }
//        } else if (day.equals("sunday")) {
//            if (dayy.equals("saturday")) {
//                number = 6;
//            } else if (dayy.equals("sunday")) {
//                number = 0;
//            } else if (dayy.equals("monday")) {
//                number = 1;
//            } else if (dayy.equals("tuesday")) {
//                number = 2;
//            } else if (dayy.equals("wednesday")) {
//                number = 3;
//            } else if (dayy.equals("thursday")) {
//                number = 4;
//            } else if (dayy.equals("friday")) {
//                number = 5;
//            }
//        } else if (day.equals("monday")) {
//            if (dayy.equals("saturday")) {
//                number = 5;
//            } else if (dayy.equals("sunday")) {
//                number = 6;
//            } else if (dayy.equals("monday")) {
//                number = 0;
//            } else if (dayy.equals("tuesday")) {
//                number = 1;
//            } else if (dayy.equals("wednesday")) {
//                number = 2;
//            } else if (dayy.equals("thursday")) {
//                number = 3;
//            } else if (dayy.equals("friday")) {
//                number = 4;
//            }
//        } else if (day.equals("tuesday")) {
//            if (dayy.equals("saturday")) {
//                number = 4;
//            } else if (dayy.equals("sunday")) {
//                number = 5;
//            } else if (dayy.equals("monday")) {
//                number = 6;
//            } else if (dayy.equals("tuesday")) {
//                number = 0;
//            } else if (dayy.equals("wednesday")) {
//                number = 1;
//            } else if (dayy.equals("thursday")) {
//                number = 2;
//            } else if (dayy.equals("friday")) {
//                number = 3;
//            }
//        } else if (day.equals("wednesday")) {
//            if (dayy.equals("saturday")) {
//                number = 3;
//            } else if (dayy.equals("sunday")) {
//                number = 4;
//            } else if (dayy.equals("monday")) {
//                number = 5;
//            } else if (dayy.equals("tuesday")) {
//                number = 6;
//            } else if (dayy.equals("wednesday")) {
//                number = 0;
//            } else if (dayy.equals("thursday")) {
//                number = 1;
//            } else if (dayy.equals("friday")) {
//                number = 2;
//            }
//        } else if (day.equals("thursday")) {
//            if (dayy.equals("saturday")) {
//                number = 2;
//            } else if (dayy.equals("sunday")) {
//                number = 3;
//            } else if (dayy.equals("monday")) {
//                number = 4;
//            } else if (dayy.equals("tuesday")) {
//                number = 5;
//            } else if (dayy.equals("wednesday")) {
//                number = 6;
//            } else if (dayy.equals("thursday")) {
//                number = 0;
//            } else if (dayy.equals("friday")) {
//                number = 1;
//            }
//        } else if (day.equals("friday")) {
//            if (dayy.equals("saturday")) {
//                number = 1;
//            } else if (dayy.equals("sunday")) {
//                number = 2;
//            } else if (dayy.equals("monday")) {
//                number = 3;
//            } else if (dayy.equals("tuesday")) {
//                number = 4;
//            } else if (dayy.equals("wednesday")) {
//                number = 5;
//            } else if (dayy.equals("thursday")) {
//                number = 6;
//            } else if (dayy.equals("friday")) {
//                number = 0;
//            }
//        }
//        Log.d(TAG, "numberOfDays: " + number);
//        return number;
//    }
}
