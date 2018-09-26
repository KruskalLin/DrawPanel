package drawing.entity.shape;

import drawing.entity.lines.LineList;
import javafx.scene.canvas.GraphicsContext;
import org.opencv.core.Point;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo: Circle Type
 */

public class Circle extends Shape {
    private Point center;
    private float radius;

    public Circle(ShapeType shapeType, LineList lineList) {
        super(shapeType, lineList);
    }

    @Override
    public void drawSimulation(GraphicsContext gc) {
        assert center != null;
        gc.strokeOval(center.x - radius, center.y - radius, radius*2, radius*2);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
