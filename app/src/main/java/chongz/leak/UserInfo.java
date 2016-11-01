package chongz.leak;

import java.util.List;

import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserInfo {

    @PrimaryKey
    public int id;

    @Required
    public String name;
    public int age;

    @Ignore
    public String password;

    public List<Address> addresses;

    public UserInfo() {

    }


    public UserInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isEmpty(@NonNull String value) {
        return true;
    }

    public static void main(String[] args) {
        UserInfo u = new UserInfo();
        System.out.println(u);
        u.isEmpty(null);
    }
}
