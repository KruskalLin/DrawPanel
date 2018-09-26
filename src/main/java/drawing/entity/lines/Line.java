package drawing.entity.lines;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo:  用户画的每一笔线，包含若干个点，继承了迭代器模式
 */
public class Line implements Iterable<LinePoint> {

    private final ArrayList<LinePoint> linePoints = new ArrayList<LinePoint>();

    public void addPoint(LinePoint linePoint) {
        this.linePoints.add(linePoint);
    }

    @Override
    public Iterator<LinePoint> iterator() {
        return new DrawnLine();
    }

    private class DrawnLine implements Iterator<LinePoint> {

        private int index;

        public DrawnLine() {
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
