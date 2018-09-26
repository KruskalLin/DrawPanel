package drawing.entity.lines;

import org.opencv.core.Point;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo: 点，为了防止和opencv的point重名改名为LinePoint
 */
public class LinePoint {
    private double x;
    private double y;


    public LinePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point toPoint() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "LinePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
