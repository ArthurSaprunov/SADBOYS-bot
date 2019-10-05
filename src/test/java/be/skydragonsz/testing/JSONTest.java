package be.skydragonsz.testing;

import org.json.JSONObject;

public class JSONTest {
    public static void main(String...args){
        JSONObject parent = new JSONObject();

        JSONObject child = new JSONObject();

        child.append("test","test");
        child.append("test2","test");
        child.append("test3","test");
        child.put("test5","test");

        System.out.println(child);
        System.out.println(parent);

        parent.put("tests",child);

        System.out.println(child);
        System.out.println(parent);


    }
}
