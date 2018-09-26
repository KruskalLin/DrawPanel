package drawing.entity.shape;

import drawing.entity.lines.LineList;
import drawing.helper.ShapeSimulator;
import drawing.helper.ShapeSimulatorImpl;
import org.opencv.core.Point;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo: Shape工厂
 */
public class ShapeFactory {
    private ShapeSimulator shapeSimulator;

    public ShapeFactory() throws UnsatisfiedLinkError{
        shapeSimulator = new ShapeSimulatorImpl();
    }

    public Shape getShape(ShapeType shapeType, LineList lineList){
        if(shapeType == null){
            return null;
        }
        switch (shapeType) {
            case Circle:
                Circle circle = new Circle(ShapeType.Circle, lineList);
                float[] params = shapeSimulator.getCircleSimulation(lineList.toPoints());
                circle.setCenter(new Point(params[1], params[2]));
                circle.setRadius(params[0]);
                return circle;
            case Rectangle:
                Rectangle rectangle = new Rectangle(ShapeType.Rectangle, lineList);
                rectangle.setPoints(shapeSimulator.getRectSimulation(lineList.toPoints()));
                return rectangle;
            case Triangle:
                Triangle triangle = new Triangle(ShapeType.Triangle, lineList);
                triangle.setPoints(shapeSimulator.getTriangleSimulation(lineList.toPoints()));
                return triangle;
            default:
                return new Unidentified(ShapeType.Unidentified, lineList);
        }
    }
}
