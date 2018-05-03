package com.etc.jump.ui.contro;


import com.etc.jump.Main;
import com.etc.jump.biz.CityBiz;
import com.etc.jump.biz.UserBiz;
import com.etc.jump.bizimpl.CityBizImpl;
import com.etc.jump.bizimpl.UserBizImpl;
import com.etc.jump.entity.City;
import com.etc.jump.entity.Users;
import com.etc.jump.util.FilePath;
import com.etc.jump.util.JDBCUtil;
import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField checkpassword;

    @FXML
    private JFXComboBox<String> citycomb1;

    @FXML
    private JFXComboBox<String> citycomb2;

    @FXML
    private JFXComboBox<String> citycomb3;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField school;

    @FXML
    private JFXTextField phonecall;

    @FXML
    private AnchorPane leftbtn;

    @FXML
    private JFXCheckBox agreechk;

    @FXML
    private JFXButton registerbtn;

    UserBiz userBiz = new UserBizImpl();

    CityBiz citybizimpl = new CityBizImpl();
    ArrayList<String> province;
    ArrayList<String> city;
    ArrayList<String> district;

    public void leftbtnEntered() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));
        scaleTransition.setNode(leftbtn);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(0.9);

        scaleTransition.play();
    }

    public void leftbtnExited() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
        scaleTransition.setNode(leftbtn);
        scaleTransition.setFromX(1.5);
        scaleTransition.setFromY(0.9);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        scaleTransition.play();
    }

    public void leftbtnClicked() {//返回login页面
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(registerPane);
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
                Stage curStage = (Stage) registerPane.getScene().getWindow();
                curStage.setTitle("登录");
                curStage.setScene(loginScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    private void resetUnFocusColor() {
        username.setUnFocusColor(Color.rgb(161, 161, 161));
        password.setUnFocusColor(Color.rgb(161, 161, 161));
        checkpassword.setUnFocusColor(Color.rgb(161, 161, 161));
        citycomb1.setUnFocusColor(Color.rgb(161, 161, 161));
        citycomb2.setUnFocusColor(Color.rgb(161, 161, 161));
        citycomb3.setUnFocusColor(Color.rgb(161, 161, 161));
        address.setUnFocusColor(Color.rgb(161, 161, 161));
        school.setUnFocusColor(Color.rgb(161, 161, 161));
        phonecall.setUnFocusColor(Color.rgb(161, 161, 161));
    }

    private void setUnFocusColor() {
        if (username.getText().equals("")) {
            username.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (password.getText().equals("")) {
            password.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (checkpassword.getText().equals("")) {
            checkpassword.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (citycomb1.getValue() == null) {
            citycomb1.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (citycomb2.getValue() == null) {
            citycomb2.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (citycomb3.getValue() == null) {
            citycomb3.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (address.getText().equals("")) {
            address.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (school.getText().equals("")) {
            school.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (phonecall.getText().equals("")) {
            phonecall.setUnFocusColor(Color.rgb(255, 0, 0));
        }
    }

    @FXML
    private void loadDialog(ActionEvent event){

        JFXDialogLayout content = new JFXDialogLayout();
        Text titeltext = new Text("用户协议");
        Text bodytext = new Text("第一!绝对不意气用事!\n" +
                "第二!绝对不漏判任何一件坏事!\n" +
                "第三!绝对裁判的公正漂亮!");
        content.setHeading(titeltext);
        content.setBody(bodytext);


        JFXDialog dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("同意");
        button.setOnAction(event1 -> {
            registerbtn.setDisable(false);//不禁用
            dialog.close();
        });

        content.setActions(button);
        dialog.setOverlayClose(false);
        dialog.show();
    }

    public void agreechkClicked() {

        if (agreechk.isSelected()) {//如果同意协议
            loadDialog((ActionEvent) agreechk.getOnMouseClicked());
        }else {
            registerbtn.setDisable(true);
        }
    }


    public void registerbtnClicked() {

        if (!username.getText().equals("")
                && !password.getText().equals("")
                && !checkpassword.getText().equals("")
                && (citycomb3.getValue() != null)
                && !address.getText().equals("")
                && !school.getText().equals("")
                && !phonecall.getText().equals("")) {

            if (userBiz.nameExist(username.getText())){
                username.clear();
                username.setUnFocusColor(Color.rgb(255,0,0));
                new JFXSnackbar(registerPane).show("名字存在",2000);
                return;
            }

            Users userstemp = new Users(1000000000,
                    username.getText(),
                    checkpassword.getText(),
                    school.getText(),
                    null,
                    citybizimpl.idByName(citycomb3.getValue()),
                    address.getText(),
                    phonecall.getText());
            userBiz.addUser(userstemp);

            Main.user = userBiz.login(username.getText(),checkpassword.getText());

            //注册成功，跳转到主页
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(registerPane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setDuration(Duration.millis(200));
            fadeTransition.setOnFinished(event -> {
                try {
                    //载入新场景
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePath.indexFXMLPath));
                    Pane indexStage = fxmlLoader.load();
                    Scene indexScene = new Scene(indexStage);
                    //用当前Stage来打开新的场景
                    Stage curStage = (Stage) registerPane.getScene().getWindow();
                    curStage.setTitle("主页");
                    curStage.setWidth(600);
                    curStage.setHeight(740);
                    curStage.setScene(indexScene);
                    curStage.centerOnScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fadeTransition.play();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //一个渐变的载入动画
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setNode(registerPane);
        fadeTransition.setFromValue(0d);
        fadeTransition.setToValue(1d);
        fadeTransition.play();

        registerbtn.setDisable(true);


         province = (ArrayList) citybizimpl.provinceByKey(0);
         for (String s: province){
             citycomb1.getItems().add(s);
         }

        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点
                setUnFocusColor();
            }
        });

        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点，
                //以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                if (!password.getText().matches("^[a-zA-Z]\\w{5,17}$")) {
                    password.clear();
                    password.setUnFocusColor(Color.rgb(255, 0, 0));
                    new JFXSnackbar(registerPane).show("密码长度6~18，只能包含字母（开头）、数字和下划线",2000);
                } else {
                    setUnFocusColor();
                }
            }
        });

        checkpassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点
                setUnFocusColor();
                if (!checkpassword.getText().equals(password.getText())) {
                    new JFXSnackbar(registerPane).show("密码不一致",2000);
                    checkpassword.clear();
                    password.setUnFocusColor(Color.rgb(255, 0, 0));
                    checkpassword.setUnFocusColor(Color.rgb(255, 0, 0));
                }
            }
        });

        citycomb1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
                citycomb2.getItems().clear();
                citycomb3.getItems().clear();
            } else {//失去焦点
                setUnFocusColor();
                String str = citycomb1.getValue();
                Integer inte = citybizimpl.idByName(str);

                city = (ArrayList<String>) citybizimpl.provinceByKey(inte);

                for (String s:city) {
                    citycomb2.getItems().add(s);
                }
                if (citycomb1.getValue()==null){
                    citycomb2.getItems().clear();
                    citycomb3.getItems().clear();
                }
            }
        });

        citycomb2.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
                citycomb3.getItems().clear();
            } else {//失去焦点
                setUnFocusColor();
                String str = citycomb2.getValue();
                Integer inte = citybizimpl.idByName(str);
                district = (ArrayList<String>) citybizimpl.provinceByKey(inte);

                for (String s:district) {
                    citycomb3.getItems().add(s);
                }
                if (citycomb2.getValue()==null){
                    citycomb3.getItems().clear();
                }
            }
        });

        citycomb3.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resetUnFocusColor();
            } else {
                setUnFocusColor();
            }
        });

        address.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();

            } else {//失去焦点
                setUnFocusColor();

//                if (status.getText().equals("")){
//                    new JFXSnackbar(registerPane).show("填写详细地址",2000);
//                }
            }
        });

        school.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点
                setUnFocusColor();

//                if (school.getText().equals("")){
//                    new JFXSnackbar(registerPane).show("填入的你学校",2000);
//                }
            }
        });

        phonecall.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else { //失去焦点，判断输入为手机号
                if (!phonecall.getText().matches(
                        "^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$")) {
                    setUnFocusColor();
                    phonecall.clear();
                    new JFXSnackbar(registerPane).show("请填写真实有效的手机号码",2000);

                } else {
                    setUnFocusColor();
                }
            }
        });


    }
}
