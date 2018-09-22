package data;

import entity.lines.LineList;
import entity.shape.Shape;

import java.io.File;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:
 */
public interface FileService {

    public void saveShape(Shape[] shapeArr,  File file);

    public Shape[] readShape(File file);


}
