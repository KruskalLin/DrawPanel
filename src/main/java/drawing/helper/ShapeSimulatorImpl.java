package drawing.helper;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;

import static org.opencv.imgproc.Imgproc.minAreaRect;
import static org.opencv.imgproc.Imgproc.minEnclosingTriangle;
import static org.opencv.imgproc.Imgproc.minEnclosingCircle;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/26
 * @Todo: 利用opencv的包络做模拟图像
 */
public class ShapeSimulatorImpl implements ShapeSimulator{
    public ShapeSimulatorImpl(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public Point[] getRectSimulation(Point[] points) {
        RotatedRect rotatedRect = minAreaRect(new MatOfPoint2f(points));
        Point[] rect = new Point[4];
        rotatedRect.points(rect);
        return rect;
    }

    @Override
    public Point[] getTriangleSimulation(Point[] points) {
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        minEnclosingTriangle(new MatOfPoint2f(points), matOfPoint2f);
        return matOfPoint2f.toArray();
    }

    @Override
    public float[] getCircleSimulation(Point[] points) {
        Point center = new Point();
        float[] params = new float[3];
        minEnclosingCircle(new MatOfPoint2f(points), center, params);
        params[1] = (float) center.x;
        params[2] = (float) center.y;
        return params;
    }

}
