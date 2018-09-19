package entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo:
 */
public class DrawnLine implements Iterable<Point> {

    private final ArrayList<Point> points = new ArrayList<Point>();

    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Line();
    }

    private class Line implements Iterator<Point> {

        private int index;

        public Line() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < points.size();
        }

        @Override
        public Point next() {
            if (this.hasNext()) {
                this.index++;
                return points.get(this.index - 1);
            }
            return null;
        }
    }
}
