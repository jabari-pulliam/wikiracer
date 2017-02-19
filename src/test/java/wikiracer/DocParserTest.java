package wikiracer;


import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DocParserTest {

    @Test
    public void findLinks_resultShouldContainKnownLink() {
        // Given
        String url = "https://en.wikipedia.org/wiki/Google";
        String knownLink = "https://en.wikipedia.org/wiki/Internet";
        String shouldntContain = "https://www.google.com/";

        // When
        Set<String> links = new DocParser().findLinks(url);

        // Then
        assertTrue(links.contains(knownLink));
        assertFalse(links.contains(shouldntContain));
    }

}
