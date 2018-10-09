import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTest {
    //TODO Need to change classes so I can test them properly. Consider adding inputs into crawler.
    @BeforeClass
    public static void runOnceBeforeClass() {
        Crawler crawler = new Crawler();

    }

    @Before
    public void runBeforeTestMethod() {
        System.out.println("@Before - runBeforeTestMethod");
    }

    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        System.out.println("testing");
        assertEquals("Junit is working fine",str);
    }

}