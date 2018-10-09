import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class JSONReader {

    JSONArray readJSON (String fileName) {
        // Create a parser
        JSONParser parser = new JSONParser();
        // Set links to null at first to check later if it is set properly
        JSONArray links = null;

        try {
            // Set file to an object
            Object obj = parser.parse(new FileReader(fileName));

            JSONObject jsonObject = (JSONObject) obj;
            // Store links of JSON into the JSONArray
            links = (JSONArray) jsonObject.get("links");


        } catch (FileNotFoundException e) {
            System.out.println("That file was not found!");
        } catch (IOException e) {
            System.out.println("The program failed trying to read the JSON file!");

        } catch (ParseException e) {
            System.out.println("The program failed trying to parse the JSON file!");
        }

        return links;
    }
}
