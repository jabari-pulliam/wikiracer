package wikiracer;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InputParserTest {

    @Test
    public void parseInputSpec_shouldReturnInputLinks() {
        // Given
        String input = "{'start': 'https://en.wikipedia.org/wiki/Malaria', " +
                "'end': 'https://en.wikipedia.org/wiki/Geophysics'}";

        // When
        InputSpec inputSpec = new InputParser().parseInputSpec(input);

        // Then
        assertNotNull(inputSpec);
        assertEquals("https://en.wikipedia.org/wiki/Malaria", inputSpec.getStart());
        assertEquals("https://en.wikipedia.org/wiki/Geophysics", inputSpec.getEnd());
    }

}
