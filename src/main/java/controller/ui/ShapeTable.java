package controller.ui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.lines.LineList;
import entity.shape.Shape;
import helper.CanvasHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.paint.Color;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:
 */
public class ShapeTable extends JFXTreeTableView<Shape> {
    private ObservableList<Shape> shapes;

    public ShapeTable() {

    }

    public void initTable() {
        initData();
        initShapeColumn();
    }

    private void initData() {
        this.shapes = FXCollections.observableArrayList();
        this.setShowRoot(false);
        final TreeItem<Shape> root = new RecursiveTreeItem<>(shapes, RecursiveTreeObject::getChildren);
        this.setRoot(root);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void setShapes(Shape[] shapes) {
        this.shapes.setAll(shapes);
    }

    public Shape[] getShapes() {
        Shape[] shapeArr = new Shape[shapes.size()];
        for(int i=0;i<shapes.size();i++){
            shapeArr[i] = shapes.get(i);
        }
        return shapeArr;
    }

    public void clearTable() {
        this.shapes.clear();
    }


    private void initShapeColumn() {
        JFXTreeTableColumn<Shape, String> column = this.initColumn("形状");
        column.setPrefWidth(this.getPrefWidth());
        this.getColumns().add(column);
    }


    private JFXTreeTableColumn<Shape, String> initColumn(String columnName) {
        JFXTreeTableColumn<Shape, String> column = new JFXTreeTableColumn<>(columnName);
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Shape, String> param) -> {
            if (column.validateValue(param)) {
                return param.getValue().getValue().getShapeType();
            } else {
                return column.getComputedValue(param);
            }
        });
        return column;
    }


    public void setTableEvent(CanvasHelper canvasHelper, GraphicsContext gc) {
        this.setRowFactory(table-> {
            TreeTableRow<Shape> row = new TreeTableRow<>();
            row.setOnMouseClicked(e->{
                if(row.getTreeItem()!=null) {
                    if (e.getClickCount() == 1) {
                        canvasHelper.refreshCanvas(gc);
                        for (Shape shape : shapes) {
                            if (shape.equals(row.getTreeItem().getValue())) {
                                canvasHelper.setStroke(gc, Color.RED);
                                shape.draw(gc);
                            } else {
                                canvasHelper.setStroke(gc, Color.BLUE);
                                shape.draw(gc);
                            }
                        }
                        canvasHelper.setStroke(gc, Color.BLUE);
                    } else if (e.getClickCount() == 2) {
                        shapes.remove(row.getTreeItem().getValue());
                        canvasHelper.refreshCanvas(gc);
                        for (Shape shape : shapes) {
                            shape.draw(gc);
                        }
                    }
                }
            });
            return row;
        });
    }
}
