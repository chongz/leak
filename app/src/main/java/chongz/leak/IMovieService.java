package chongz.leak;


import com.segment.jsonrpc.JsonRPC;

import chongz.leak.bean.MovieEntry;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IMovieService {
    @GET("top250")
    Observable<MovieEntry> getTopMovie(@Query("start") int start, @Query("count") int count);


    @JsonRPC("process")
    @POST("/IBookingExternalJsonService")
    Observable<String> logout(@Body String req);
}
