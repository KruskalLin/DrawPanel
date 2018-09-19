package entity.shape;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.LineList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Shape extends RecursiveTreeObject<Shape> {

    private StringProperty shapeType = new SimpleStringProperty();

    public Shape(ShapeType shapeType) {
        this.shapeType.set(shapeType.getType());
    }

    public StringProperty getShapeType() {
        return shapeType;
    }
}
