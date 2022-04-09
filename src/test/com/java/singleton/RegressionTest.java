package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class RegressionTest {

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
        commandHandler.turnLeft();commandHandler.turnLeft();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnLeft();commandHandler.moveRobot(1);commandHandler.turnRight();
        commandHandler.moveRobot(1);commandHandler.turnRight();
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
    void testCase13(){

        commandHandler.initializeSystem(10);
        commandHandler.penDown();
        commandHandler.moveRobot(9);commandHandler.turnRight();
        commandHandler.moveRobot(9);commandHandler.turnRight();
        commandHandler.moveRobot(9);commandHandler.turnRight();
        commandHandler.moveRobot(9);commandHandler.turnRight();
        commandHandler.moveRobot(5);commandHandler.turnRight();
        commandHandler.moveRobot(9);commandHandler.turnLeft();
        commandHandler.moveRobot(2);commandHandler.turnLeft();
        commandHandler.moveRobot(4);commandHandler.turnLeft();
        commandHandler.moveRobot(2);commandHandler.turnLeft();commandHandler.turnLeft();
        commandHandler.moveRobot(4);commandHandler.turnRight();
        commandHandler.moveRobot(2);commandHandler.turnRight();
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

    }

    @Test
    void testCase1(){

        outputStreamCaptor.reset();
        commandHandler.initializeSystem(10);
        commandHandler.commands.add("d");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        assertEquals(
                "Initializing with size: 10\n" +
                        "\n" +
                        "History command:i 10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:d\n" +
                        "\n" +
                        "Pen direction going down...\n" +
                        "\n" +
                        "History command:m5\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8|                     \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5| ↓                   \n" +
                        "4| *                   \n" +
                        "3| *                   \n" +
                        "2| *                   \n" +
                        "1| *                   \n" +
                        "0| *                   \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());

    }

    @Test
    void testCase2(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m2");
        commandHandler.commands.add("m2");
        commandHandler.commands.add("m2");
        commandHandler.commands.add("m2");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:m2\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:m2\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:m2\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:m2\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8| ↑                   \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5|                     \n" +
                        "4|                     \n" +
                        "3|                     \n" +
                        "2|                     \n" +
                        "1|                     \n" +
                        "0|                     \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase3(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("d");
        commandHandler.commands.add("c");
        commandHandler.commands.add("u");
        commandHandler.commands.add("c");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:d\n" +
                        "\n" +
                        "Pen direction going down...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: down - Facing: north\n" +
                        "\n" +
                        "History command:u\n" +
                        "\n" +
                        "Pen direction going up...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: north", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase4(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i0");
        commandHandler.commands.add("i110");
        commandHandler.commands.add("i10");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        assertEquals(
                "History command:i0\n" +
                        "\n" +
                        "Invalid entry\n" +
                        "\n" +
                        "History command:i110\n" +
                        "\n" +
                        "Invalid entry\n" +
                        "\n" +
                        "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8|                     \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5|                     \n" +
                        "4|                     \n" +
                        "3|                     \n" +
                        "2|                     \n" +
                        "1|                     \n" +
                        "0| ↑                   \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase5(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("l");
        commandHandler.commands.add("c");
        commandHandler.commands.add("L");
        commandHandler.commands.add("c");
        commandHandler.commands.add("L");
        commandHandler.commands.add("C");
        commandHandler.commands.add("l");
        commandHandler.commands.add("C");
        commandHandler.commands.add("r");
        commandHandler.commands.add("c");
        commandHandler.commands.add("r");
        commandHandler.commands.add("c");
        commandHandler.commands.add("r");
        commandHandler.commands.add("c");
        commandHandler.commands.add("r");
        commandHandler.commands.add("c");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:l\n" +
                        "\n" +
                        "Turning left...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: west\n" +
                        "\n" +
                        "History command:L\n" +
                        "\n" +
                        "Turning left...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: south\n" +
                        "\n" +
                        "History command:L\n" +
                        "\n" +
                        "Turning left...\n" +
                        "\n" +
                        "History command:C\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: east\n" +
                        "\n" +
                        "History command:l\n" +
                        "\n" +
                        "Turning left...\n" +
                        "\n" +
                        "History command:C\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: north\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: east\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: south\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: west\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 0 - Pen: up - Facing: north", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase6(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m0");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("m10");
        commandHandler.commands.add("c");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:m0\n" +
                        "\n" +
                        "Invalid entry\n" +
                        "\n" +
                        "History command:m5\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:m10\n" +
                        "\n" +
                        "Can not move 10 in the north direction, the robot will fall off the table\n" +
                        "\n" +
                        "History command:c\n" +
                        "\n" +
                        "Position: 0, 5 - Pen: up - Facing: north", outputStreamCaptor.toString().trim());
    }
    @Test
    void testCase7(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("p");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8|                     \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5|                     \n" +
                        "4|                     \n" +
                        "3|                     \n" +
                        "2|                     \n" +
                        "1|                     \n" +
                        "0| ↑                   \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9 \n" +
                        "History command:d\n" +
                        "\n" +
                        "Pen direction going down...\n" +
                        "\n" +
                        "History command:m5\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8|                     \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5| ↓                   \n" +
                        "4| *                   \n" +
                        "3| *                   \n" +
                        "2| *                   \n" +
                        "1| *                   \n" +
                        "0| *                   \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9", outputStreamCaptor.toString().trim());
    }
    @Test
    void testCase8(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("p");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
                        "9|                     \n" +
                        "8|                     \n" +
                        "7|                     \n" +
                        "6|                     \n" +
                        "5|                     \n" +
                        "4|                     \n" +
                        "3|                     \n" +
                        "2|                     \n" +
                        "1|                     \n" +
                        "0| ↑                   \n" +
                        "  ____________________\n" +
                        "   0 1 2 3 4 5 6 7 8 9 \n" +
                        "History command:d\n" +
                        "\n" +
                        "Pen direction going down...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m9\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:p\n" +
                        "\n" +
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
    void testCase9(){
        outputStreamCaptor.reset();
        commandHandler.commands.add("i10");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("l");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("q");
        commandHandler.runHistory();
        assertEquals(
                "History command:i10\n" +
                        "\n" +
                        "Initializing with size: 10\n" +
                        "\n" +
                        "History command:r\n" +
                        "\n" +
                        "Turning right...\n" +
                        "\n" +
                        "History command:m5\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:l\n" +
                        "\n" +
                        "Turning left...\n" +
                        "\n" +
                        "History command:m5\n" +
                        "\n" +
                        "Moving...\n" +
                        "\n" +
                        "History command:q", outputStreamCaptor.toString().trim());
    }

}
