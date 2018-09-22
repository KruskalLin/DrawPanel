package controller;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controller.ui.ShapeTable;
import data.FileService;
import data.FileServiceImpl;
import helper.AlertHelper;
import helper.CanvasHelper;
import helper.DetectShapeHelper;
import entity.lines.Line;
import entity.lines.LineList;
import entity.lines.LinePoint;
import entity.shape.Shape;
import entity.shape.ShapeFactory;
import entity.shape.ShapeType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
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

    @FXML
    private ShapeTable table;

    private GraphicsContext graphicsContext;

    private Line line;

    private LineList lineList;

    private CanvasHelper canvasHelper;

    private FileService fileService;

    private AlertHelper alertHelper;

    private DetectShapeHelper detectShapeHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        canvasHelper = new CanvasHelper();
        alertHelper = new AlertHelper();
        detectShapeHelper = new DetectShapeHelper();
        initPanel(graphicsContext);
        initTable();
    }

    @FXML
    private void startDraw(MouseEvent e) {
        this.line = new Line();
        if (this.lineList == null) {
            lineList = new LineList();
        }
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


    @FXML
    private void finishDraw() {
        table.addShape(detectShapeHelper.detectShape(lineList));
        lineList = null;
    }

    @FXML
    private void saveFile() {
        assert fileService != null;
        if(fileService == null) {
            fileService = new FileServiceImpl((Stage) canvas.getScene().getWindow());
        }
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("保存文件");
        File file = chooser.showDialog(canvas.getScene().getWindow());
        if (file != null) {
            if(lineList!=null) {
                finishDraw();
            }
            fileService.saveShape(table.getShapes(), file);
        }
    }


    @FXML
    private void readFile() {
        assert fileService != null;
        if(fileService == null) {
            fileService = new FileServiceImpl((Stage) canvas.getScene().getWindow());
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开文件");
        FileChooser.ExtensionFilter shapeFilter = new FileChooser.ExtensionFilter("Shape文件 (*.shape)", "*.shape");
        fileChooser.getExtensionFilters().add(shapeFilter);
        File file = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (file != null) {
            Shape[] shapes = fileService.readShape(file);
            readShapes(shapes);
        }
    }

    @FXML
    private void help() {
        alertHelper.setDialog("帮助", "双击可删除对应形状，文件只能读取.shape文件", (Stage) canvas.getScene().getWindow());
    }

    private void initPanel(GraphicsContext gc){
        canvasHelper.refreshCanvas(gc);
        canvasHelper.setStroke(gc, Color.BLUE);
    }



    private void initTable() {
        table.initTable();
        table.setTableEvent(canvasHelper, graphicsContext);
    }


    private void readShapes(Shape[] shapes){
        canvasHelper.refreshCanvas(graphicsContext);
        assert shapes != null;
        if(shapes!=null) {
            table.setShapes(shapes);
            for (int i = 0; i < shapes.length; i++) {
                shapes[i].draw(graphicsContext);
            }
        }
    }
}
