package eg.gov.iti.jets.foodie.network;

import eg.gov.iti.jets.foodie.model.MyResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Service {
    @GET("api/json/v1/1/list.php?a=list")
    Call<MyResponse> getAllMealsByArea();
}
