package eg.gov.iti.jets.foodie.network;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface NetworkDelegation {
    public void onSuccess(List<Meal> meals);

    public void onFailure(String error);
}
