package be.skydragonsz.testing;

public class StringTest {
    public static void main(String...args){
        String test;
        test = "%u testing %u";
        test = test.replace("%m","USERNAME");
        System.out.println(test);

    }
}
