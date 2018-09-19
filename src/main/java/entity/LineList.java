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

    public int size(){
        return drawnLines.size();
    }

}
