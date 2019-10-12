package rickyxe.demo.mvp.demo2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface V2exApiService {

    @GET("/api/members/show.json")
    Call<UserApiResult> getUserInfo(@Query("username") String userName);
}
