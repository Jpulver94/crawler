import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JSONReader {

    public Iterator<String> readJSON (String fileName) {

        JSONParser parser = new JSONParser();
        Iterator<String> iterator = null;

        try {

            Object obj = parser.parse(new FileReader(fileName));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray links = (JSONArray) jsonObject.get("links");
            iterator = links.iterator();

        } catch (FileNotFoundException e) {
            System.out.println("The program failed trying to open the JSON file!");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("The program failed trying to read the JSON file!");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The program failed trying to read the JSON file!");
            System.out.println(e.getMessage());
        }

        return iterator;
    }
}
