package controller;

import Helper.DetectShapeHelper;
import Helper.TreeTableHelper;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.DrawnLine;
import entity.LineList;
import entity.LinePoint;
import entity.shape.Shape;
import entity.shape.ShapeFactory;
import entity.shape.ShapeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
    private JFXTreeTableView<Shape> table;

    private static GraphicsContext graphicsContext;

    private DrawnLine drawnLine;

    private LineList lineList;

    private ObservableList<Shape> shapes = FXCollections.observableArrayList();

    private ShapeFactory shapeFactory;

    private TreeTableHelper treeTableHelper;

    private DetectShapeHelper detectShapeHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        shapeFactory = new ShapeFactory();
        treeTableHelper = new TreeTableHelper();
        detectShapeHelper = new DetectShapeHelper();
        initDraw(graphicsContext);
        initTable();
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
        drawnLine.addPoint(new LinePoint(e.getX(),e.getY()));
        graphicsContext.lineTo(e.getX(), e.getY());
        graphicsContext.stroke();
    }

    @FXML
    private void endDraw(MouseEvent e) {
        this.lineList.addDrawnLine(this.drawnLine);
    }

    private void initDraw(GraphicsContext gc){
        this.refreshCanvas(gc);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
    }

    private void initTable() {
        table.setShowRoot(false);
        final TreeItem<Shape> root = new RecursiveTreeItem<>(shapes, RecursiveTreeObject::getChildren);
        table.setRoot(root);
        JFXTreeTableColumn column = this.treeTableHelper.initColumn("形状");
        column.setPrefWidth(table.getPrefWidth());
        table.getColumns().add(column);
    }

    @FXML
    private void finishDraw() {
        if(lineList==null){
            Shape shape = shapeFactory.getShape(ShapeType.Unidentified);
            shapes.add(shape);
        }else {
            ShapeType shapeType = detectShapeHelper.detect(lineList);
            Shape shape = shapeFactory.getShape(shapeType);
            lineList = null;
            shapes.add(shape);
        }
    }

    public void refreshCanvas(GraphicsContext gc) {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.fill();
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.strokeRect(0, 0, canvasWidth, canvasHeight);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
    }


}
