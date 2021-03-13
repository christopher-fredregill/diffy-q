package com.inamona.diffyq;


import io.vavr.Function3;

/**
 * @author christopher
 * @since 3/12/21
 */
public class Linear2ndOrderOdeSolver {

    private final Function3<Double, Double, Double, Double> f;

    private final Function3<Double, Double, Double, Double> g;

    private final Point initialValue;

    public Linear2ndOrderOdeSolver(
        final Function3<Double, Double, Double, Double> f,
        final Function3<Double, Double, Double, Double> g,
        final Point initialValue
    ) {
        this.f = f;
        this.g = g;
        this.initialValue = initialValue;
    }

    public void solve(final double h, final long iterations) {
        Point p = this.initialValue;
        for (long i = 0; i < iterations; i++) {
            final double k1X = h * this.f.apply(p.getT(), p.getX(), p.getY());
            final double k1Y = h * this.g.apply(p.getT(), p.getX(), p.getY());

            final double k2X = h * this.f.apply(p.getT() + 0.5 * h,p.getX() + 0.5 * k1X, p.getY() + 0.5 * k1Y);
            final double k2Y = h * this.g.apply(p.getT() + 0.5 * h,p.getX() + 0.5 * k1X, p.getY() + 0.5 * k1Y);

            final double k3X = h * this.f.apply(p.getT() + 0.5 * h,p.getX() + 0.5 * k2X, p.getY() + 0.5 * k2Y);
            final double k3Y = h * this.g.apply(p.getT() + 0.5 * h,p.getX() + 0.5 * k2X, p.getY() + 0.5 * k2Y);

            final double k4X = h * this.f.apply(p.getT() + h,p.getX() + k3X, p.getY() + k3Y);
            final double k4Y = h * this.g.apply(p.getT() + h,p.getX() + k3X, p.getY() + k3Y);

            final double nextT = p.getT() + h;
            final double nextX = this.nextStep(p.getX(), k1X, k2X, k3X, k4X);
            final double nextY = this.nextStep(p.getY(), k1Y, k2Y, k3Y, k4Y);
            p = new Point(nextT, nextX, nextY);
            System.out.println(p);
        }
    }

    private double nextStep(double previousStep, double k1, double k2, double k3, double k4) {
        final double coefficientSum = k1 + (2 * k2) + (2 * k3) + k4;
        return previousStep + (coefficientSum / 6);
    }
}
