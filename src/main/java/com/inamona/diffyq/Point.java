package com.inamona.diffyq;

/**
 * @author christopher
 * @since 3/12/21
 */
public final class Point {

    private final double t;

    private final double x;

    private final double y;

    public Point(double t, double x, double y) {
        this.t = t;
        this.x = x;
        this.y = y;
    }

    public double getT() {
        return this.t;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("%f,%f,%f", this.t, this.x, this.y);
    }
}
