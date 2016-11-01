package chongz.leak.api;

import chongz.leak.bean.UpdateApkBean;
import retrofit2.http.GET;
import rx.Observable;

public interface TicketApi {
    @GET("getappinfo.jsp")
    Observable<UpdateApkBean> getUpdateApkBean();
}
