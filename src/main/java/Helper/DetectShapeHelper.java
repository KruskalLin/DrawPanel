package Helper;

import entity.LineList;
import entity.shape.Shape;
import entity.shape.ShapeType;

public class DetectShapeHelper {
    public ShapeType detect(LineList list) {
        switch (list.size()){
            case 1: return ShapeType.Circle;
            case 3: return ShapeType.Triangle;
            case 4: return ShapeType.Rectangle;
            default: return ShapeType.Unidentified;
        }
    }

}
