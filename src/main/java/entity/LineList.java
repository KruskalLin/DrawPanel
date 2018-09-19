package entity;

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

    private final ArrayList<DrawnLine> drawnLines = new ArrayList<DrawnLine>();

    public void addDrawnLine(DrawnLine point) {
        this.drawnLines.add(point);
    }


    @Deprecated
    public int size(){
        return drawnLines.size();
    }


    @Deprecated
    public void draw(GraphicsContext gc) {
        for(int i=0;i<drawnLines.size();i++){
            Point point = null;
            for(Iterator iter = drawnLines.get(i).iterator();iter.hasNext();){
                if(point == null){
                    point = (Point) iter.next();
                    gc.moveTo(point.getX(), point.getY());
                }
                Point nextPoint = (Point) iter.next();
                gc.lineTo(nextPoint.getX(), nextPoint.getY());
                gc.stroke();
                gc.moveTo(nextPoint.getX(), nextPoint.getY());
                point = nextPoint;
            }
        }
    }
}
