package data;

import com.google.gson.Gson;
import entity.lines.LineList;
import entity.shape.Shape;
import entity.shape.ShapeFactory;
import entity.shape.ShapeType;
import helper.AlertHelper;
import helper.DetectShapeHelper;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/22
 * @Todo:
 */
public class FileServiceImpl implements FileService{


    private DetectShapeHelper detectShapeHelper;

    private AlertHelper alertHelper;

    private Stage stage;

    private FileServiceImpl() {
        detectShapeHelper = new DetectShapeHelper();
        alertHelper = new AlertHelper();
    }

    public FileServiceImpl(Stage stage) {
        this();
        this.stage = stage;
    }


    @Override
    public void saveShape(Shape[] shapes, File dir) {
        LineList[] lineLists = new LineList[shapes.length];
        for(int i=0;i<shapes.length;i++){
            lineLists[i] = shapes[i].getLineList();
        }
        String shapeJson = new Gson().toJson(lineLists);
        if(!dir.isDirectory()) {
            alertHelper.setDialog("错误", "这不是文件夹！", this.stage);
        }
        String path = dir.getAbsolutePath();
        File file = new File(path + System.currentTimeMillis()+".shape");

        if(file.exists()){
            alertHelper.setDialog("错误", "文件已经存在！", this.stage);
        }

        FileOutputStream shapeOutputStream = null;
        try{
            file.createNewFile();
            shapeOutputStream = new FileOutputStream(file);
            shapeOutputStream.write(shapeJson.getBytes());
        }catch (IOException e){
            alertHelper.setDialog("错误", "未知错误！", this.stage);
        } finally {
            try {
                shapeOutputStream.close();
            }catch (IOException e) {
                alertHelper.setDialog("错误", "未知错误！", this.stage);
            }
        }
    }




    @Override
    public Shape[] readShape(File file) {
        FileInputStream shapeInputStream = null;
        if(!file.exists()){
            alertHelper.setDialog("错误", "文件不存在！", this.stage);
            return null;
        }
        Shape[] shapes = null;
        try {
            shapeInputStream = new FileInputStream(file);
            byte bytesData[]=new byte[10000000];
            int cursor=0;
            while((cursor=shapeInputStream.read(bytesData))!= -1){
                String data = new String(bytesData,0,cursor);
                LineList[] lineLists = new Gson().fromJson(data, LineList[].class);
                shapes = new Shape[lineLists.length];
                for(int i =0;i<lineLists.length;i++){
                    shapes[i] = getShape(lineLists[i]);
                }
            }
            return shapes;

        } catch (Exception e) {
            alertHelper.setDialog("错误", "文件读取失败！", this.stage);
        } finally{
            try {
                shapeInputStream.close();
            } catch (IOException e) {
                alertHelper.setDialog("错误", "未知错误！", this.stage);
            }
        }
        return null;
    }

    private Shape getShape(LineList lineList) {
        return detectShapeHelper.detectShape(lineList);
    }

}
