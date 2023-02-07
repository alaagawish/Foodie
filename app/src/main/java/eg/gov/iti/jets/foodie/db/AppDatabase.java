package eg.gov.iti.jets.foodie.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eg.gov.iti.jets.foodie.model.MealFavPlan;

@Database(entities = {MealFavPlan.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase = null;

    public abstract MealFavPlanDAO mealFavPlanDAO();

    public synchronized static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "favMeals").build();
        }
        return appDatabase;
    }


}