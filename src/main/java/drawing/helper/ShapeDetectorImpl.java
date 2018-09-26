package drawing.helper;

import drawing.entity.lines.LineList;
import drawing.entity.shape.Shape;
import drawing.entity.shape.ShapeFactory;
import drawing.entity.shape.ShapeType;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo: 用于侦测Shape类型
 */
public class ShapeDetectorImpl implements ShapeDetector {

    private ShapeFactory shapeFactory;

    public ShapeDetectorImpl() throws UnsatisfiedLinkError{
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

    @Override
    public Shape detectShape(LineList lineList) {
        if(lineList==null){
            return shapeFactory.getShape(ShapeType.Unidentified, lineList);
        }else {
            ShapeType shapeType = detect(lineList);
            return shapeFactory.getShape(shapeType, lineList);
        }
    }

}
