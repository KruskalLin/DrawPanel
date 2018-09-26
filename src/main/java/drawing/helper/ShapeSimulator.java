package drawing.helper;

import org.opencv.core.Point;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/26
 * @Todo:
 */
public interface ShapeSimulator {
    /**
     * @Description: 得到三角闭包
     * @author Popping Lim
     * @date 2018/9/26
     */
    public Point[] getTriangleSimulation(Point[] points);

    /**
     * @Description: 得到斜正方闭包
     * @author Popping Lim
     * @date 2018/9/26
     */
    public Point[] getRectSimulation(Point[] points);

    /**
     * @Description: 得到圆闭包
     * @author Popping Lim
     * @date 2018/9/26
     */
    public float[] getCircleSimulation(Point[] points);
}
