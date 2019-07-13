import java.sql.Timestamp;

public class Testing {
    public static void main(String[] args) {


        Timestamp timestamp = Timestamp.valueOf("2019-06-26T17:38:53.716Z".replace("T", " ").replace("Z", ""));
        System.out.println(timestamp.getTime());

        System.out.println(String.valueOf(System.currentTimeMillis()).substring(0, 10));
    }
}
