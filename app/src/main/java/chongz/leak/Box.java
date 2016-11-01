package chongz.leak;

import lombok.Data;

@Data
public class Box {
    Cat hiddenCat;

    public static void main(String[] args) {
        Box box = new Box();
        String s = box.toString();
        System.out.println(s);
    }
}

