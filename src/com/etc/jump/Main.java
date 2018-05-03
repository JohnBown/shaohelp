package com.etc.jump;

import com.etc.jump.entity.Users;
import com.etc.jump.util.FilePath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;
    public static Users user;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        String logoFXMLPath = FilePath.logoFXMLPath;
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource(logoFXMLPath));
        Pane logoStage = fxmlLoader.load();
        Scene logoScene = new Scene(logoStage);

        window.setWidth(700);
        window.setHeight(440);

        window.setResizable(false);
        window.setScene(logoScene);
        window.setTitle("Logo");
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
