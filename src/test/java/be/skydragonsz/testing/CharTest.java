package be.skydragonsz.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharTest {

    public static void main(String...args){
        String tempString = "This is a test, return %u and %m";

        List<Integer> test = new ArrayList<>();
        for (int i = 0; i < tempString.length(); i++) {
            if(tempString.charAt(i) == '%') test.add(i);
        }

        System.out.println(test);
    }
}
