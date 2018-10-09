import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTest {
    private Crawler testCrawl = new Crawler();

    @Test
    public void testBadURL() {
        testCrawl.crawl("src/test/resources/invalidURL.json");
        assertEquals(1, testCrawl.getErrors());
        assertEquals(0, testCrawl.getSuccesses());
        assertEquals(1, testCrawl.getNumOfRequests());
    }

    @Test
    public void testGibberish() {
        testCrawl.crawl("src/test/resources/gibberish.json");
        assertEquals(1, testCrawl.getErrors());
        assertEquals(0, testCrawl.getSuccesses());
        assertEquals(1, testCrawl.getNumOfRequests());
    }

    @Test
    public void testBadPorotcol() {
        testCrawl.crawl("src/test/resources/badProtocol.json");
        assertEquals(1, testCrawl.getErrors());
        assertEquals(0, testCrawl.getSuccesses());
        assertEquals(1, testCrawl.getNumOfRequests());
    }

    @Test
    public void invalidJSON() {
        testCrawl.crawl("src/test/resources/invalidJSON.json");
    }

    @Test
    public void emptyJSON() {
        testCrawl.crawl("src/test/resources/emptyJSON.json");
    }

    @Test
    public void badFile() {
        testCrawl.crawl("src/test/resources/doesn'tExist.test");
    }

    @Test
    public void badInput() {
        testCrawl.crawl("this is wrong");
    }

    @Test
    public void oneSuccess() {
        testCrawl.crawl("src/test/resources/oneSuccess.json");
        assertEquals(1, testCrawl.getNumOfRequests());
        assertEquals(1, testCrawl.getSuccesses());
        assertEquals(0, testCrawl.getErrors());
    }

    @Test
    public void tenSuccesses() {
        testCrawl.crawl("src/test/resources/tenSuccesses.json");
        assertEquals(10, testCrawl.getNumOfRequests());
        assertEquals(10, testCrawl.getSuccesses());
        assertEquals(0, testCrawl.getErrors());
    }

    @Test
    public void twoFails() {
        testCrawl.crawl("src/test/resources/twoFails.json");
        assertEquals(2, testCrawl.getNumOfRequests());
        assertEquals(0, testCrawl.getSuccesses());
        assertEquals(2, testCrawl.getErrors());
    }

    @Test
    public void testDuplicates() {
        testCrawl.crawl("src/test/resources/duplicateLinks.json");
        assertEquals(3, testCrawl.getNumOfRequests());
        assertEquals(1, testCrawl.getSuccesses());
        assertEquals(2, testCrawl.getErrors());
    }



}