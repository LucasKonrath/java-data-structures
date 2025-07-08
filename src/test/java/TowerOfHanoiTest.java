import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoiTest {

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
    public void testSingleDisk() {
        // Test with n=1 (base case)
        TowerOfHanoi.solveTowerOfHanoi(1, 'A', 'C', 'B');
        String output = outputStreamCaptor.toString().trim();
        assertEquals("Move disk 1 from A to C", output);
    }

    @Test
    public void testTwoDisks() {
        // Test with n=2
        TowerOfHanoi.solveTowerOfHanoi(2, 'A', 'C', 'B');
        String output = outputStreamCaptor.toString().trim();
        String[] moves = output.split("\n");

        assertEquals(3, moves.length, "Should have exactly 3 moves for 2 disks");
        assertEquals("Move disk 1 from A to B", moves[0]);
        assertEquals("Move disk 2 from A to C", moves[1]);
        assertEquals("Move disk 1 from B to C", moves[2]);
    }

    @Test
    public void testThreeDisks() {
        // Test with n=3
        TowerOfHanoi.solveTowerOfHanoi(3, 'A', 'C', 'B');
        String output = outputStreamCaptor.toString().trim();
        String[] moves = output.split("\n");

        assertEquals(7, moves.length, "Should have exactly 7 moves for 3 disks");

        // Verify the sequence of moves for 3 disks
        assertEquals("Move disk 1 from A to C", moves[0]);
        assertEquals("Move disk 2 from A to B", moves[1]);
        assertEquals("Move disk 1 from C to B", moves[2]);
        assertEquals("Move disk 3 from A to C", moves[3]);
        assertEquals("Move disk 1 from B to A", moves[4]);
        assertEquals("Move disk 2 from B to C", moves[5]);
        assertEquals("Move disk 1 from A to C", moves[6]);
    }

    @Test
    public void testFourDisks() {
        // Test with n=4
        TowerOfHanoi.solveTowerOfHanoi(4, 'A', 'C', 'B');
        String output = outputStreamCaptor.toString().trim();
        String[] moves = output.split("\n");

        assertEquals(15, moves.length, "Should have exactly 15 moves for 4 disks");
    }

    @Test
    public void testMoveCountFormula() {
        // Test that the number of moves follows the formula 2^n - 1
        for (int n = 1; n <= 5; n++) {
            outputStreamCaptor.reset();
            TowerOfHanoi.solveTowerOfHanoi(n, 'A', 'C', 'B');
            String output = outputStreamCaptor.toString().trim();
            String[] moves = output.split("\n");

            int expectedMoves = (int) Math.pow(2, n) - 1;
            assertEquals(expectedMoves, moves.length,
                "Number of moves for " + n + " disks should be " + expectedMoves);
        }
    }

    @Test
    public void testDifferentRodNames() {
        // Test with different rod names
        TowerOfHanoi.solveTowerOfHanoi(2, 'X', 'Z', 'Y');
        String output = outputStreamCaptor.toString().trim();
        String[] moves = output.split("\n");

        assertEquals(3, moves.length);
        assertEquals("Move disk 1 from X to Y", moves[0]);
        assertEquals("Move disk 2 from X to Z", moves[1]);
        assertEquals("Move disk 1 from Y to Z", moves[2]);
    }

    @Test
    public void testValidMoveSequence() {
        // Test that the sequence of moves is valid (no larger disk on smaller disk)
        TowerOfHanoi.solveTowerOfHanoi(3, 'A', 'C', 'B');
        String output = outputStreamCaptor.toString().trim();
        String[] moves = output.split("\n");

        // Simulate the towers to verify the moves are valid
        List<Integer>[] towers = new List[3]; // A=0, B=1, C=2
        towers[0] = new ArrayList<>();
        towers[1] = new ArrayList<>();
        towers[2] = new ArrayList<>();

        // Initialize tower A with disks 3, 2, 1 (bottom to top)
        towers[0].add(3);
        towers[0].add(2);
        towers[0].add(1);

        // Execute each move and verify it's valid
        for (String move : moves) {
            String[] parts = move.split(" ");
            int disk = Integer.parseInt(parts[2]);
            char from = parts[4].charAt(0);
            char to = parts[6].charAt(0);

            int fromIndex = from - 'A';
            int toIndex = to - 'A';

            // Verify the disk being moved is on top of the source tower
            assertFalse(towers[fromIndex].isEmpty(), "Source tower should not be empty");
            assertEquals(disk, (int) towers[fromIndex].get(towers[fromIndex].size() - 1),
                "Disk being moved should be on top of source tower");

            // Verify we're not placing a larger disk on a smaller one
            if (!towers[toIndex].isEmpty()) {
                assertTrue(disk < towers[toIndex].get(towers[toIndex].size() - 1),
                    "Cannot place larger disk on smaller disk");
            }

            // Execute the move
            towers[fromIndex].remove(towers[fromIndex].size() - 1);
            towers[toIndex].add(disk);
        }

        // Verify all disks are on the destination tower
        assertTrue(towers[0].isEmpty(), "Source tower should be empty");
        assertTrue(towers[1].isEmpty(), "Auxiliary tower should be empty");
        assertEquals(3, towers[2].size(), "Destination tower should have all disks");
        assertEquals(Integer.valueOf(3), towers[2].get(0), "Bottom disk should be 3");
        assertEquals(Integer.valueOf(2), towers[2].get(1), "Middle disk should be 2");
        assertEquals(Integer.valueOf(1), towers[2].get(2), "Top disk should be 1");
    }
}
