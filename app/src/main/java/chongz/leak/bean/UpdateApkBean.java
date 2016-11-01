package chongz.leak.bean;

public class UpdateApkBean {

    /**
     * version_up_info : 支持电子票、增加换取发票功能
     * is_force2version_up : 1
     * version : 1.0
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        private String version_up_info;
        private String is_force2version_up;
        private String version;

        public String getVersion_up_info() {
            return version_up_info;
        }

        public void setVersion_up_info(String version_up_info) {
            this.version_up_info = version_up_info;
        }

        public String getIs_force2version_up() {
            return is_force2version_up;
        }

        public void setIs_force2version_up(String is_force2version_up) {
            this.is_force2version_up = is_force2version_up;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
