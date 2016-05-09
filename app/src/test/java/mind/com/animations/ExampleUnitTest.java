package mind.com.animations;

import org.junit.Test;
import org.mockito.Mockito;

import mind.com.animations.models.City;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void animation_sterted(){
        City city = Mockito.mock(City.class);
        city.name = "test";

        assertEquals(city.name, "test");
    }
}