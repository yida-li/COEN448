package com.java.singleton;
import com.java.main.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTest {

    CommandHandler commandHandler;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        commandHandler = new CommandHandler();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testCase12(){

        commandHandler.initializeSystem(10);
        commandHandler.penDown();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        //test with invalid table size
        outputStreamCaptor.reset();
        commandHandler.printTable();
        assertEquals(
                "9| * * * * * * * * * * \n" +
                        "8| *                 * \n" +
                        "7| *                 * \n" +
                        "6| *                 * \n" +
                        "5| *                 * \n" +
                        "4| *                 * \n" +
                        "3| *                 * \n" +
                        "2| *                 * \n" +
                        "1| *                 * \n" +
                        "0| ↓ * * * * * * * * * \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase13(){

        commandHandler.initializeSystem(10);
        commandHandler.penDown();
        commandHandler.moveRobot(9);
        commandHandler.turnLeft();
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnLeft();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(1);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        outputStreamCaptor.reset();
        commandHandler.printTable();
        assertEquals(
                "9| *                   \n" +
                        "8| * *                 \n" +
                        "7| * * *               \n" +
                        "6| *   * *             \n" +
                        "5| *     * *           \n" +
                        "4| *       * *         \n" +
                        "3| *         * *       \n" +
                        "2| *           * *     \n" +
                        "1| *             * *   \n" +
                        "0| ↓ * * * * * * * * * \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase14(){

        commandHandler.initializeSystem(10);
        commandHandler.penDown();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnRight();
        commandHandler.moveRobot(5);
        commandHandler.turnRight();
        commandHandler.moveRobot(9);
        commandHandler.turnLeft();
        commandHandler.moveRobot(2);
        commandHandler.turnLeft();
        commandHandler.moveRobot(4);
        commandHandler.turnLeft();
        commandHandler.moveRobot(2);
        commandHandler.turnLeft();
        commandHandler.turnLeft();
        commandHandler.moveRobot(4);
        commandHandler.turnRight();
        commandHandler.moveRobot(2);
        commandHandler.turnRight();
        commandHandler.moveRobot(2);
        outputStreamCaptor.reset();
        commandHandler.printTable();
        assertEquals(

                "9| * * * * * * * * * * \n" +
                        "8| *         *   *   * \n" +
                        "7| *         * * ↓ * * \n" +
                        "6| *         *       * \n" +
                        "5| * * * * * * * * * * \n" +
                        "4| *                 * \n" +
                        "3| *                 * \n" +
                        "2| *                 * \n" +
                        "1| *                 * \n" +
                        "0| * * * * * * * * * * \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
        Main main= new Main();
    }

}
