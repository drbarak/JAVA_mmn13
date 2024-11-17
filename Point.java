
/**
 * Point class to handle points with x, y coordinates in the 1st quarter (x>=0 and y>=0).
 * Based on lectures of Dr. Amir Goren
 *
 * @author (Zvika Barak)
 * @version (17.11.2024)
 */
public class Point
{
    private final int INVALID_NUM = 0;
    // the coordinates of the point object
    private int _x;
    private int _y;

    /**
     * Constructors for objects of class Point
     */
    public Point(int x, int y)
    {
        // initialise instance variables
        _x = (x >= INVALID_NUM ? x : INVALID_NUM);
        _y = (y >= INVALID_NUM ? y : INVALID_NUM);
    }
    public Point(Point other)   // copy an existing Point object
    {
        // initialise instance variables
        if (other == null) return;
        _x = other._x;
        _y = other._y;
    }
    public Point()   // initialise a default point
    {
        _x = INVALID_NUM;
        _y = INVALID_NUM;
    }

    /**
     * Method move to move a point by given units of X and Y
     *
     * @param  deltaX  number of units to move the x coordinate
     * @param  deltaY  number of units to move the y coordinate
     */
    public void move(int deltaX, int deltaY)
    {
        // move the point only if to a valid point
        if (_x + deltaX >= INVALID_NUM && _y + deltaY >= INVALID_NUM)
        {
            _x += deltaX;
            _y += deltaY;
        }
    }
    /**
     * Method move to move a point by given units of X
     *
     * @param  deltaX  number of units to move the x coordinate
     */
    public void moveX(int deltaX)
    {
        // move the point only if to a valid point
        if (_x + deltaX >= INVALID_NUM)
            _x += deltaX;
    }
    /**
     * Method move to move a point by given units of Y
     *
     * @param  deltaY  number of units to move the y coordinate
     */
    public void moveY(int deltaY)
    {
        // move the point only if to a valid point
        if (_x + deltaY >= INVALID_NUM)
            _x += deltaY;
    }
    /**
     * Method to check if this object is equal to another point
     *
     * @param  other  the other Point object
     * @return true if they are equal
     */
    public boolean equals(Point other)
    {
        return(other == null ? false : other._x == _x && other._y == _y);
    }
    /**
     * Method to return the x coordinate of the point
     *
     * @return the x coordinate of the point
     */
    public int getX()
    {
        return _x;
    }
    /**
     * Method to return the y coordinate of the point
     *
     * @return the y coordinate of the point
     */
    public int getY()
    {
        return _y;
    }
    /**
     * Method to set the x coordinate of the point, if it is valid
     *
     * @param   x   the new x coordinate to set
     */
    public void setX(int x)
    {
        if (x >= INVALID_NUM)
            _x = x;
    }
    /**
     * Method to set the y coordinate of the point, if it is valid
     *
     * @param   y   the new y coordinate to set
     */
    public void setY(int y)
    {
        if (y >= INVALID_NUM)
            _y = y;
    }
    /**
     * Method to check if this point is above another point
     *
     * @param  other  the other Point object
     * @return true   if this point y coordiante is larger than that of the other object
     */
    public boolean isAbove(Point other) // whose y coordinates is larger
    {
        return (other == null ? false : _y > other._y);
    }
    /**
     * Method to check if this point is below another point
     *
     * @param  other  the other Point object
     * @return true   if this point y coordiante is smaller than that of the other object
     */
    public boolean isUnder(Point other) // whose y coordinates is lower
    {
        return (other == null ? false : other.isAbove(this));
    }
    /**
     * Method to check if this point is to the left of another point
     *
     * @param  other  the other Point object
     * @return true   if this point x coordiante is less than that of the other object
     */
    public boolean isLeft(Point other) // whose y coordinates is larger
    {
        return (other == null ? false : _x < other._x);
    }
    /**
     * Method to check if this point is to the right of another point
     *
     * @param  other  the other Point object
     * @return true   if this point x coordiante is larger than that of the other object
     */
    public boolean isRight(Point other)
    {
        return (other == null ? false : other.isLeft(this));
    }
    /**
     * Method to calculate the distance between this point and another point
     *
     * @param  other  the other Point object
     * @return double the distance between the 2 points
     */
    public double distance(Point other)
    {
        return (other == null ? -1.0 : Math.sqrt(Math.pow(_x - other._x, 2) +
                                Math.pow(_y - other._y, 2)));
    }
    /**
     * Method to prepare the point for printing
     *
     * @return String the point in the format "(_x,_y)"
     */
    public String toString()
    {
        return "(" + _x + ", " + _y + ")";
    }
} // end of class Pont
