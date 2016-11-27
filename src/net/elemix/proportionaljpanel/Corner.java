package net.elemix.proportionaljpanel;

/** Represents x and y percentages of a window
 */
public enum Corner {
    NORTH(1D / 2D, 1D / 6D), SOUTH(1D / 2D, 5D / 6D), EAST(5D / 6D, 1D / 2D), WEST(1D / 6D, 1D / 2D), NORTH_EAST(5D / 6D, 1D / 6D), NORTH_WEST(1D / 6D, 1D / 6D), SOUTH_EAST(5D / 6D, 5D / 6D), SOUTH_WEST(1D / 6D, 5D / 6D), CENTER(1D / 2D, 1D / 2D), LEFT(1D / 4D, 1D / 2D), RIGHT(3D / 4D, 1D / 2D), UP(1D / 2D, 1D / 4D), DOWN(1D / 2D, 3D / 4D);
    private double x;
    private double y;

    Corner(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return double x - Returns X Percentage
     */
    public double getX() {
        return this.x;
    }

    /**
     *
     * @return double y - Returns Y Percentage
     */
    public double getY() {
        return this.y;
    }
}
