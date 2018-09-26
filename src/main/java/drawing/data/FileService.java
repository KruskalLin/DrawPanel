package drawing.data;

import drawing.entity.shape.Shape;

import java.io.File;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:  Data接口
 */
public interface FileService {
    /**
     * @Description: data层保存shapes到file中
     * @author Popping Lim
     * @date 2018/9/26
     */
    public void saveShape(List<Shape> shapes, File file);


    /**
     * @Description: data层读取shapes
     * @author Popping Lim
     * @date 2018/9/26
     */
    public List<Shape> readShape(File file);


}
