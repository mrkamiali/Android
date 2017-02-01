package kamran.com.practiceproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import kamran.com.practiceproj.model.get_request_usermodel.User;
import kamran.com.practiceproj.network.UserService;

public class MainActivity extends AppCompatActivity {

    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         userService = new UserService();
        userService.UserService().userResponseList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(new Function<List<User>, Iterable<User>>() {
                    @Override
                    public Iterable<User> apply(List<User> users) throws Exception {
                        return users;
                    }
                })
//                .filter(user -> user.getId()==1)
                .flatMap(new Function<User, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(User user) throws Exception {

                        return getUserByid(user.getId());
                    }
                })
                .toSortedList((user, t1) -> user.getName().compareTo(t1.getName()))
                        .subscribe(new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> users) throws Exception {
                                for (User user : users) {
                                    AppLogs.loge(user.toString()+"\n");
                                }
                            }
                        });

//        GetUser service = GetUser.retrofit.create(GetUser.class);


        //for post request
//        UserPostRequest userPostRequest = new UserPostRequest();
//        userPostRequest.setBody("bar");
//        userPostRequest.setTitle("foo");
//        userPostRequest.setUserId(1);
//        Call<UserPostRequest> userpost = service.userpost(userPostRequest);
//
//        userpost.enqueue(new Callback<UserPostRequest>() {
//            @Override
//            public void onResponse(Call<UserPostRequest> call, Response<UserPostRequest> response) {
//                if (response.isSuccessful()){
//                    AppLogs.logd(response.body().getBody()+" \n id: "+response.body().getId()+" \n userid: "+response.body().getUserId());
//                }
//            }
//            @Override
//            public void onFailure(Call<UserPostRequest> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        //Get Json Object
//        Call<User> user = service.user(1);
//        user.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()){
//                    AppLogs.logd(response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        //Get jason Array
//        Call<List<User>> listCall = service.userResponseList();
//
//        listCall.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.isSuccessful()){
//                    AppLogs.logd(response.body().get(1).toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });


    }

    private Observable<User> getUserByid(Integer id) {
     //   Observable<User> user =
         return  userService.UserService().user(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
       // return user;
    }
}
