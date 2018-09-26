package drawing.entity.lines;

import javafx.scene.canvas.GraphicsContext;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo:  包装用户所画的若干条线，方便判断
 */
public class LineList {

    private final ArrayList<Line> lines = new ArrayList<Line>();

    public void addDrawnLine(Line point) {
        this.lines.add(point);
    }

    public int size() {
        return lines.size();
    }

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < size(); i++) {
            Line line = lines.get(i);
            LinePoint point = null;
            LinePoint nextPoint = null;
            for (Iterator iter = line.iterator(); iter.hasNext(); ) {
                if (point == null) {
                    point = (LinePoint) iter.next();
                    nextPoint = point;
                }
                if (iter.hasNext()) {
                    nextPoint = (LinePoint) iter.next();
                }
                gc.strokeLine(point.getX(), point.getY(), nextPoint.getX(), nextPoint.getY());
                point = nextPoint;
            }
        }
    }

    public Point[] toPoints() {
        ArrayList<LinePoint> pointsList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            Line line = lines.get(i);
            for (Iterator iter = line.iterator(); iter.hasNext(); ) {
               pointsList.add((LinePoint)iter.next());
            }
        }
        Point[] points = new Point[pointsList.size()];
        for(int i=0;i<points.length;i++){
            points[i] = pointsList.get(i).toPoint();
        }
        return points;
    }

}
