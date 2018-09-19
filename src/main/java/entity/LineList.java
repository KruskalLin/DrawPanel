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
public class LineList implements Iterable<DrawnLine> {

    private final ArrayList<DrawnLine> drawnLines = new ArrayList<DrawnLine>();

    public void addDrawnLine(DrawnLine point) {
        this.drawnLines.add(point);
    }

    public int size() {
        return drawnLines.size();
    }

    @Override
    public Iterator<DrawnLine> iterator() {
        return new Lines();
    }

    private class Lines implements Iterator<DrawnLine> {

        private int index;

        public Lines() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < drawnLines.size();
        }

        @Override
        public DrawnLine next() {
            if (this.hasNext()) {
                this.index++;
                return drawnLines.get(this.index - 1);
            }
            return null;
        }
    }
}
