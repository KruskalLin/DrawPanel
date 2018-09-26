package drawing.entity.shape;

import drawing.entity.lines.LineList;
import javafx.scene.canvas.GraphicsContext;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo: the null type of shape
 */
public class Unidentified extends Shape {
    public Unidentified(ShapeType shapeType, LineList lineList) {
        super(shapeType, lineList);
    }

    /**
     * @Todo: Nothing
     * @author Popping Lim
     * @date 2018/9/26
     */
    @Override
    public void drawSimulation(GraphicsContext gc) {
    }
}
