package eg.gov.iti.jets.foodie.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;


@Dao
public interface MealFavPlanDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavMeal(Meal meal);

    @Update
    void deleteFromFavMeal(Meal meal);

    @Delete
    void deleteFromPlan(Meal meal);

    @Query("SELECT * FROM favMeals ")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM favMeals where isFav = 1")
    LiveData<List<Meal>> getAllFavMeals();

    @Query("SELECT * FROM favMeals where isFav = 0  and day !='temp'")
    LiveData<List<Meal>> getPlannedMeals();

    @Query("SELECT * FROM favMeals where day = :day")
    LiveData<List<Meal>> getMealsByDay(String day);


}