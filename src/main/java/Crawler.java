
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Crawler {

    private  int numOfRequests = 0;
    private  int errors = 0;
    private  int successes = 0;
    private List<String> unvisitedPages = new ArrayList<>();
    private  Set<String> visitedPages = new HashSet<>();

    private void crawl() {
        long startTime = System.nanoTime();

        // Get the initial list of links from the JSON.
        JSONArray initialLinks = new JSONReader().readJSON();

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
                    if(visitedPages.contains(currLink)) continue;
//                    System.out.println(currLink);
                    // It hasn't been visited so add it to the list.
                    unvisitedPages.add(currLink);

                }
            } catch (IOException e) {
                errors++;
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

    public static void main(String[] args) {

        new Crawler().crawl();
    }
}
