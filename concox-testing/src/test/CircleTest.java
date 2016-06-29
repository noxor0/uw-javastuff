/* TCSS 305 - Spring 2016
 * Assignment 1 - Testing
 */
package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import shape.Circle;

/**
 * Tests of the Circle class.
 * 
 * @author Connor Cox
 * @version 1.0
 */
public class CircleTest {

    /** A tolerance value used for comparing two double values. */
    private static final double TOLERANCE = .0001;
    
    /** A Circle used in the tests. */
    private Circle myCircle;
    /**A Point used in the tests. */
    private Point myTestPoint;
    
    /**
     * The method that sets up the test before each test.
     */
    @Before
    public void setUp() {
        myTestPoint = new Point(6, 9);
        myCircle = new Circle();
    }

    /**
     * Test method for constructor without any parameters passed in.
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals(1.0, myCircle.getRadius(), TOLERANCE);
        assertEquals(new Point(0, 0), myCircle.getCenter());
        assertEquals(Color.BLACK, myCircle.getColor());
    }

    /**
     * Test method for the circle constructor with specific parameters.
     */
    @Test
    public void testOverloadedConstructor() {
        final Circle c1 = new Circle(5.0, myTestPoint, Color.BLUE);
        assertEquals(5.0, c1.getRadius(), TOLERANCE);
        assertEquals(myTestPoint, c1.getCenter());
        assertEquals(Color.BLUE, c1.getColor());
    }

    /**
     * Test the overloaded constructor with an invalid parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullOverloadedConstructor1() {
        new Circle(5.0, null, Color.BLACK);
    }
    /**
     * Test the overloaded constructor with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullOverloadedConstructor2() {
        new Circle(-5, myTestPoint, Color.BLACK);
    }
    /**
     * Test the overloaded constructor with an invalid parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullOverloadedConstructor3() {
        new Circle(5.0, null, Color.BLACK);
    }
    /**
     * Test the overloaded constructor with an invalid parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullOverloadedConstructor4() {
        new Circle(5.0, myTestPoint, null);
    }
    /**
     * Test the setRadius method.
     */
    @Test
    public void testSetRadius() {
        myCircle.setRadius(5.0);
        assertEquals(5.0, myCircle.getRadius(), TOLERANCE);
    }
    /**
     * Test the invalid setRadius method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetInvaildRadius() {
        myCircle.setRadius(-42);
    }

    /**
     * Test the setCenter method.
     */
    @Test
    public void testSetCenter() {
        myCircle.setCenter(myTestPoint);
        assertEquals(myTestPoint, myCircle.getCenter());
    }

    /**
     * Test the setColor method.
     */
    @Test
    public void testSetColor() {
        myCircle.setColor(Color.BLUE);
        assertEquals(Color.BLUE, myCircle.getColor());
    }

    /**
     * Test the calculated diameter method, confirm that the proper equation is being used.
     */
    @Test
    public void testCalculateDiameter() {
        myCircle.setRadius(2.0);
        assertEquals(4.0, myCircle.calculateDiameter(), TOLERANCE);
    }

    /**
     *  Test the calculated diameter method, confirm that the proper equation is being used.
     */
    @Test
    public void testCalculateCircumference() {
        myCircle.setRadius(2.0);
        assertEquals(12.5664, myCircle.calculateCircumference(), TOLERANCE);
    }

    /**
     * Tests the calculate area method, and confirms that the proper equation.
     */
    @Test
    public void testCalculateArea() {
        myCircle.setRadius(3.0);
        assertEquals(28.2743, myCircle.calculateArea(), TOLERANCE);
    }

    /**
     * Test method for {@link Circle#toString()}.
     */
//  Do not think that this is working as intended. Yes it is, I'm dumb!
    @Test
    public void testToString() {
        assertEquals("Unexecpected toString() message!",
                     "Circle [radius=1.00, center=java.awt.Point[x=0,y=0],"
                     + " color=java.awt.Color[r=0,g=0,b=0]]",
                     myCircle.toString());        
    }

}
