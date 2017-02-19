package wikiracer;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathFinderTest {

    @Test
    public void findPath_shouldFindShortestPath() {
        // Given
        String start = "https://en.wikipedia.org/wiki/Malaria";
        String end = "https://en.wikipedia.org/wiki/Geophysics";

        // When
        List<String> path = new PathFinder().findPath(start, end);

        for (String p : path)
            System.out.println(p);

        // Then
        assertEquals("https://en.wikipedia.org/wiki/Malaria", path.get(0));
        assertEquals("https://en.wikipedia.org/wiki/Geophysics", path.get(path.size()-1));
    }

}
