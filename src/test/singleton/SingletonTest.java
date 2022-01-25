package singleton;

import com.java.singleton.Singleton;
import java.awt.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonTest {
    private static Singleton robot;

    @BeforeAll
    public static void setUp(){
        robot = Singleton.getInstance();
    }

    @Test
    void testGetInstance(){
        assertEquals(robot, robot.getInstance());
    }

    @Test
    void testGetPenState(){
        assertEquals(false, robot.getPenState());
    }

    @Test
    void testSetPenState(){
        robot.setPenState();
        assertEquals(true, robot.getPenState());
        robot.setPenState();
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
        p = new Point(0,0);
        robot.setCoordinates(p);
    }
}