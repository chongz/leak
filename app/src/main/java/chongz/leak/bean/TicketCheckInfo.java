package chongz.leak.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCheckInfo extends RealmObject {
    @Required
    private String userNo;
    @Required
    private String userName;
    @PrimaryKey
    private String uuid;
    @Required
    private Date checkDate;
    @Required
    private String checkPlace;


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuffer sb = new StringBuffer();
        sb.append("uuid:").append(uuid).append(" userNo:").append(userNo).append(" userName:")
            .append
                (userName).append(" checkDate:").append(sdf.format(checkDate)).append(" checkPlace").append
            (checkPlace);
        return sb.toString();
    }
}
