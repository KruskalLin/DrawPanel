package Helper;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.shape.Shape;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.paint.Color;

import java.util.Iterator;

public class TreeTableHelper {

    public JFXTreeTableColumn<Shape, String> initColumn(String columnName) {
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
