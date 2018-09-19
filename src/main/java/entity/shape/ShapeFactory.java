package entity.shape;

public class ShapeFactory {
    public Shape getShape(ShapeType shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType == ShapeType.Circle){
            return new Circle(ShapeType.Circle);
        } else if(shapeType == ShapeType.Rectangle){
            return new Rectangle(ShapeType.Rectangle);
        } else if(shapeType == ShapeType.Triangle){
            return new Triangle(ShapeType.Triangle);
        }
        return null;
    }
}
