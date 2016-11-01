package chongz.leak;

import java.util.concurrent.TimeUnit;

import chongz.leak.api.TicketApi;
import chongz.leak.bean.MovieEntry;
import chongz.leak.bean.UpdateApkBean;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiManager {
    private Retrofit mRetrofit;
    private IMovieService mIMovieService;

    private Retrofit mTicketRetrofit;
    private TicketApi mTicketApi;

    private static class PlaceHolder {
        private static ApiManager INSTANCE = new ApiManager();
    }

    private ApiManager(){



    }

    private void initTicketApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);

        mTicketRetrofit = new Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl("http://192.168.1.104:8080/ticketchecker/")
            .build();

        mTicketApi = mTicketRetrofit.create(TicketApi.class);
    }

    public void getUpdateApk(Subscriber<UpdateApkBean> subscriber) {
        if (mTicketApi == null) {
            initTicketApi();
        }

        mTicketApi.getUpdateApkBean()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
    }


    private void initIMovieService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl("https://api.douban.com/v2/movie/")
            .build();

        mIMovieService = mRetrofit.create(IMovieService.class);
    }

    public static ApiManager getInstance() {
        return PlaceHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<MovieEntry> subscriber,int start, int count) {
        if ( mIMovieService == null ) {
            initIMovieService();
        }

        mIMovieService.getTopMovie(start, count)
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
    }
}
