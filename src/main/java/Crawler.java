
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Crawler {

    private  int numOfRequests = 0;
    private  int errors = 0;
    private  int successes = 0;
    private List<String> unvisitedPages = new ArrayList<>();
    private  Set<String> visitedPages = new HashSet<>();

    void crawl(String fileName) {
        long startTime = System.nanoTime();

        // Get the initial list of links from the JSON.
        JSONArray initialLinks = new JSONReader().readJSON(fileName);

        // Make sure the links are not null.
        if(initialLinks == null) {
            System.out.println("Something went wrong trying to read the JSON file and the program has exited.");
            return;
        }

        // Add initial links into the set to remove duplicates.
        unvisitedPages.addAll(initialLinks);
        String currURL;

        for (int i = 0; i < unvisitedPages.size(); i++) {
            // Get current URL
            currURL = unvisitedPages.get(i);
            // Continue to next index if we have already visited this page to avoid an infinite loop.
            if(visitedPages.contains(currURL)) continue;
            // Add page to already visited set.
            visitedPages.add(currURL);
            numOfRequests++;
//            System.out.println(numOfRequests + " " + currURL);

            try {
                // Try to access the page.
                Document doc = Jsoup.connect(currURL).get();
                successes++;
                // Grab all the links on the current page.
                Elements links = doc.select("a[href]");

                for(Element link : links) {
                    // Grab a link from this page
                    String currLink  = link.attr("abs:href");
                    // Check if the link has already been visited.
                    if(visitedPages.contains(currLink) /*|| unvisitedPages.contains(currLink)*/) continue;
//                    System.out.println(currLink);
                    // It hasn't been visited so add it to the list.
                    unvisitedPages.add(currLink);

                }
            } catch (MalformedURLException | IllegalArgumentException e) {
                errors++;
                System.out.print("The URL is invalid. ");
                System.out.println(e.getMessage());
            }catch (SocketTimeoutException e) {
                errors++;
                System.out.println("The connection has timed out on : " + currURL);
                System.out.println(e.getMessage());
            } catch (IOException e) {
                errors++;
                System.out.print("There was an error with the URL ");
                System.out.println(e.getMessage() + ": " + currURL);
            }
        }

        System.out.println("Total number of requests is " + numOfRequests);
        System.out.println("Total number of failed requests is " + errors);
        System.out.println("Total number of successful requests is " + successes);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;
        System.out.println("The program took : " + duration + " ms to finish" );

    }

    // Getters for testing purposes
    int getSuccesses() {
        return successes;
    }

    int getErrors() {
        return errors;
    }

    int getNumOfRequests() {
        return numOfRequests;
    }

    public static void main(String[] args) {

        System.out.println("Please enter a JSON file with a list of URLs similar to the example file data.json.");
        System.out.println("Or enter 'n' to use default file.");
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        String fileName = in.nextLine();
        if(fileName.compareToIgnoreCase("n") == 0) {
            System.out.println("Using the default file src/main/resources/data.json");
            new Crawler().crawl("src/main/resources/data.json");
        }
        else {
            System.out.println("You entered the file " + fileName);
            new Crawler().crawl(fileName);
        }
    }
}
