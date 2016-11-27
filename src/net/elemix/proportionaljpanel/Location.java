package net.elemix.proportionaljpanel;

/**
 * Simple Storage for X and Y coordinates
 */
public class Location {
    private int x;
    private int y;

    private Location() {

    }

    /**
     *
     * @param x - X Coordinate (int)
     * @param y - Y Coordinate (int)
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X Coordinate
     * @return int x - X Coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get Y Coordinate
     * @return int y - Y Coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set X Coordinate
     * @param x - X Coordinate (int)
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set Y Coordinate
     * @param y - Y Coordinate (int)
     */
    public void setY(int y) {
        this.y = y;
    }
}
