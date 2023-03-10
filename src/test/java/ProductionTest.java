import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductionTest {

    private int add(String numbersInput) {
        if (numbersInput == null) {
            throw new InvalidParameterException();
        }
        if (numbersInput.equals("")) {
            return 0;
        }
        if (!numbersInput.contains(",")) {
            return Integer.parseInt(numbersInput);
        }
        String[] numbers = numbersInput.split(",");
        return Stream.of(numbers)
            .mapToInt(Integer::parseInt)
            .sum();
    }

    @Test
    public void emptyString() {
        assertEquals(0, add(""));
    }

    @Test
    public void oneNumberString() {
        assertEquals(2, add("2"));
    }

    @Test
    public void threeNumberString() {
        assertEquals(3, add("3"));
    }

    @Test
    public void nullString() {
        assertThrows(InvalidParameterException.class, () -> add(null));
    }

    @Test
    public void oneAndTwoReturnThree() {
        assertEquals(3, add("1,2"));
    }

    @Test
    public void twoAndOneReturnThree() {
        assertEquals(3, add("2,1"));
    }

    @Test
    public void fiveAndSixReturnEleven(){
        assertEquals(11, add("5,6"));
    }

    @Test
    public void fiftyFive(){
        assertEquals(55, add("55"));
    }

    @Test
    public void fiveHundred(){
        assertEquals(500, add("500"));
    }

    @Test
    public void oneTwoAndThree(){
        assertEquals(6, add("1,2,3"));
    }
}