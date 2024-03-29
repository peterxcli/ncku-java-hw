package hw3;

public class Point {
    private double x;
    private double y;

    // Reset the coordinate position of the point by the given the x and y.
    public void Set(int vertical, int horizontal) {
        this.x = vertical;
        this.y = horizontal;
    }
    // Move the current point x pixels on the x-axis and y pixels on the y-axis.
    public void Move(int x, int y) {
        this.x += x;
        this.y += y;
    }
    // Rotate 90 degrees clockwise from the origin.
    public void Rotate() {
        double temp = this.x;
        this.x = this.y;
        this.y = -temp;
    }
    // Get the value x of the point.
    public int RetrieveVertical() {
        return (int) this.x;
    }
    // Get the value y of the point.
    public int RetrieveHorizontal() {
        return (int) this.y;
    }
    // calculate the distance between other point class using the Manhattan distance
    public int calculateManhattanDistance(Point other) {
        return Math.abs((int) this.x - other.RetrieveVertical()) + Math.abs((int) this.y - other.RetrieveHorizontal());
    }
    // calculate the distance between other point class using the Chebyshev distance
    public double ChebyshevDistance(Point other) {
        return Math.max(Math.abs(this.x - other.RetrieveVertical()), Math.abs(this.y - other.RetrieveHorizontal()));
    }
}