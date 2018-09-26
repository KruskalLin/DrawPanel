package drawing.entity.shape;

import drawing.entity.lines.LineList;
import javafx.scene.canvas.GraphicsContext;
import org.opencv.core.Point;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:  Triangle type
 */
public class Triangle extends Shape {
    private Point[] points;

    public Triangle(ShapeType shapeType, LineList lineList) {
        super(shapeType, lineList);
    }


    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public void drawSimulation(GraphicsContext gc) {
        assert points.length == 3;
        gc.strokePolygon(new double[]{points[0].x,points[1].x,points[2].x},new double[]{points[0].y,points[1].y,points[2].y},3);
    }
}
