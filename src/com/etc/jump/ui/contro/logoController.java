package com.etc.jump.ui.contro;

import com.etc.jump.util.FilePath;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class logoController implements Initializable{

    @FXML
    private Pane logoPane;
    public void JFXBtnClicked(){

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(logoPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setOnFinished(event -> {
            try {
                //载入新场景
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePath.loginFXMLPath));
                Pane loginStage = fxmlLoader.load();
                Scene loginScene = new Scene(loginStage);
                //用当前Stage来打开新的场景
                Stage curStage = (Stage) logoPane.getScene().getWindow();
                curStage.setTitle("登录");
                curStage.setScene(loginScene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    @FXML
    private JFXButton btn1;
    public void JFXBtn1MouseEntered(){
        JFXBtnMouseEntered(btn1);
    }
    public void JFXBtn1MouseExited(){
        JFXBtnMouseExited(btn1);
    }

    @FXML
    private JFXButton btn2;
    public void JFXBtn2MouseEntered(){
        JFXBtnMouseEntered(btn2);
    }
    public void JFXBtn2MouseExited(){
        JFXBtnMouseExited(btn2);
    }

    @FXML
    private JFXButton btn3;
    public void JFXBtn3MouseEntered(){
        JFXBtnMouseEntered(btn3);
    }
    public void JFXBtn3MouseExited(){
        JFXBtnMouseExited(btn3);
    }

    @FXML
    private JFXButton btn4;
    public void JFXBtn4MouseEntered(){
        JFXBtnMouseEntered(btn4);
    }
    public void JFXBtn4MouseExited(){
        JFXBtnMouseExited(btn4);
    }


    private void JFXBtnMouseEntered(JFXButton JFXbtn){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setNode(JFXbtn);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void JFXBtnMouseExited(JFXButton JFXbtn){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setNode(JFXbtn);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //一个渐变的载入动画
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(logoPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
