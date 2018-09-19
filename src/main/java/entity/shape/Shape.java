package entity.shape;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.LineList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Shape extends RecursiveTreeObject<Shape> {

    private LineList lines;

    private StringProperty shapeType = new SimpleStringProperty();

    public Shape(ShapeType shapeType) {
        this.shapeType.set(shapeType.name());
    }
    public String getShape() {
        return this.shapeType.get();
    }

    public StringProperty getShapeType() {
        return shapeType;
    }

    public void setLines(LineList lines) {
        this.lines = lines;
    }

    public LineList getLines() {
        return lines;
    }
}
