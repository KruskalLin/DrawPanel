package drawing.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import drawing.controller.ui.PanelCanvas;
import drawing.controller.ui.ShapeTable;
import drawing.data.FileService;
import drawing.data.FileServiceImpl;
import drawing.helper.AlertHelper;
import drawing.entity.shape.Shape;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/19
 * @Todo: FXController, MVC中的Controller基类 这个既是整个panel的controller
 */
public class PanelController implements Initializable{

    @FXML
    private PanelCanvas canvas;

    @FXML
    private ShapeTable table;

    @FXML
    private JFXButton drawFinished;

    @FXML
    private JFXToggleButton simulator;

    private FileService fileService;

    private AlertHelper alertHelper;

    private SimpleBooleanProperty isSimulating = new SimpleBooleanProperty(false);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertHelper = new AlertHelper();
        canvas.initPanel();
        initSimulation();
        initTable();

        try{
            canvas.initDetector();
        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            simulator.disableProperty().set(false);
        }
    }


    @FXML
    private void finishDraw() {
        table.addShape(canvas.getDetectedShape());
    }

    @FXML
    private void saveFile() {
        if(fileService == null) {
            fileService = new FileServiceImpl((Stage) canvas.getScene().getWindow());
        }
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("保存文件");
        File file = chooser.showDialog(canvas.getScene().getWindow());
        if (file != null) {
            table.addShape(canvas.getDetectedShape());
            fileService.saveShape(table.getShapes(), file);
        }
    }


    @FXML
    private void readFile() {
        if(fileService == null) {
            fileService = new FileServiceImpl((Stage) canvas.getScene().getWindow());
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开文件");
        FileChooser.ExtensionFilter shapeFilter = new FileChooser.ExtensionFilter("Shape文件 (*.shape)", "*.shape");
        fileChooser.getExtensionFilters().add(shapeFilter);
        File file = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (file != null) {
            List<Shape> shapes = fileService.readShape(file);
            readShapes(shapes);
        }
    }

    @FXML
    private void help() {
        alertHelper.setDialog("帮助", "双击可删除对应形状，文件只能读取.shape文件", (Stage) canvas.getScene().getWindow());
    }

    @FXML
    private void clear() {
        canvas.clear();
        table.clearTable();
    }

    private void initTable() {
        table.initTable();
        table.setTableEvent(canvas);
    }

    private void initSimulation() {
        simulator.selectedProperty().bindBidirectional(isSimulating);
        isSimulating.addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                canvas.renderSimulationShapes(table.getShapes());
            }else{
                canvas.renderShapes(table.getShapes());
            }
        });
        drawFinished.disableProperty().bindBidirectional(isSimulating);
    }


    private void readShapes(List<Shape> shapes){
        if(shapes != null) {
            table.setShapes(shapes);
            canvas.renderShapes(shapes);
        }
    }
}
