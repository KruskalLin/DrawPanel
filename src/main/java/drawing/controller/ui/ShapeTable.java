package drawing.controller.ui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import drawing.entity.shape.Shape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:  Shape表子模版，暂存当下的shapes，并且提供增删的功能
 */
public class ShapeTable extends JFXTreeTableView<Shape> implements FXMLTemplateLoader {
    private ObservableList<Shape> shapes;

    public ShapeTable() {
        load();
    }

    public void initTable() {
        initData();
        initShapeColumn();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes.setAll(shapes);
    }

    public List<Shape> getShapes() {
        return new ArrayList<>(shapes);
    }

    public void clearTable() {
        this.shapes.clear();
    }

    public void setTableEvent(PanelCanvas canvas) {
        this.setRowFactory(table-> {
            TreeTableRow<Shape> row = new TreeTableRow<>();
            row.setOnMouseClicked(e->{
                if(row.getTreeItem()!=null) {
                    if (e.getClickCount() == 1) {
                        List<Shape> filShapes = shapes.stream().filter(shape -> shape != row.getTreeItem().getValue()).collect(Collectors.toList());
                        canvas.highlightShape(filShapes, row.getTreeItem().getValue());
                    } else if (e.getClickCount() == 2) {
                        shapes.remove(row.getTreeItem().getValue());
                        canvas.renderShapes(shapes);
                    }
                }
            });
            return row;
        });
    }

    @Override
    public String getURL() {
        return "/table.fxml";
    }

    private void initData() {
        this.shapes = FXCollections.observableArrayList();
        this.setShowRoot(false);
        final TreeItem<Shape> root = new RecursiveTreeItem<>(shapes, RecursiveTreeObject::getChildren);
        this.setRoot(root);
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
}
