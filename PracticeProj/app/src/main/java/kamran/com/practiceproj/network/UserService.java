package kamran.com.practiceproj.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.List;
import io.reactivex.Observable;
import kamran.com.practiceproj.model.get_request_usermodel.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Kamran ALi on 2/1/2017.
 */

public class UserService {
    private GetUser getUser;

    public GetUser UserService() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.user_baseUrl)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return getUser = retrofit.create(GetUser.class);
    }


}
