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
public class DrawnLine implements Iterable<LinePoint> {

    private final ArrayList<LinePoint> linePoints = new ArrayList<LinePoint>();

    public void addPoint(LinePoint linePoint) {
        this.linePoints.add(linePoint);
    }

    @Override
    public Iterator<LinePoint> iterator() {
        return new Line();
    }

    private class Line implements Iterator<LinePoint> {

        private int index;

        public Line() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < linePoints.size();
        }

        @Override
        public LinePoint next() {
            if (this.hasNext()) {
                this.index++;
                return linePoints.get(this.index - 1);
            }
            return null;
        }
    }
}
