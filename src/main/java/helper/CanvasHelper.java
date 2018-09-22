package helper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:
 */
public class CanvasHelper {


    public void refreshCanvas(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.fill();
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.strokeRect(0, 0, canvasWidth, canvasHeight);
        setStroke(gc, Color.BLUE);
    }

    public void setStroke(GraphicsContext gc, Paint color) {
        gc.setStroke(color);
        gc.setLineWidth(1);
    }
}
