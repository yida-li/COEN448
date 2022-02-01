package com.java.singleton;

import java.awt.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {
    private static Singleton robot;

    @BeforeEach
    void setUp(){
        robot = Singleton.getInstance();
    }

    @AfterEach
    void TearDown() {
        robot.reinitialize();
    }

    @Test
    void testGetInstance(){
        assertEquals(robot, robot.getInstance());
    }

    @Test
    void testGetDirection(){
        assertEquals("north", robot.getDirection());
    }

    @Test
    void testSetDirectionNorth(){
        robot.setDirectionSouth();
        robot.setDirectionNorth();
        assertEquals("north", robot.getDirection());
    }

    @Test
    void testSetDirectionSouth(){
        robot.setDirectionSouth();
        assertEquals("south", robot.getDirection());
    }

    @Test
    void testSetDirectionEast(){
        robot.setDirectionEast();
        assertEquals("east", robot.getDirection());
    }

    @Test
    void testSetDirectionWest(){
        robot.setDirectionWest();
        assertEquals("west", robot.getDirection());
    }

    @Test
    void testGetPenState(){
        //test pen is up
        assertFalse(robot.getPenState());

        robot.setPenState(true);
        //test pen is down
        assertTrue(robot.getPenState());
    }

    @Test
    void testSetPenState(){
        //test setting pen down
        robot.setPenState(true);
        assertTrue(robot.getPenState());

        //test setting pen up
        robot.setPenState(false);
        assertFalse(robot.getPenState());
    }

    @Test
    void testGetCoordinates(){
        Point p = new Point(0,0);
        assertEquals(p,robot.getCoordinates());
    }

    @Test
    void testSetCoordinates(){
        Point p = new Point(5,3);
        robot.setCoordinates(p);
        assertEquals(p,robot.getCoordinates());
    }

    @Test
    void testReinitialize(){
        //change robot state
        Point p1 = new Point(5,3);
        robot.setPenState(true);
        robot.setCoordinates(p1);
        robot.setDirectionWest();

        //reset robot
        robot.reinitialize();

        Point p = new Point(0,0);
        assertAll(() -> assertFalse(robot.getPenState()),
                () -> assertEquals(p, robot.getCoordinates()),
                () -> assertEquals("north", robot.getDirection()));
    }
}
