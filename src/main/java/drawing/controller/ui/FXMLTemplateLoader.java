package drawing.controller.ui;

import javafx.fxml.FXMLLoader;
import java.io.IOException;


/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/26
 * @Todo: FXML子模版抽象类
 */

public interface FXMLTemplateLoader {
    /**
     * @Description: 加载FXML文件
     * @author Popping Lim
     * @date 2018/9/26
     */
    default void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getURL();
}
