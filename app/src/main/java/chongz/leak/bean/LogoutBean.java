package chongz.leak.bean;

import java.util.List;

public class LogoutBean {

    /**
     * method : process
     * params : ["{\"RequestMessage\":{\"body\":{\"req\":{\"userId\":\"006\"}},\"header\":{\"compress\":\"0\",\"encrypt\":\"0\",\"user_id\":\"006\",\"ver\":\"1\"},\"transaction\":{\"code\":\"T000001\",\"error_code\":\"0\",\"error_msg\":\"success\",\"sequence\":\"20160711171411\",\"time\":\"2016-07-11 17:14:11\"}}}"]
     * id : 0
     */

    private String method;
    private int id;
    private List<String> params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
