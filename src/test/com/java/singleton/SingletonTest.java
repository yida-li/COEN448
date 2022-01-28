package com.java.singleton;

import com.java.singleton.Singleton;
import java.awt.*;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(false, robot.getPenState());
    }

    @Test
    void testSetPenState(){
        robot.setPenState(true);
        assertEquals(true, robot.getPenState());
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
        Point p1 = new Point(5,3);
        robot.setPenState(true);
        robot.setCoordinates(p1);
        robot.setDirectionWest();
        robot.reinitialize();

        Point p = new Point(0,0);
        assertAll(() -> assertEquals(false, robot.getPenState()),
                () -> assertEquals(p, robot.getCoordinates()),
                () -> assertEquals("north", robot.getDirection()));
    }
}
