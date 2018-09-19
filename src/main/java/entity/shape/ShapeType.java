package entity.shape;

public enum ShapeType {
    Circle("圆"),
    Triangle("三角形"),
    Rectangle("矩形"),
    Unidentified("未识别");


    private String type;

    ShapeType(String str) {
        this.type = str;
    }

    public String getType() {
        return type;
    }
}
