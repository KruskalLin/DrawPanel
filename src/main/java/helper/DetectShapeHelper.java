package helper;

import entity.lines.LineList;
import entity.shape.Shape;
import entity.shape.ShapeFactory;
import entity.shape.ShapeType;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:
 */
public class DetectShapeHelper {

    public ShapeFactory shapeFactory;

    public DetectShapeHelper () {
        shapeFactory = new ShapeFactory();
    }

    private ShapeType detect(LineList list) {
        switch (list.size()){
            case 1: return ShapeType.Circle;
            case 3: return ShapeType.Triangle;
            case 4: return ShapeType.Rectangle;
            default: return ShapeType.Unidentified;
        }
    }
    public Shape detectShape(LineList lineList) {
        if(lineList==null){
            Shape shape = shapeFactory.getShape(ShapeType.Unidentified, lineList);
            return shape;
        }else {
            ShapeType shapeType = detect(lineList);
            Shape shape = shapeFactory.getShape(shapeType, lineList);
            return shape;
        }
    }

}
