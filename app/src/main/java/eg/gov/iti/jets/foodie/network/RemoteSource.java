package eg.gov.iti.jets.foodie.network;

public interface RemoteSource {
    void enqueueCall(NetworkDelegation networkDelegate);

    void enqueueCall(NetworkDelegation networkDelegate, String name, char c);
}
