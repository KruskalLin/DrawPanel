package drawing.controller.ui;

import drawing.entity.lines.Line;
import drawing.entity.lines.LineList;
import drawing.entity.lines.LinePoint;
import drawing.entity.shape.Shape;
import drawing.helper.CanvasHelper;
import drawing.helper.ShapeDetector;
import drawing.helper.ShapeDetectorImpl;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/26
 * @Todo: 子模版Canvas，提供画板功能
 */
public class PanelCanvas extends Canvas implements FXMLTemplateLoader {
    private GraphicsContext graphicsContext;

    private Line line;

    private LineList lineList;

    private CanvasHelper canvasHelper;

    private ShapeDetector detectShapeHelper;


    public PanelCanvas() {
        load();
        graphicsContext = this.getGraphicsContext2D();
        canvasHelper = new CanvasHelper();
    }

    public Shape getDetectedShape() {
        Shape shape = detectShapeHelper.detectShape(lineList);
        clearLineList();
        return shape;
    }

    public void clear() {
        canvasHelper.refreshCanvas(graphicsContext);
        clearLineList();
    }

    /**
     * @Description: 重新根据shapes绘制界面
     * @author Popping Lim
     * @date 2018/9/26
     */
    public void renderShapes(List<Shape> shapes) {
        canvasHelper.refreshCanvas(graphicsContext);
        for (Shape shape : shapes) {
            shape.draw(graphicsContext);
        }
        if(lineList!=null){
            lineList.draw(graphicsContext);
        }
    }

    /**
     * @Description: 重新根据shapes绘制界面，添加模拟界面
     * @author Popping Lim
     * @date 2018/9/26
     */
    public void renderSimulationShapes(List<Shape> shapes) {
        canvasHelper.refreshCanvas(graphicsContext);
        for (Shape shape : shapes) {
            shape.draw(graphicsContext);
            canvasHelper.setStroke(graphicsContext, Color.ORANGE);
            shape.drawSimulation(graphicsContext);
            canvasHelper.setStroke(graphicsContext, Color.BLUE);
        }
        if(lineList!=null){
            lineList.draw(graphicsContext);
        }
    }


    public void initPanel(){
        canvasHelper.refreshCanvas(graphicsContext);
        canvasHelper.setStroke(graphicsContext, Color.BLUE);
    }

    public void initDetector() throws UnsatisfiedLinkError {
        detectShapeHelper = new ShapeDetectorImpl();
    }

    /**
     * @Description: 高亮方法，配合table
     * @author Popping Lim
     * @date 2018/9/26
     */
    public void highlightShape(List<Shape> shapes, Shape highlightShape) {
        canvasHelper.refreshCanvas(graphicsContext);
        for (Shape shape : shapes) {
            shape.draw(graphicsContext);
        }
        canvasHelper.setStroke(graphicsContext, Color.RED);
        highlightShape.draw(graphicsContext);
        canvasHelper.setStroke(graphicsContext, Color.BLUE);
    }


    @Override
    public String getURL() {
        return "/canvas.fxml";
    }

    @FXML
    private void startDraw(MouseEvent e) {
        this.line = new Line();
        if (this.lineList == null) {
            lineList = new LineList();
        }
        line.addPoint(new LinePoint(e.getX(),e.getY()));
        graphicsContext.beginPath();
        graphicsContext.moveTo(e.getX(), e.getY());
        graphicsContext.stroke();
    }

    @FXML
    private void keepDraw(MouseEvent e) {
        line.addPoint(new LinePoint(e.getX(),e.getY()));
        graphicsContext.lineTo(e.getX(), e.getY());
        graphicsContext.stroke();
    }

    @FXML
    private void endDraw(MouseEvent e) {
        this.lineList.addDrawnLine(this.line);
    }

    private void clearLineList() {
        lineList = null;
    }
}
