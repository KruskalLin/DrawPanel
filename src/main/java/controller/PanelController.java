package controller;

import entity.DrawnLine;
import entity.LineList;
import entity.Point;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo:
 */
public class PanelController implements Initializable{

    @FXML
    private Canvas canvas;

    private static GraphicsContext graphicsContext;

    private DrawnLine drawnLine;

    private LineList lineList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
    }

    @FXML
    private void startDraw(MouseEvent e) {
        this.drawnLine = new DrawnLine();
        if (this.lineList == null) {
            lineList = new LineList();
        }
        graphicsContext.beginPath();
        graphicsContext.moveTo(e.getX(), e.getY());
        graphicsContext.stroke();
    }

    @FXML
    private void keepDraw(MouseEvent e) {
        drawnLine.addPoint(new Point(e.getX(),e.getY()));
        graphicsContext.lineTo(e.getX(), e.getY());
        graphicsContext.stroke();
    }

    @FXML
    private void endDraw(MouseEvent e) {
        this.lineList.addDrawnLine(this.drawnLine);
        System.out.println(lineList.size());
    }

    private void initDraw(GraphicsContext gc){
        refreshCanvas(gc);
        setStrokeColor(gc, Color.BLUE);
    }

    private void refreshCanvas(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.fill();
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.strokeRect(0, 0, canvasWidth, canvasHeight);
    }

    private void setStrokeColor(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        gc.setLineWidth(1);
    }

    @FXML
    private void finishDraw() {

    }



}
