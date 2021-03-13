package com.inamona.diffyq;

import io.vavr.Function3;

/**
 * @author christopher
 * @since 3/12/21
 */
public class SolverMain {

    public static void main(final String[] args) {

        final Function3<Double, Double, Double, Double> f = (t, x, y) -> x * y + t;
        final Function3<Double, Double, Double, Double> g = (t, x, y) -> t * y + x;

        final Linear2ndOrderOdeSolver solver = new Linear2ndOrderOdeSolver(f, g, new Point(0,1, -1));
        solver.solve(0.1, 10);
    }
}
