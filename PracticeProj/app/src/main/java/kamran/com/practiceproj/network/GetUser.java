package kamran.com.practiceproj.network;

import java.util.List;

import io.reactivex.Observable;
import kamran.com.practiceproj.model.get_request_usermodel.User;
import kamran.com.practiceproj.model.post_request.UserPostRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Kamran ALi on 1/31/2017.
 */

public interface GetUser {
    @GET("users")
    Observable<List<User>> userResponseList();

    @GET("users/{id}")
    Observable<User> user(@Path("id") int id);

    @POST("posts")
    Call<UserPostRequest> userpost(@Body UserPostRequest userPostRequest);

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Urls.user_baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
