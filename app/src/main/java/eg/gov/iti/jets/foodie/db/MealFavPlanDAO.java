package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.gov.iti.jets.foodie.model.MealFavPlan;

@Dao
public interface MealFavPlanDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavMeal(MealFavPlan mealFavPlan);

    @Delete
    void deleteFromFavMeal(MealFavPlan mealFavPlan);

    @Query("SELECT * FROM favMeals")
    LiveData<List<MealFavPlan>> getAllFavMeals();


}