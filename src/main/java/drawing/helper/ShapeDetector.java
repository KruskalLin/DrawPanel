package drawing.helper;

import drawing.entity.lines.LineList;
import drawing.entity.shape.Shape;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/26
 * @Todo: 用于侦测Shape类型
 */
public interface ShapeDetector {
    public Shape detectShape(LineList lineList);
}
