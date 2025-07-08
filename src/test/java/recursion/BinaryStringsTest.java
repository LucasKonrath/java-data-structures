package recursion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BinaryStringsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConstructorWithValidSize() {
        // Test constructor creates array of correct size
        BinaryStrings bs = new BinaryStrings(3);
        assertNotNull(bs.array);
        assertEquals(3, bs.array.length);

        // Array should be initialized with zeros
        for (int i = 0; i < bs.array.length; i++) {
            assertEquals(0, bs.array[i]);
        }
    }

    @Test
    public void testConstructorWithZeroSize() {
        // Test constructor with size 0
        BinaryStrings bs = new BinaryStrings(0);
        assertNotNull(bs.array);
        assertEquals(0, bs.array.length);
    }

    @Test
    public void testBinaryStringsLength1() {
        // Test with n=1, should generate [0] and [1]
        BinaryStrings bs = new BinaryStrings(1);
        bs.bin(1);

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(2, lines.length, "Should generate exactly 2 combinations for n=1");

        Set<String> expectedCombinations = Set.of("[0]", "[1]");
        Set<String> actualCombinations = Set.of(lines);

        assertEquals(expectedCombinations, actualCombinations);
    }

    @Test
    public void testBinaryStringsLength2() {
        // Test with n=2, should generate 4 combinations
        BinaryStrings bs = new BinaryStrings(2);
        bs.bin(2);

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(4, lines.length, "Should generate exactly 4 combinations for n=2");

        Set<String> expectedCombinations = Set.of("[0, 0]", "[0, 1]", "[1, 0]", "[1, 1]");
        Set<String> actualCombinations = Set.of(lines);

        assertEquals(expectedCombinations, actualCombinations);
    }

    @Test
    public void testBinaryStringsLength3() {
        // Test with n=3, should generate 8 combinations
        BinaryStrings bs = new BinaryStrings(3);
        bs.bin(3);

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(8, lines.length, "Should generate exactly 8 combinations for n=3");

        Set<String> expectedCombinations = Set.of(
            "[0, 0, 0]", "[0, 0, 1]", "[0, 1, 0]", "[0, 1, 1]",
            "[1, 0, 0]", "[1, 0, 1]", "[1, 1, 0]", "[1, 1, 1]"
        );
        Set<String> actualCombinations = Set.of(lines);

        assertEquals(expectedCombinations, actualCombinations);
    }

    @Test
    public void testBinaryStringsLength4() {
        // Test with n=4, should generate 16 combinations
        BinaryStrings bs = new BinaryStrings(4);
        bs.bin(4);

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(16, lines.length, "Should generate exactly 16 combinations for n=4");

        // Verify all combinations are unique
        Set<String> uniqueCombinations = new HashSet<>(Arrays.asList(lines));
        assertEquals(16, uniqueCombinations.size(), "All combinations should be unique");

        // Verify each combination has correct format and length
        for (String line : lines) {
            assertTrue(line.matches("\\[([0-1], ){3}[0-1]\\]"),
                "Each line should match the format [x, x, x, x] where x is 0 or 1");
        }
    }

    @Test
    public void testBinaryStringsPowerOfTwoFormula() {
        // Test that the number of combinations follows 2^n formula
        for (int n = 1; n <= 4; n++) {
            outputStreamCaptor.reset();
            BinaryStrings bs = new BinaryStrings(n);
            bs.bin(n);

            String output = outputStreamCaptor.toString().trim();
            String[] lines = output.split("\n");

            int expectedCount = (int) Math.pow(2, n);
            assertEquals(expectedCount, lines.length,
                "For n=" + n + ", should generate " + expectedCount + " combinations");
        }
    }

    @Test
    public void testBinaryStringsWithZeroLength() {
        // Test with n=0, should print empty array once
        BinaryStrings bs = new BinaryStrings(0);
        bs.bin(0);

        String output = outputStreamCaptor.toString().trim();
        assertEquals("[]", output);
    }

    @Test
    public void testBinaryStringsWithNegativeInput() {
        // Test with negative input, should print array once
        BinaryStrings bs = new BinaryStrings(3);
        bs.bin(-1);

        String output = outputStreamCaptor.toString().trim();
        assertEquals("[0, 0, 0]", output);
    }

    @Test
    public void testArrayStateAfterGeneration() {
        // Test that array state is preserved after generation
        BinaryStrings bs = new BinaryStrings(2);
        bs.bin(2);

        // The final state should be [1, 1] based on the recursive algorithm
        assertArrayEquals(new int[]{1, 1}, bs.array);
    }

    @Test
    public void testMultipleCallsToSameInstance() {
        // Test calling bin multiple times on same instance
        BinaryStrings bs = new BinaryStrings(2);

        // First call
        bs.bin(2);
        String firstOutput = outputStreamCaptor.toString().trim();

        // Reset and call again
        outputStreamCaptor.reset();
        bs.bin(2);
        String secondOutput = outputStreamCaptor.toString().trim();

        // Both outputs should be identical
        assertEquals(firstOutput, secondOutput);
    }

    @Test
    public void testPartialGeneration() {
        // Test calling bin with smaller n than array size
        BinaryStrings bs = new BinaryStrings(4);
        bs.bin(2); // Only generate for first 2 positions

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(4, lines.length, "Should generate 4 combinations for n=2");

        // Verify each combination shows the full array but only first 2 positions vary
        for (String line : lines) {
            assertTrue(line.matches("\\[([0-1], ){3}[0-1]\\]"),
                "Each line should show full array of length 4");
        }
    }

    @Test
    public void testLargerBinaryStrings() {
        // Test with n=5 to ensure scalability
        BinaryStrings bs = new BinaryStrings(5);
        bs.bin(5);

        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertEquals(32, lines.length, "Should generate exactly 32 combinations for n=5");

        // Verify all combinations are unique
        Set<String> uniqueCombinations = new HashSet<>(Arrays.asList(lines));
        assertEquals(32, uniqueCombinations.size(), "All combinations should be unique");
    }
}
