import java.util.Iterator;

public class Crawler {

    public static void main(String[] args) {

        JSONReader reader = new JSONReader();
        Iterator<String> iter = reader.readJSON("src/main/resources/data.json");

        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
