package drawing.data;

import com.google.gson.Gson;
import drawing.entity.lines.LineList;
import drawing.entity.shape.Shape;
import drawing.helper.AlertHelper;
import drawing.helper.ShapeDetectorImpl;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo: 接口实现类
 */
public class FileServiceImpl implements FileService {


    private ShapeDetectorImpl detectShapeHelper;

    private AlertHelper alertHelper;

    private Stage stage;

    private static final int DATA_LENGTH = 10000000;

    private Logger log = Logger.getLogger("FileService");

    private FileServiceImpl() {
        detectShapeHelper = new ShapeDetectorImpl();
        alertHelper = new AlertHelper();
    }

    public FileServiceImpl(Stage stage) {
        this();
        this.stage = stage;
    }


    @Override
    public void saveShape(List<Shape> shapes, File dir) {
        LineList[] lineLists = new LineList[shapes.size()];
        for (int i = 0; i < shapes.size(); i++) {
            lineLists[i] = shapes.get(i).getLineList();
        }
        String shapeJson = new Gson().toJson(lineLists);

        if (!dir.isDirectory()) {
            alertHelper.setDialog("错误", "这不是文件夹！", this.stage);
        }
        String path = dir.getAbsolutePath();
        File file = new File(path + System.currentTimeMillis() + ".shape");
        if (file.exists()) {
            alertHelper.setDialog("错误", "文件已经存在！", this.stage);
        }

        FileOutputStream shapeOutputStream = null;
        try {
            file.createNewFile();
            shapeOutputStream = new FileOutputStream(file);
            shapeOutputStream.write(shapeJson.getBytes());
        } catch (IOException e) {
            alertHelper.setDialog("错误", "文件写入错误！", this.stage);
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                shapeOutputStream.close();
            } catch (IOException e) {
                alertHelper.setDialog("错误", "字节流关闭错误！", this.stage);
                log.log(Level.SEVERE, e.getMessage());
            }
        }
    }


    @Override
    public List<Shape> readShape(File file) {
        FileInputStream shapeInputStream = null;
        ArrayList<Shape> shapes = new ArrayList<>();

        if (!file.exists()) {
            alertHelper.setDialog("错误", "文件不存在！", this.stage);
            return shapes;
        }

        try {
            shapeInputStream = new FileInputStream(file);
            byte[] shapesByteData = new byte[DATA_LENGTH];
            int cursor = 0;
            while ((cursor = shapeInputStream.read(shapesByteData)) != -1) {
                String data = new String(shapesByteData, 0, cursor);
                LineList[] lineLists = new Gson().fromJson(data, LineList[].class);
                for (LineList lineList : lineLists) {
                    shapes.add(getShape(lineList));
                }
            }
            return shapes;
        } catch (Exception e) {
            alertHelper.setDialog("错误", "文件读取失败！", this.stage);
            log.log(Level.SEVERE, e.getMessage());

        } finally {
            try {
                shapeInputStream.close();
            } catch (IOException e) {
                alertHelper.setDialog("错误", "字节流关闭错误！", this.stage);
                log.log(Level.SEVERE, e.getMessage());

            }
        }
        return shapes;
    }

    private Shape getShape(LineList lineList) {
        return detectShapeHelper.detectShape(lineList);
    }

}
