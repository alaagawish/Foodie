package eg.gov.iti.jets.foodie.favourites.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.jets.foodie.favourites.view.FavouriteViewInterface;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.RepositoryInterface;

public class FavouritePresenter implements FavouritePresenterInterface {
    private static final String TAG = "FavouritePresenter";
    private RepositoryInterface repositoryInterface;

    FavouriteViewInterface favouriteViewInterface;

    public FavouritePresenter(FavouriteViewInterface favouriteViewInterface, RepositoryInterface repositoryInterface) {
        this.favouriteViewInterface = favouriteViewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void removeFavouriteMeal(Meal meal) {
        repositoryInterface.removeMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getAllMealFavPlan() {
        return repositoryInterface.getMealsDB();
    }
}
