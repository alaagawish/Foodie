package eg.gov.iti.jets.foodie.db;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eg.gov.iti.jets.foodie.model.Meal;

@Database(entities = {Meal.class}, version = 1)

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

//@Database(version = 2,
//        entities = {Meal.class},
//        autoMigrations = {@AutoMigration(from = 1, to = 2)}
//)
