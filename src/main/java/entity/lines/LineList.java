package entity.lines;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo:
 */
public class LineList{

    private final ArrayList<Line> lines = new ArrayList<Line>();

    public void addDrawnLine(Line point) {
        this.lines.add(point);
    }

    public int size(){
        return lines.size();
    }

    public void draw(GraphicsContext gc) {
        for(int i=0;i<size();i++){
            Line line = lines.get(i);
            LinePoint point = null;
            LinePoint nextPoint = null;
            for (Iterator iter = line.iterator();iter.hasNext();){
                if(point == null) {
                    point = (LinePoint) iter.next();
                    nextPoint = point;
                }
                if(iter.hasNext()) {
                    nextPoint = (LinePoint) iter.next();
                }
                gc.strokeLine(point.getX(), point.getY(), nextPoint.getX(), nextPoint.getY());
                point = nextPoint;
            }
        }
    }
}
