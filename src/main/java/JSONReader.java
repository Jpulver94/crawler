import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

class JSONReader {

    JSONArray readJSON () {

        JSONParser parser = new JSONParser();
        JSONArray links = null;

        try {

            Object obj = parser.parse(new FileReader("src/main/resources/data.json"));

            JSONObject jsonObject = (JSONObject) obj;

            links = (JSONArray) jsonObject.get("links");


        } catch (FileNotFoundException e) {
            System.out.println("The program failed trying to open the JSON file!");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("The program failed trying to read the JSON file!");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("The program failed trying to parse the JSON file!");
            System.out.println(e.getMessage());
        }

        return links;
    }
}
