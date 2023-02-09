package eg.gov.iti.jets.foodie.network;

import eg.gov.iti.jets.foodie.model.Meal;

public interface RemoteSource {
    void enqueueCall(NetworkDelegation networkDelegate);

    void enqueueCallBySearch(NetworkDelegation networkDelegate, String search);

    void enqueueCallById(NetworkDelegation networkDelegate, String id);

    void enqueueCall(NetworkDelegation networkDelegate, String name, char c);
}