package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.biz.UserBiz;
import com.etc.jump.bizimpl.UserBizImpl;
import com.etc.jump.util.FilePath;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable{

    @FXML
    private AnchorPane loginPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginbtn;

    @FXML
    private AnchorPane leftbtn;

    @FXML
    private AnchorPane rightbtn;

    private UserBiz userBiz = new UserBizImpl();

    public void leftbtnClicked(){//返回logo页面
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setOnFinished(event -> {
            try {
                //载入新场景
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePath.logoFXMLPath));
                Pane logoStage = fxmlLoader.load();
                Scene logoScene = new Scene(logoStage);
                //用当前Stage来打开新的场景
                Stage curStage = (Stage) loginPane.getScene().getWindow();
                curStage.setTitle("首页");
                curStage.setScene(logoScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    public void rightbtnClicked(){//进入注册页面
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setOnFinished(event -> {
            try {
                //载入新场景
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePath.registerFXMLPath));
                Pane registerStage = fxmlLoader.load();
                Scene registerScene = new Scene(registerStage);
                //用当前Stage来打开新的场景
                Stage curStage = (Stage) loginPane.getScene().getWindow();
                curStage.setTitle("注册");
                curStage.setScene(registerScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    public void leftbtnEntered(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));
        scaleTransition.setNode(leftbtn);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(0.9);

        scaleTransition.play();
    }

    public void leftbtnExited(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
        scaleTransition.setNode(leftbtn);
        scaleTransition.setFromX(1.5);
        scaleTransition.setFromY(0.9);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        scaleTransition.play();
    }

    public void rightbtnEntered(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));
        scaleTransition.setNode(rightbtn);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(0.9);

        scaleTransition.play();
    }

    public void rightbtnExited(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
        scaleTransition.setNode(rightbtn);
        scaleTransition.setFromX(1.5);
        scaleTransition.setFromY(0.9);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        scaleTransition.play();
    }

    public void loginbtnClicked(){
        if(!(username.getText().equals("")||password.getText().equals(""))){
            Main.user = userBiz.login(username.getText(),password.getText());
            if (Main.user!=null){
                //登录成功
                FadeTransition fadeTransition = new FadeTransition();
                fadeTransition.setNode(loginPane);
                fadeTransition.setFromValue(1);
                fadeTransition.setToValue(0);
                fadeTransition.setDuration(Duration.millis(100));
                fadeTransition.setOnFinished(event -> {
                try {
                    //载入新场景
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePath.indexFXMLPath));
                    Pane indexStage = fxmlLoader.load();
                    Scene indexScene = new Scene(indexStage);
                    //用当前Stage来打开新的场景
                    Stage curStage = (Stage) loginPane.getScene().getWindow();
                    curStage.setTitle("首页");
                    curStage.setWidth(600);
                    curStage.setHeight(740);
                    curStage.setScene(indexScene);
                    curStage.centerOnScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                });
                fadeTransition.play();
            }else {//登录失败
//                username.clear();
                password.clear();
                username.setUnFocusColor(Color.rgb(255,0,0));
                password.setUnFocusColor(Color.rgb(255,0,0));
            }
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //一个渐变的载入动画
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(200));
        fadeTransition.setNode(loginPane);
        fadeTransition.setFromValue(0d);
        fadeTransition.setToValue(1d);
        fadeTransition.play();

        //文本框焦点
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){//用户名框获得焦点
                username.setUnFocusColor(Color.rgb(161,161,161));
                password.setUnFocusColor(Color.rgb(161,161,161));
            }else {//用户名框失去焦点
                if(username.getText().equals("")){
                    username.setUnFocusColor(Color.rgb(255,0,0));
                }else if (password.getText().equals("")){
                    password.setUnFocusColor(Color.rgb(255,0,0));
                }
            }
        });

        //密码框焦点
        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){//密码框获得焦点
                if(username.getText().equals("")){
                    username.setUnFocusColor(Color.rgb(255,0,0));
                }else {
                    username.setUnFocusColor(Color.rgb(161, 161, 161));
                    password.setUnFocusColor(Color.rgb(161, 161, 161));
                }
            }else {//密码框失去焦点
                if(username.getText().equals("")){
                    username.setUnFocusColor(Color.rgb(255,0,0));
                }else if (password.getText().equals("")){
                    password.setUnFocusColor(Color.rgb(255,0,0));
                }
            }
        });

    }
}
