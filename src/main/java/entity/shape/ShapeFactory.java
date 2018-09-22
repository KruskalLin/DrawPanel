package entity.shape;

import entity.lines.LineList;

public class ShapeFactory {
    public Shape getShape(ShapeType shapeType, LineList lineList){
        if(shapeType == null){
            return null;
        }
        if(shapeType == ShapeType.Circle){
            return new Circle(ShapeType.Circle, lineList);
        } else if(shapeType == ShapeType.Rectangle){
            return new Rectangle(ShapeType.Rectangle, lineList);
        } else if(shapeType == ShapeType.Triangle){
            return new Triangle(ShapeType.Triangle, lineList);
        }
        return new Unidentified(ShapeType.Unidentified, lineList);
    }
}
