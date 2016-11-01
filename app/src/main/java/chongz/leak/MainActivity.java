package chongz.leak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.segment.jsonrpc.JsonRPCConverterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chongz.leak.bean.MovieEntry;
import chongz.leak.bean.UpdateApkBean;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn)
    Button btn;

    @BindView(R.id.firstTextView)
    TextView firstTextView;


    @BindView(R.id.textView)
    TextView textView;

    private StringBuffer mStringBuffer = new StringBuffer();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        Observable.just(1, 2, 3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    System.out.println(integer);
                }
            });

        UserInfo userInfo = new UserInfo(1,"zc");
        UserInfo userInfo2 = new UserInfo(2, "m");

        Address address = new Address("anshan road");
        Address address1 = new Address("shenyang road");

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        addresses.add(address1);

        userInfo.setAddresses(addresses);

        UserInfo[] userInfos = new UserInfo[]{
            userInfo,userInfo2
        };

        Observable.from(userInfos)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(new Func1<UserInfo, Observable<Address>>() {
                @Override
                public Observable<Address> call(UserInfo userInfo) {
                    if ( userInfo.getAddresses() == null ) {
                        return null;
                    }
                    return Observable.from(userInfo.getAddresses());
                }
            })
            .subscribe(new Subscriber<Address>() {
                @Override
                public void onCompleted() {
                    firstTextView.setText(mStringBuffer.toString());
                    mStringBuffer.setLength(0);
                }

                @Override
                public void onError(Throwable e) {
                    firstTextView.setText(e.getLocalizedMessage());
                    Log.e(TAG, "onError: " + e.getLocalizedMessage());
                }

                @Override
                public void onNext(Address address) {
                    mStringBuffer.append(address.getDesc()).append("\n");
                }
            });

        Log.d(TAG, "onCreate: " + realm.getPath());
    }



    @OnClick(R.id.btn)
    public void btnClick() {
//        getMovie();
//        logout();
        getUpdate();
    }

    private void getUpdate() {
        Subscriber<UpdateApkBean> apkBeanSubscriber = new Subscriber<UpdateApkBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getLocalizedMessage());
            }

            @Override
            public void onNext(UpdateApkBean updateApkBean) {
                System.out.println(updateApkBean.getBody().getVersion_up_info());
            }
        };

        ApiManager.getInstance().getUpdateApk(apkBeanSubscriber);
    }

    private void logout() {
        String baseUrl = "http://192.168.1.57:8080/PreSysService/e/";

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JsonRPCConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

        String postData =
            "{\"RequestMessage\":{\"body\":{\"req\":{\"userId\":\"006\"}}," +
            "\"header\":{\"compress\":\"0\",\"encrypt\":\"0\",\"user_id\":\"006\",\"ver\":\"1\"}," +
            "\"transaction\":{\"code\":\"T000001\",\"error_code\":\"0\",\"error_msg\":\"success\",\"sequence\":\"20160711171411\",\"time\":\"2016-07-11 17:14:11\"}}}'";

        IMovieService iMovieService = retrofit.create(IMovieService.class);
        iMovieService.logout(postData)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity.this, "finisheeeeed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    firstTextView.setText(e.getLocalizedMessage());
                }

                @Override
                public void onNext(String s) {
                    firstTextView.setText(s);
                }
            });
    }

    private void getMovie() {
        Subscriber<MovieEntry> subscriber = new Subscriber<MovieEntry>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                firstTextView.setText(e.getLocalizedMessage());
            }

            @Override
            public void onNext(MovieEntry movieEntry) {
                StringBuffer sb = new StringBuffer();
                for ( MovieEntry.SubjectsBean subject : movieEntry.getSubjects() ) {
                    sb.append(subject.getTitle()).append("\n");
                }

                firstTextView.setText(sb.toString());
            }
        };

        ApiManager.getInstance().getTopMovie(subscriber,0,10);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
